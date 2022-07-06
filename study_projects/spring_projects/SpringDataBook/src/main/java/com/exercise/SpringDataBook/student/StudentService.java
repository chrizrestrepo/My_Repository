package com.exercise.SpringDataBook.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> listOfStudents(){
        return studentRepository.findAll();
    }

    public Map<Long, Student> mapOfStudent(){
       Map<Long,Student> mapa =  studentRepository.findAll().stream()
               .collect(Collectors.toMap(Student::getId, Function.identity()));
       return mapa;
    }

    public Student findStudentByID(Long id){
        Student student = studentRepository.findById(id).orElseThrow();
        return student;
    }

    public List<Student> findStudentByName(String name){
        List<Student> listStudent = studentRepository.findByFirstName(name);
        return listStudent;
    }

    public List<Student> findStudentByNameAndLastName(String emailAdress, String name){
        List<Student> student = studentRepository.findByFirstNameAndLastName(emailAdress,name);
        return student;
    }

    public List<Student> findStudenByNameOrLastName(String name, String lastName){
        List<Student> listStudents= studentRepository.findByFirstNameOrLastName(name, lastName);
        return listStudents;
    }

    public void newStudent(Student student) {
        if(studentRepository.findByEmailAdress(student.getEmailAdress()).isPresent()){
            throw new IllegalStateException("Email Already Exist");
        }
        studentRepository.save(student);
    }

    public void deleteStudentById(Long id){
        boolean exists = studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Student whit id" + id + "does not exists");
        }
        studentRepository.deleteById(id);
    }

    public Page<Student> getAllStudentsDescOrder(){
        Pageable pageable = PageRequest.of(1,3, Sort.by(Sort.Direction.DESC, "firstName"));
        return studentRepository.findAll(pageable);
    }

    public List<Student> findStudentsByNameOrderEmail(String name){
        return studentRepository.findByFirstName(name, Sort.by(Sort.Direction.DESC, "emailAdress"));
    }

    public void updateStudent(Long id, String name, String lastname, String email){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("the Student with id " + id + "does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getFirstName(), name)){
            student.setFirstName(name);
        }

        if(lastname != null && lastname.length() > 0 && !Objects.equals(student.getLastName(), lastname)){
            student.setLastName(lastname);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmailAdress(), email)){
            Optional<Student> studentOptional = studentRepository.findByEmailAdress(email);
            if(studentOptional.isPresent()){
                new IllegalStateException("email taken");
            }
            student.setEmailAdress(email);
        }
    }
}
