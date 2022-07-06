package com.crestrepo.study.spring.data.jpa.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "students",
        uniqueConstraints = @UniqueConstraint(
        name = "email_unique",
        columnNames = "email_adress"
))
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long id;
    private String firstName;
    private String lastName;

    @Column(name = "email_adress", nullable = false)
    private String email;

    @Embedded
    private Guardian guardian;

}
