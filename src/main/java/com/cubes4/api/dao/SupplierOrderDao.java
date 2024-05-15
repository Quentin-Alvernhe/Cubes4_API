package com.cubes4.api.dao;

import com.cubes4.api.model.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierOrderDao extends JpaRepository<SupplierOrder, Integer> {
}
