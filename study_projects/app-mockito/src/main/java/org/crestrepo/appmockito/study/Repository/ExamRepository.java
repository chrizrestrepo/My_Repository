package org.crestrepo.appmockito.study.Repository;

import org.crestrepo.appmockito.study.model.Exam;

import java.util.List;

public interface ExamRepository {
    Exam save(Exam exam);
    List<Exam> findAll();
}
