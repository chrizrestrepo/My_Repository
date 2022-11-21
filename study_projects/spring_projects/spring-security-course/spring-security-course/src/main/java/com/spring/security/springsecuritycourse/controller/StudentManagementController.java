package com.spring.security.springsecuritycourse.controller;

import com.spring.security.springsecuritycourse.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private List<Student> STUDENT_LIST = Arrays.asList(
            new Student("123", "cristian"),
            new Student("456", "veronica"),
            new Student("789", "hamilton"),
            new Student("890", "simon"));

    //podemos realizar la configuracion de los antMatchers por medio de la anotacion PreAuthorize usando las
    //siguinetes definiciones, las cuales son casi identicas a los metodos del Bean de configuracion para los
    //antMatchers
    //hasRole('ROLE_') hasAnyRole('ROLE_', 'ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    //para habilitar el uso de la anotacion PreAuthorize, es necesario usar la anotacion
    //@EnableGlobalMethodSecurity(prePostEnabled = true) en la clase de configuracion con el valor prePostEnabled en true

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents(){
        return STUDENT_LIST;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public String createNewStudent(@RequestBody Student student){
        return student.toString();
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public String deleteStudent(@PathVariable(name = "studentId") String studentId){
        return "the student with ID: ".concat(studentId).concat(" deleted");
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public String updateStudentInfo(@PathVariable(name = "studentId") String studentId, @RequestBody Student student){
        return String.format("%s %s", studentId, student.toString());
    }
}
