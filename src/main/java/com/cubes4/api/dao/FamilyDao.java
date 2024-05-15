package com.cubes4.api.dao;

import com.cubes4.api.model.Family;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyDao extends JpaRepository<Family, Integer> {
    List<Family> findByDeletedFalse();
}