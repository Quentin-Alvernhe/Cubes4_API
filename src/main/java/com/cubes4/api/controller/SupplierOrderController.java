package com.cubes4.api.controller;

import com.cubes4.api.dao.FamilyDao;
import com.cubes4.api.dao.ProductDao;
import com.cubes4.api.dao.StockHistoryDao;
import com.cubes4.api.dao.SupplierOrderDao;
import com.cubes4.api.model.Family;
import com.cubes4.api.model.Product;
import com.cubes4.api.model.SupplierOrder;
import com.cubes4.api.model.SupplierOrderLine;
import com.cubes4.api.model.StockHistory;
import com.cubes4.api.view.SupplierOrderLineView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
public class SupplierOrderController {

    @Autowired
    private SupplierOrderDao supplierOrderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private FamilyDao familyDao;

    @Autowired
    private StockHistoryDao stockHistoryDao;

    @GetMapping("/supplier-order/{id}")
    public ResponseEntity<?> getSupplierOrder(@PathVariable int id) {
        try {
            Optional<SupplierOrder> supplierOrderOptional = supplierOrderDao.findById(id);
            if (supplierOrderOptional.isEmpty()) {
                // Retourne une erreur 404 si produit pas trouvé
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(supplierOrderOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération de la commande fournisseur : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(SupplierOrderLineView.class)
    @GetMapping("/supplier-orders")
    public ResponseEntity<?> getSupplierOrders() {
        try {
            List<SupplierOrder> listSupplierOrders = supplierOrderDao.findAll();
            return new ResponseEntity<>(listSupplierOrders, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération des commandes fournisseurs : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/supplier-order")
    public ResponseEntity<?> updateOrCreateSupplierOrder(@RequestBody SupplierOrder supplierOrder) {
        try {
            // Si c'est une mise à jour (update)
            if (supplierOrder.getId() != null) {
                Optional<SupplierOrder> supplierOrderOptional = supplierOrderDao.findById(supplierOrder.getId());
                // si le produit n'existe pas, il s'agit d'une action non autorisée
                // cad : affecté directement un id sans suivre l'auto-incrémentation
                if (supplierOrderOptional.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                supplierOrderDao.save(supplierOrder);
                // si commande livrée, on update le stock
                if (supplierOrder.getStatus() == 3) {
                    List<SupplierOrderLine> lines = supplierOrder.getLines();
                    List<Product> productsToUpdate = new ArrayList<>();

                    for (SupplierOrderLine line : lines) {
                        try {
                            Product productToUpdate = line.getProduct();

                            familyDao.saveAll(productToUpdate.getFamilyList());
                            familyDao.flush();

                            productToUpdate.setQuantity(productToUpdate.getQuantity() + line.getQuantity());
                            productsToUpdate.add(productToUpdate);
                            create(productToUpdate); // Met à jour de l'historique des stocks
                        } catch (Exception e) {
                            return new ResponseEntity<>(
                                    "Une erreur s'est produite lors de la mise à jour du stock : "
                                            + e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR);

                        }
                    }
                    productDao.saveAll(productsToUpdate);
                    productDao.flush();
                }

                if (supplierOrder.getStatus() == 1) {
                    List<SupplierOrderLine> lines = supplierOrder.getLines();
                    List<Product> productsToUpdate = new ArrayList<>();

                    for (SupplierOrderLine line : lines) {
                        try {
                            Product productToUpdate = line.getProduct();

                            familyDao.saveAll(productToUpdate.getFamilyList());
                            familyDao.flush();

                            productToUpdate.setReliquat(productToUpdate.getReliquat() + line.getQuantity());
                            productsToUpdate.add(productToUpdate);
                            create(productToUpdate); // Met à jour de l'historique des stocks
                        } catch (Exception e) {
                            return new ResponseEntity<>(
                                    "Une erreur s'est produite lors de la mise à jour du reliquat : "
                                            + e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR);

                        }
                    }
                    productDao.saveAll(productsToUpdate);
                    productDao.flush();
                }

                // DIRTY TRICK : réalisé par un professionnel ceci n'est pas à reproduire chez
                // soi
                familyDao.deleteAll(familyDao.findAll().stream().filter(p -> p.getName() == null)
                        .collect(Collectors.toList()));
                familyDao.flush();

                return new ResponseEntity<>(supplierOrderOptional.get(), HttpStatus.OK);
            }

            // si c'est une création
            supplierOrderDao.save(supplierOrder);
            return new ResponseEntity<>(supplierOrder, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de l'ajout de la commande fournisseur : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/supplier-order/{id}")
    public ResponseEntity<?> deleteSupplierOrder(@PathVariable int id) {
        try {
            Optional<SupplierOrder> supplierOrderOptional = supplierOrderDao.findById(id);
            if (supplierOrderOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            supplierOrderDao.deleteById(id);
            return new ResponseEntity<>(supplierOrderOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la supprression de la commande fournisseurs : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void create(Product product) {
        StockHistory stockHistory = new StockHistory();
        stockHistory.setQuantity(product.getQuantity());
        stockHistory.setProduct(product);
        stockHistory.setDate(LocalDateTime.now());
        stockHistoryDao.save(stockHistory);
    }
}
