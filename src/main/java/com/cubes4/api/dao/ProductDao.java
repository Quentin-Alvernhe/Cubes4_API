package com.cubes4.api.dao;

import com.cubes4.api.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Interface spécifiant des méthodes de base pour effectuer des opérations CRUD (Create, Read, Update, Delete) sur l'entité Product
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    List<Product> findByDeletedFalse();

    @Query("SELECT p FROM Product p WHERE p.supplier.id = :idSupplier")
    List<Product> findBySupplierId(@Param(value = "idSupplier") int idSupplier);
}
