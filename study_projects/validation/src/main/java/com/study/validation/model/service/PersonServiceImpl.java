package com.study.validation.model.service;

import com.study.validation.model.entity.Person;
import com.study.validation.model.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements IPersonService{

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("the person with id: " + id + " not found"));
    }

    @Override
    public Map<String, Object> savePerson(Person person) {

        Map<String, Object> response = new LinkedHashMap<>();

        try{
            personRepository.save(person);
        }catch(IllegalArgumentException e){
            response.put("message", "the person can't be save");
            response.put("error", e.getMessage() + ": \n cause by" + e.getCause().getMessage());
            return response;
        }

        response.put("message", "the registry has been save");

        return response;
    }
}
