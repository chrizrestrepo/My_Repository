package com.prueba.tecnica.titamedia.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public User(String firstName, List<Debt> debts) {
        this.firstName = firstName;
        this.debts = debts;
    }

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long userId;

    @Column(length = 100, nullable = false, name = "first_name")
    private String firstName;

    @OneToMany(targetEntity = Bank.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user", referencedColumnName = "userId")
    private List<Debt> debts;

    @ManyToOne(targetEntity = Bank.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_bank", referencedColumnName = "bankId")
    private Bank bank;

}
