package com.prueba.tecnica.titamedia.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "banks")
public class Bank {

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    @Id
    @SequenceGenerator(name = "bank_sequence", sequenceName = "bank_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_sequence")
    private Long bankId;

    @Column(length = 100, nullable = false, name = "bank_name")
    private String bankName;
}
