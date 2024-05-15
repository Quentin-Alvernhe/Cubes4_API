package com.cubes4.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.SQLDelete;
import com.cubes4.api.view.SupplierOrderLineView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE supplier SET deleted = true WHERE id=?")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(SupplierOrderLineView.class)
    private Integer id;

    @JsonView(SupplierOrderLineView.class)
    private String name;

    @JsonView(SupplierOrderLineView.class)
    private String adress;

    @JsonView(SupplierOrderLineView.class)
    private String email;

    @JsonView(SupplierOrderLineView.class)
    private String phoneNumber;
    private boolean deleted = false;
}