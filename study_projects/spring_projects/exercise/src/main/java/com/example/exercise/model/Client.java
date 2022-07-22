package com.example.exercise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client implements Serializable {

    private static final Long serialVersionUUID = 1L;

    public Client(String identification, String firstName, String lastName, String email) {
        this.identification = identification;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Client(Long clientId, String identification, String firstName, String lastName, String email) {
        this.clientId = clientId;
        this.identification = identification;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "identification", nullable = false, unique = true)
    private String identification;

    @Column(name = "first_name", length = 50, nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Email(message = "the emial doesn't permitted")
    private String email;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Bill.class)
    private List<Bill> bills;

    public Client() {
       bills = new ArrayList<>();
    }

    public void addBill(Bill bill){
        bills.add(bill);
    }


}
