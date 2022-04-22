package org.crestrepo.appmockito.study.service;

import org.crestrepo.appmockito.study.model.Exam;

public interface ExamService {
    Exam findByName(String name);
    Exam findQuestionsByExamName(String name);
    Exam save(Exam exam);

}
