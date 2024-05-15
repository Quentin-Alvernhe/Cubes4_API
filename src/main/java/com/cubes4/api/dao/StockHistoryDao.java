package com.cubes4.api.dao;

import com.cubes4.api.model.Product;
import com.cubes4.api.model.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockHistoryDao extends JpaRepository<StockHistory, Integer> {
    Optional<List<StockHistory>> findAllByProduct(Product product);
}
