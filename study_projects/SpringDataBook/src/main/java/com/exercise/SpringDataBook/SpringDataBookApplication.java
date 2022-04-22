package com.exercise.SpringDataBook;

import com.exercise.SpringDataBook.student.Student;
import com.exercise.SpringDataBook.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class SpringDataBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataBookApplication.class, args);
	}

	//metodo para crear estudiantes desde dentro de api

    @Bean
    public CommandLineRunner saveSomeStudent(StudentRepository repository) {
        return args -> {
        Student camilo = new Student("camilo", "montoya", "cami.montoya@yopmail.com", LocalDate.of(2001, Month.JANUARY, 1));
        Student cristian = new Student("cristian", "restrepo", "chrizrestrepo@hotmail.com", LocalDate.of(1994, Month.JULY, 1));
        Student hamilton = new Student("hamilton", "restrepo", "altanera@hotmail.com", LocalDate.of(1997, Month.OCTOBER, 14));
			repository.saveAll(
                List.of(camilo, cristian, hamilton)
        );
        };
    }

}
