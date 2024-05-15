package com.cubes4.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import com.cubes4.api.view.SupplierOrderLineView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    @JsonView(SupplierOrderLineView.class)
    private Integer id;

    @JsonView(SupplierOrderLineView.class)
    private String firstname;

    @JsonView(SupplierOrderLineView.class)
    private String lastname;

    @JsonView(SupplierOrderLineView.class)
    private String login;

    @JsonView(SupplierOrderLineView.class)
    private String password;

    @ManyToOne
    @JsonView(SupplierOrderLineView.class)
    private Role role;

    private boolean deleted = false;
}
