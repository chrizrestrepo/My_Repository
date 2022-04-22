package com.crestrepo.study.spring.data.jpa.course.repository;

import com.crestrepo.study.spring.data.jpa.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
