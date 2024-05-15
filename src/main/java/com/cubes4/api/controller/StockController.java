package com.cubes4.api.controller;

import com.cubes4.api.dao.ProductDao;
import com.cubes4.api.dao.StockHistoryDao;
import com.cubes4.api.model.Product;
import com.cubes4.api.model.StockHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController
public class StockController {
    @Autowired
    private StockHistoryDao stockHistoryDao;

    @Autowired
    private ProductDao productDao;

    @GetMapping("/stocks")
    public ResponseEntity<List<StockHistory>> getAll() {
        return new ResponseEntity<List<StockHistory>>(stockHistoryDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<List<StockHistory>> get(@PathVariable int id) {

        Product product = productDao.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<StockHistory> stock = stockHistoryDao.findAllByProduct(product).orElse(null);
        if (stock == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<StockHistory>>(stock, HttpStatus.OK);
    }
}