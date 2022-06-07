package com.prueba.tecnica.titamedia.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "debts")
public class Debt {

    public Debt(Bank bank, Long outstandingBalance) {
        this.bank = bank;
        this.outstandingBalance = outstandingBalance;
    }

    @Id
    @SequenceGenerator(name = "debt_sequence", sequenceName = "debt_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debt_sequence")
    private Long debtId;

    @Column(name = "pay_reference")
    private String payReference;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "fk_bank", referencedColumnName = "bankId")
    private Bank bank;

    @Column(name = "outstanding_balance")
    private Long outstandingBalance;

}
