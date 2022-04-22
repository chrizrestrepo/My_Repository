package com.exercise.SpringDataBook.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeacher(){
        return this.teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherByNameAndLastName(String name, String lastName){
        return this.teacherRepository.findByFirstNameAndLastName(name, lastName);
    }

    public Map<String, Object> createNewTeacher(Teacher teacher){

        Map<String, Object> response = new LinkedHashMap<>();

            try {
                this.teacherRepository.save(teacher);
            } catch (DataAccessException e) {
                response.put("message", "the registry with name: " + teacher.getFirstName() + " " + "can't be save");
                response.put("response", e.getMessage() + " " + e.getMostSpecificCause().getMessage());
                return response;
        }
        response.put("massage", "registry save");
        return response;
    }

    
}
