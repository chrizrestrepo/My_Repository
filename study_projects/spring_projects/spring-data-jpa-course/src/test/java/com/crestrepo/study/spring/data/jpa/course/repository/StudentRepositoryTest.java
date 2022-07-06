package com.crestrepo.study.spring.data.jpa.course.repository;

import com.crestrepo.study.spring.data.jpa.course.model.Guardian;
import com.crestrepo.study.spring.data.jpa.course.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        Guardian guardian = Guardian.builder()
                .name("Arturo")
                .email("arthur@gamil.com")
                .mobile(3156936565L)
                .build();

        Student cristian = Student.builder()
                .email("chrizrestrepo@gamil.com")
                .firstName("cristian")
                .lastName("restrepo")
                .guardian(guardian)
                .build();

        Student veronica = Student.builder()
                .firstName("veronica")
                .lastName("giraldo")
                .email("vero1546@gmail.com")
                .guardian(guardian)
                .build();

        repository.saveAll(Arrays.asList(cristian, veronica));

    }

    @Autowired
    public StudentRepositoryTest(StudentRepository repository) {
        this.repository = repository;
    }

    @Test
    void findByFirstName() {
        List<Student> student = repository.findByFirstName("cristian");
        Assertions.assertTrue(student.stream()
                .allMatch(s -> s.getFirstName().equalsIgnoreCase("cristian")));
    }

    @Test
    void findByFirstNameContaining() {
        List<Student> student = repository.findByFirstNameContaining("ver");
        Assertions.assertTrue(student.stream()
                .allMatch(s -> s.getLastName().equalsIgnoreCase("giraldo")));
    }

    @Test
    void findByLastNameNotNull() {
        repository.findByLastNameNotNull();
    }

    @Test
    void findByGuardianName() {
    }

    @Test
    void findByFirstNameAndLastName() {
    }

    @Test
    void getStudentByEmailAddress() {
    }

    @Test
    void getStudentFirstNameByEmailAddress() {
    }

    @Test
    void getStudentByEmailAddressNative() {
    }

    @Test
    void getStudentByLastName() {
    }

    @Test
    void updateStudentNameByEmail() {
    }
}