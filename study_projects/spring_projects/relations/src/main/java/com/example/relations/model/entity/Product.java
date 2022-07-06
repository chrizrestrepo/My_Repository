package com.example.relations.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nameProduct;

    @Column(length = 100)
    private String brand;

    @Column
    private Integer price;

    public Product(String nameProduct, String brand, Integer price) {
        this.nameProduct = nameProduct;
        this.brand = brand;
        this.price = price;
    }
}
