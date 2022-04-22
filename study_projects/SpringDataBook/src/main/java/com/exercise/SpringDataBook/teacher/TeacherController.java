package com.exercise.SpringDataBook.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllTeacher(){
        return this.teacherService.getAllTeacher();
    }

    @GetMapping("/")
    public ResponseEntity<?> getTeacherByNameAndLastName(String name, String lastName){

        try{
            this.teacherService.getTeacherByNameAndLastName(name, lastName)
                    .orElseThrow();
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<?> saveNewTeacher(@Valid @RequestBody Teacher teacher, BindingResult result){

        Map<String, Object> response = new LinkedHashMap<>();

        if(result.hasErrors()){
            List<String> error = result.getFieldErrors()
                    .stream()
                    .map(e -> "the field " + e.getField() + " " + e.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", error);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response = teacherService.createNewTeacher(teacher);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
