package com.study.validation.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person implements Serializable {

    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    private Long id;

    @NotEmpty(message = "can not be void")
    private String firstName;

    @NotEmpty(message = "can not be void")
    private String lastName;

    @Past(message = "most be in the past tense")
    private LocalDate dateOfBirth;

    @Transient
    private Integer age;

    public Integer getAge() {
        return Period.between(LocalDate.now(), dateOfBirth).getYears();
    }
}
