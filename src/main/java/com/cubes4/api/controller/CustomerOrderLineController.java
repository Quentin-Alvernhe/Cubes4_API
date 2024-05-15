package com.cubes4.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cubes4.api.dao.CustomerOrderLineDao;
import com.cubes4.api.model.CustomerOrderLine;

@CrossOrigin
@RestController
public class CustomerOrderLineController {

    @Autowired
    private CustomerOrderLineDao customerOrderLineDao;

    @PostMapping("/customer-order-line")
    public ResponseEntity<?> createCustomerOrderLine(@RequestBody CustomerOrderLine customerOrderLine) {
        try {
            customerOrderLineDao.save(customerOrderLine);
            return new ResponseEntity<>(customerOrderLine, HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.toString());
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la création ou de la mise à jour de la ligne de commande client : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
