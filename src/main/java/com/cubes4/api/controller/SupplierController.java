package com.cubes4.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.cubes4.api.dao.ProductDao;
import com.cubes4.api.dao.SupplierDao;
import com.cubes4.api.model.Product;
import com.cubes4.api.model.Supplier;

@CrossOrigin()
@RestController
public class SupplierController {

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private ProductDao productDao;

    // Obtenir un produit
    @GetMapping("/supplier/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable int id) {
        try {
            Optional<Supplier> supplierOptional = supplierDao.findById(id);
            if (supplierOptional.isEmpty()) {
                // Retourne une erreur 404 si fournisseur pas trouvé
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(supplierOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération du fournisseur : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtenir la liste des produits
    @GetMapping("/suppliers")
    public ResponseEntity<?> getSuppliers() {
        try {
            List<Supplier> listSuppliers = supplierDao.findByDeletedFalse();
            return new ResponseEntity<>(listSuppliers, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération des fournisseurs : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/supplier/{id}/products")
    public ResponseEntity<List<Product>> getSupplierProducts(@PathVariable int id) {
        return new ResponseEntity<>(productDao.findBySupplierId(id), HttpStatus.OK);
    }

    // Ajouter un fournisseur et modifier
    @PostMapping("/supplier")
    public ResponseEntity<?> updateOrCreateSupplier(@RequestBody Supplier supplier) {
        try {
            // Si c'est une mise à jour (update)
            if (supplier.getId() != null) {
                Optional<Supplier> supplierOptional = supplierDao.findById(supplier.getId());
                // si le fournisseur n'existe pas, il s'agit d'une action non autorisée
                // cad : affecté directement un id sans suivre l'auto-incrémentation
                if (supplierOptional.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                supplierDao.save(supplier);
                return new ResponseEntity<>(supplierOptional.get(), HttpStatus.OK);
            }

            // si c'est une création
            supplierDao.save(supplier);
            return new ResponseEntity<>(supplier, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de l'ajout des fournisseurs : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Supprimer un fournisseur
    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable int id) {
        try {
            Optional<Supplier> supplierOptional = supplierDao.findById(id);
            if (supplierOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            supplierDao.deleteById(id);
            return new ResponseEntity<>(supplierOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la supprression des fournisseurs : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
