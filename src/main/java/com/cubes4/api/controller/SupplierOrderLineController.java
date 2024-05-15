package com.cubes4.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cubes4.api.dao.SupplierOrderLineDao;
import com.cubes4.api.model.SupplierOrderLine;

@CrossOrigin
@RestController
public class SupplierOrderLineController {

    @Autowired
    private SupplierOrderLineDao supplierOrderLineDao;

    @PostMapping("/supplier-order-line")
    public ResponseEntity<?> createSupplierOrderLine(@RequestBody SupplierOrderLine supplierOrderLine) {
        try {
            supplierOrderLineDao.save(supplierOrderLine);
            return new ResponseEntity<>(supplierOrderLine, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.toString());
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la création ou de la mise à jour de la ligne de commande fournisseur : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
