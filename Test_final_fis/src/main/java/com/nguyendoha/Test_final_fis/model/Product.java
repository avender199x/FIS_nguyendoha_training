package com.nguyendoha.Test_final_fis.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "price",nullable = false)
    private Double price;
    @Column(name = "available",nullable = false)
    private Long available;

}
