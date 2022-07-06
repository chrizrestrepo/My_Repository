package com.exercise.SpringDataBook.student;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "student")
@Table
public class Student implements Serializable {

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long id;

    @NotEmpty
    @Column(name = "firts_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String emailAdress;

    private LocalDate birthDay;

    @Transient
    private Integer age;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, String emailAdress, LocalDate birthDay, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdress = emailAdress;
        this.birthDay = birthDay;
        this.age = age;
    }

    public Student(String firstName, String lastName, String emailAdress, LocalDate birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdress = emailAdress;
        this.birthDay = birthDay;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public Integer getAge() {
        return Period.between(this.birthDay, LocalDate.now()).getYears();
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAdress='" + emailAdress + '\'' +
                ", birthDay=" + birthDay +
                ", age=" + age +
                '}';
    }
}
