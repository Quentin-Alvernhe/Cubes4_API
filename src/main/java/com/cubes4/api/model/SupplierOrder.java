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

public class SupplierOrder {

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
    private Supplier supplier;

    @OneToMany(mappedBy = "order")
    @JsonView(SupplierOrderLineView.class)
    private List<SupplierOrderLine> lines;

}
