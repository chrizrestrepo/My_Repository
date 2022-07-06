package com.crestrepo.study.spring.data.jpa.course.repository;

import com.crestrepo.study.spring.data.jpa.course.DataTest;
import com.crestrepo.study.spring.data.jpa.course.model.Course;
import com.crestrepo.study.spring.data.jpa.course.model.CourseMaterial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    private final CourseMaterialRepository materialRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseMaterialRepositoryTest(CourseMaterialRepository materialRepository, CourseRepository courseRepository) {
        this.materialRepository = materialRepository;
        this.courseRepository = courseRepository;
    }

    @BeforeEach
    void setUp() {
        courseRepository.saveAll(DataTest.COURSES);
    }

    @Test
    void saveCourseMaterialCascade() {
        Course course = Course.builder()
                .title("develop")
                .credit(3)
                .build();

        CourseMaterial material = CourseMaterial.builder()
                .course(course)
                .url("udemy.com")
                .build();


        materialRepository.save(material);
        assertTrue(materialRepository.findAll().contains(material));
    }

    @Test
    void findAllPagination() {
        Pageable cuorsePagination = PageRequest.of(0, 2, Sort.by("title").and(Sort.by("credit").descending()));

        assertAll(
                () -> assertEquals(5, courseRepository.findAll(cuorsePagination).getTotalPages()),
                () -> assertEquals(10, courseRepository.findAll(cuorsePagination).getTotalElements()),
                () -> assertEquals(2L, courseRepository.findAll().stream()
                        .filter(c -> c.getTitle().contains("Jav"))
                        .count())
        );


    }
}