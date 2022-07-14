package com.example.exercise.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bills")
@Getter
@Setter
public class Bill implements Serializable {

    private static final Long serialVersionUUID = 1L;

    public Bill() {
        items = new ArrayList<>();
    }

    public Bill(String description, String observation) {
        this.description = description;
        this.observation = observation;
        items = new ArrayList<>();
    }

    @Id
    @SequenceGenerator(name = "bill_sequence", sequenceName = "bill_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "bill_sequence")
    @Column(name = "bill_id")
    private Long billId;

    @Column(name = "buy_description")
    private String description;

    @Column(name = "buy_observation")
    private String observation;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    @Temporal(TemporalType.TIME)
    @Column(name = "create_hour")
    @DateTimeFormat(pattern = "^[0-2][0-3]:[0-5][0-9]$")
    private Date createHour;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_fk", referencedColumnName = "bill_id")
    private List<Item> items;

    @Column(name = "total_bill")
    private Double totalBill;

    public void addItem(List<Item> item){
        items.addAll(item);
    }

    public Double calculateTotalBill(){
        return items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getAmount())
                .sum();
    }

    @PrePersist
    public void prePresist(){
        createAt = new Date();
        createHour = new Date();
    }
}
