package com.exercise.SpringDataBook.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "teacher")
@Table(name = "teachers")
public class Teacher {

    @Id
    @SequenceGenerator(name = "teacher_sequence", sequenceName = "teacher_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
    private Long id;
    @NotEmpty(message = "can't be null or empty")
    @Column(name = "first_name", length = 50)
    private String firstName;
    @NotEmpty(message = "can't be null or empty")
    @Column(name = "last_name", length = 50)
    private String lastName;
    @Column(name = "emial", unique = true)
    private String emialAdress;
    @Column(name = "day_birthday")
    private LocalDate dateBirthday;
    @Transient
    private Integer age;

    public Integer getAge() {
        return Period.between(this.dateBirthday, LocalDate.now()).getYears();
    }
}
