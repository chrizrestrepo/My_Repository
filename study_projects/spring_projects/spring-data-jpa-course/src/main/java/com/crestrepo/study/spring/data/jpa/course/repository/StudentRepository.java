package com.crestrepo.study.spring.data.jpa.course.repository;

import com.crestrepo.study.spring.data.jpa.course.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String name);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String LastName);

    @Query("select s from Student s where s.email = ?1")
    Student getStudentByEmailAddress(String email);

    @Query("select s.firstName from Student s where s.email = ?1")
    String getStudentFirstNameByEmailAddress(String email);

    @Query(value = "SELECT * FROM students s WHERE s.email_adress = ?1", nativeQuery = true)
    Student getStudentByEmailAddressNative(String email);

    @Query("select s from Student s where s.lastName = :lastName")
    List<Student> getStudentByLastName(@Param("lastName") String lastName);

    @Modifying
    @Transactional
    @Query("update Student set firstName = :name where email = :email")
    void updateStudentNameByEmail(@Param("name") String name, @Param("email") String email);

}
