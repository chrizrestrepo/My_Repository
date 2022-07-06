package com.exercise.SpringDataBook.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.listOfStudents();
    }

    @GetMapping(path = "/pageDesc")
    public Page<Student> getAllStudentDesc(){
        return studentService.getAllStudentsDescOrder();
    }

    @GetMapping(path = "/emailOrder")
    public List<Student> getAllStudentsOrderByNameAndEmail(@RequestParam String name){
        return studentService.findStudentsByNameOrderEmail(name);
    }

    @GetMapping(path = "/map")
    public Map<Long, Student> getMapOfStudents(){
        return studentService.mapOfStudent();
    }

    @GetMapping(path = "/any")
    public List<Student> getStudentsByNameOrLastName(@RequestParam(value = "name") String name, @RequestParam(value = "lastName") String lastName){
        return studentService.findStudenByNameOrLastName(name, lastName);
    }

    @GetMapping(path = "/name")
    public List<Student> getStudentsByName(@RequestParam(value = "name") String name){
        return studentService.findStudentByName(name);
    }

    @GetMapping(path = "/id")
    public Student getStudentById(@RequestParam(value = "id") Long id){
        return studentService.findStudentByID(id);
    }

    @GetMapping(path = "/nala")
    public List<Student> getStudentsByNameAndLastName(@RequestParam(value = "name") String name, @RequestParam(value = "lastName") String lastName){
        return studentService.findStudentByNameAndLastName(name, lastName);
    }

    @PostMapping
    public void createANewStudent(@RequestBody Student student){
        studentService.newStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudentById(studentId);
    }

    @PutMapping(path = {"studentId"}) public void updateStudent(
            @PathVariable Long studentId,
            @RequestParam(required = false)String name,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, lastName, email);
    }
}
