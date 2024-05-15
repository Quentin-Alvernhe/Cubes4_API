package com.cubes4.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.cubes4.api.view.SupplierOrderLineView;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter

public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(SupplierOrderLineView.class)
    private Integer id;

    @JsonView(SupplierOrderLineView.class)
    private LocalDateTime date;

    @JsonView(SupplierOrderLineView.class)
    private int status;

    @ManyToOne
    @JsonView(SupplierOrderLineView.class)
    private User customer;

    @OneToMany(mappedBy = "customerorder")
    @JsonView(SupplierOrderLineView.class)
    private List<CustomerOrderLine> lines;

}
