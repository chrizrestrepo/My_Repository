package com.spring.security.springsecuritycourse.controller;

import com.spring.security.springsecuritycourse.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private List<Student> STUDENT_LIST = Arrays.asList(
            new Student("123", "cristian"),
            new Student("456", "veronica"),
            new Student("789", "hamilton"),
            new Student("890", "simon"));

    @GetMapping(path = "/get/{studentId}")
    public Student getStudentById(@PathVariable(name = "studentId") String studentId){
        return STUDENT_LIST
                .stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("the Student with id: ".concat(studentId).concat(" Not found")));
    }

    @GetMapping(path = "/name/{studentName}")
    public Student getStudentByName(@PathVariable(name = "studentName") String studentName){
        return STUDENT_LIST
                .stream()
                .filter(student -> student.getFirstName().equals(studentName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("the Student with id: ".concat(studentName).concat(" Not found")));
    }
}
