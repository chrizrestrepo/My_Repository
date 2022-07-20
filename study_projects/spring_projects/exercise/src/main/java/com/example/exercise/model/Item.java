package com.example.exercise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "bill_items")
@Getter
@Setter
@NoArgsConstructor
public class Item implements Serializable {

    private static final Long serialVersionUUID = 1L;

    public Item(Integer amount) {
        this.amount = amount;
    }

    @Id
    @Column(name = "item_id")
    @GeneratedValue
    private UUID itemId;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_fk", referencedColumnName = "product_id")
    private Product product;

    public Double calculateAmount(){
        return product.getPrice() * amount.doubleValue();
    }
}
