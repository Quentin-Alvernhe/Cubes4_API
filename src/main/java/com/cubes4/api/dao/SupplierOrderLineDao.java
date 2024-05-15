package com.cubes4.api.dao;

import com.cubes4.api.model.SupplierOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierOrderLineDao extends JpaRepository<SupplierOrderLine, Integer> {
}
