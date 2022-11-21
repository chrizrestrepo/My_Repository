package com.spring.security.springsecuritycourse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

//    public Student(String firstName) {
//        this.firstName = firstName;
//    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String firstName;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
