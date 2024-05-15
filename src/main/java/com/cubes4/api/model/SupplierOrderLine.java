package com.cubes4.api.model;

import com.cubes4.api.view.SupplierOrderLineView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class SupplierOrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    @JsonView(SupplierOrderLineView.class)
    private Integer id;

    @JsonView(SupplierOrderLineView.class)
    private int quantity;

    @JsonView(SupplierOrderLineView.class)
    private int price;

    @ManyToOne
    private SupplierOrder order;

    @ManyToOne
    @JsonView(SupplierOrderLineView.class)
    private Product product;

}
