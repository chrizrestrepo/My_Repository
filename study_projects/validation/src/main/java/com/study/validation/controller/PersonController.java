package com.study.validation.controller;

import com.study.validation.exceptions.InvalidDataException;
import com.study.validation.model.entity.Person;
import com.study.validation.model.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/persons")
public class PersonController {

    private PersonServiceImpl personService;

    @Autowired
    public PersonController(PersonServiceImpl personService){
        this.personService = personService;
    }

    @GetMapping(path = "")
    public List<Person> getAllpeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "/id")
    public Person getPersonById(@RequestParam(value = "id") Long id){
        return personService.getPersonById(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveNewPerson(@Valid @RequestBody Person person, BindingResult result){

        Map<String, Object> response;

        if(result.hasErrors()){
            throw new InvalidDataException(result);
        }
        response = personService.savePerson(person);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
