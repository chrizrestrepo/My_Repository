package com.crestrepo.study.spring.data.jpa.course.repository;

import com.crestrepo.study.spring.data.jpa.course.model.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
}
