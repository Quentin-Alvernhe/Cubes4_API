package com.cubes4.api.dao;

import com.cubes4.api.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderDao extends JpaRepository<CustomerOrder, Integer> {
}
