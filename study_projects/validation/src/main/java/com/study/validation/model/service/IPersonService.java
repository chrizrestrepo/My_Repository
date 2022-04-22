package com.study.validation.model.service;

import com.study.validation.model.entity.Person;

import java.util.List;
import java.util.Map;

public interface IPersonService {

    List<Person> getAllPeople();
    Person getPersonById(Long id);
    Map<String, Object> savePerson(Person person);
}
