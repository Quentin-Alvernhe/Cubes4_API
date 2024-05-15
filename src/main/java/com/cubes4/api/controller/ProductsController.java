package com.cubes4.api.controller;

import java.util.List;

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
import java.util.Optional;

import com.cubes4.api.dao.ProductDao;
import com.cubes4.api.model.Product;

@CrossOrigin()
@RestController
public class ProductsController {

    // Annotation est utilisée pour demander à Spring d'injecter automatiquement une
    // dépendance de type ProductDao ici
    @Autowired
    private ProductDao productDao;

    // Obtenir un produit
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        try {
            Optional<Product> productOptional = productDao.findById(id);
            if (productOptional.isEmpty()) {
                // Retourne une erreur 404 si produit pas trouvé
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération du produit : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtenir la liste des produits
    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        try {
            List<Product> listProducts = productDao.findByDeletedFalse();
            return new ResponseEntity<>(listProducts, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération des produits : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Ajouter un produit et modifier
    @PostMapping("/product")
    public ResponseEntity<?> updateOrCreateProduct(@RequestBody Product product) {
        try {
            // Si c'est une mise à jour (update)
            if (product.getId() != null) {
                Optional<Product> productOptional = productDao.findById(product.getId());
                // si le produit n'existe pas, il s'agit d'une action non autorisée
                // cad : affecté directement un id sans suivre l'auto-incrémentation
                // ou editer un article supprimé
                if (productOptional.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                productDao.save(product);
                return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
            }

            // si c'est une création
            productDao.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de l'ajout des produits : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Supprimer un produit
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        try {
            Optional<Product> productOptional = productDao.findById(id);
            if (productOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            productDao.deleteById(id);

            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la supprression des produits : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/manager/change-stock/{idProduct}/{quantity}")
    public ResponseEntity<?> changeStock(@PathVariable int idProduct, @PathVariable int quantity) {
        try {
            Optional<Product> productOptional = productDao.findById(idProduct);

            if (productOptional.isPresent()) {
                Product product = productOptional.get();

                // si la quantité à modifier est positive
                // ou si la quantité à supprimer est inférieur au stock
                // (cad qu'il y a assez de stock à supprimer)
                if (quantity > 0 || product.getQuantity() > -quantity) {
                    product.setQuantity(product.getQuantity() + quantity);
                    productDao.save(product);
                    return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }

            // si le produit n'existe pas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de l'ajout des produits : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
