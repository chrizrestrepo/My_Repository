package com.crestrepo.study.spring.data.jpa.course;

import com.crestrepo.study.spring.data.jpa.course.model.Course;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class DataTest {

    public static final List<Course> COURSES = createCourses();

    private List<Course> createCourses() {
        return Arrays.asList(
                Course.builder().title("Java").credit(3).build(),
                Course.builder().title("Javascript").credit(3).build(),
                Course.builder().title(".Net").credit(3).build(),
                Course.builder().title("C#").credit(3).build(),
                Course.builder().title("Angular").credit(3).build(),
                Course.builder().title("Spring Boot").credit(3).build(),
                Course.builder().title("Spring WebFlux").credit(3).build(),
                Course.builder().title("Flutter").credit(3).build(),
                Course.builder().title("Phyton").credit(3).build(),
                Course.builder().title("React").credit(3).build()
            );
    }


}
