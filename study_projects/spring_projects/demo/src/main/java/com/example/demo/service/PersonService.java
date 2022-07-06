package com.example.demo.service;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDAO personDAO;

    @Autowired
    public PersonService(@Qualifier("fakeDAO") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public int addPerson(Person person){
        return personDAO.insertPerson(person);
    }

    public List<Person> getAllPeople(){
        return personDAO.getAllPeople();
    }

    public Optional<Person> selectPersonByID(UUID id){
        return personDAO.selectPersonByID(id);
    }

    public int deletePersonById(UUID id){
        return personDAO.deletePersonByID(id);
    }

    public int updatePersonById(UUID id, Person person){
        return personDAO.updatePersonByID(id, person);
    }
}
