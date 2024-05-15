package com.cubes4.api.dao;

import com.cubes4.api.model.CustomerOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderLineDao extends JpaRepository<CustomerOrderLine, Integer> {
}
