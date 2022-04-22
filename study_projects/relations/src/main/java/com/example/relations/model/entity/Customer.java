package com.example.relations.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(unique = true, length = 250)
    private String email;

    /*en una relacion one to one el propietario de la relacion es el lado en donde se ubica el JoinColumn
    por lo que la columna con la FK es contenida en dicha tabla o entidad, en el caso de que sea una relacion
    bidereccional se debe generar un JoinTable en donde se relacionen ambas claves
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_phone")
    private Phone phone;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_costumer", referencedColumnName = "id")
    private List<Product> products;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
