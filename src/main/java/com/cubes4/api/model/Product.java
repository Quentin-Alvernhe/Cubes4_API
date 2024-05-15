package com.cubes4.api.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import com.cubes4.api.view.SupplierOrderLineView;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id=?")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(SupplierOrderLineView.class)
    private Integer id;

    @JsonView(SupplierOrderLineView.class)
    private String name;

    @JsonView(SupplierOrderLineView.class)
    private int quantity;

    @JsonView(SupplierOrderLineView.class)
    private int reliquat;

    @JsonView(SupplierOrderLineView.class)
    private int price;

    @JsonView(SupplierOrderLineView.class)
    private int purchase_price;

    @ManyToOne
    @JsonView(SupplierOrderLineView.class)
    private Supplier supplier;

    @JsonView(SupplierOrderLineView.class)
    private String image;

    @JsonView(SupplierOrderLineView.class)
    private String description;

    @JsonView(SupplierOrderLineView.class)
    private String degustation;

    @ManyToMany()
    @JoinTable(name = "family_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "family_id"))
    @JsonView(SupplierOrderLineView.class)
    List<Family> familyList = new ArrayList<>();

    private boolean deleted = false;
}
