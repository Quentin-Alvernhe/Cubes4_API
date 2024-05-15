package com.cubes4.api.controller;

import com.cubes4.api.dao.FamilyDao;
import com.cubes4.api.dao.ProductDao;
import com.cubes4.api.dao.StockHistoryDao;
import com.cubes4.api.dao.CustomerOrderDao;
import com.cubes4.api.model.Family;
import com.cubes4.api.model.Product;
import com.cubes4.api.model.CustomerOrder;
import com.cubes4.api.model.CustomerOrderLine;
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
public class CustomerOrderController {

    @Autowired
    private CustomerOrderDao customerOrderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private FamilyDao familyDao;

    @Autowired
    private StockHistoryDao stockHistoryDao;

    @GetMapping("/customer-order/{id}")
    public ResponseEntity<?> getCustomerOrder(@PathVariable int id) {
        try {
            Optional<CustomerOrder> customerOrderOptional = customerOrderDao.findById(id);
            if (customerOrderOptional.isEmpty()) {
                // Retourne une erreur 404 si produit pas trouvé
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(customerOrderOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération de la commande client : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @JsonView(SupplierOrderLineView.class)
    @GetMapping("/customer-orders")
    public ResponseEntity<?> getCustomerOrders() {
        try {
            List<CustomerOrder> listCustomerOrders = customerOrderDao.findAll();
            return new ResponseEntity<>(listCustomerOrders, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la récupération des commandes clients : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/customer-order")
    public ResponseEntity<?> updateOrCreateCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        try {
            if (customerOrder.getId() != null) {
                Optional<CustomerOrder> customerOrderOptional = customerOrderDao.findById(customerOrder.getId());
                // si le produit n'existe pas, il s'agit d'une action non autorisée
                // cad : affecté directement un ied sans suivre l'auto-incrémentation
                if (customerOrderOptional.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                customerOrderDao.save(customerOrder);
                // si commande livrée, on update le stock
                if (customerOrder.getStatus() == 2) {
                    List<CustomerOrderLine> lines = customerOrder.getLines();
                    List<Product> productsToUpdate = new ArrayList<>();

                    for (CustomerOrderLine line : lines) {
                        try {
                            Product productToUpdate = line.getProduct();

                            familyDao.saveAll(productToUpdate.getFamilyList());
                            familyDao.flush();

                            productToUpdate.setQuantity(productToUpdate.getQuantity() -
                                    line.getQuantity());
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
                if (customerOrder.getStatus() == 1) {
                    List<CustomerOrderLine> lines = customerOrder.getLines();
                    List<Product> productsToUpdate = new ArrayList<>();

                    for (CustomerOrderLine line : lines) {
                        try {
                            Product productToUpdate = line.getProduct();

                            familyDao.saveAll(productToUpdate.getFamilyList());
                            familyDao.flush();

                            productToUpdate.setReliquat(productToUpdate.getReliquat() -
                                    line.getQuantity());
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

                // DIRTY TRICK : réalisé par un professionnel ceci n'est pas à reproduire
                // chez soi
                familyDao.deleteAll(familyDao.findAll().stream().filter(p -> p.getName() == null)
                        .collect(Collectors.toList()));
                familyDao.flush();

                return new ResponseEntity<>(HttpStatus.OK);
            }

            // si c'est une création
            customerOrderDao.save(customerOrder);
            return new ResponseEntity<>(customerOrder, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de l'ajout de la commande client : "
                            + exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customer-order/{id}")
    public ResponseEntity<?> deleteCustomerOrder(@PathVariable int id) {
        try {
            Optional<CustomerOrder> customerOrderOptional = customerOrderDao.findById(id);
            if (customerOrderOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            customerOrderDao.deleteById(id);
            return new ResponseEntity<>(customerOrderOptional.get(), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    "Une erreur s'est produite lors de la supprression de la commande clients : "
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