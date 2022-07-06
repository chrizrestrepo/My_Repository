package com.example.exercise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bill_items")
@Getter
@Setter
@NoArgsConstructor
public class Item implements Serializable {

    private static final Long serialVersionUUID = 1L;

    @Id
    @SequenceGenerator(name = "item_sequence", sequenceName = "item_sequence", allocationSize = 1)
    @GeneratedValue(generator = "item_sequence", strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_fk", referencedColumnName = "product_id")
    private Product product;

    public Double calculateAmount(){
        return product.getPrice() * amount.doubleValue();
    }
}
