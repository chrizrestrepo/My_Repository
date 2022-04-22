package org.crestrepo.appmockito.study.Repository;

import java.util.List;

public interface QuestionRepository {
    List<String> findQuestionByExamId(Long id);
    void saveQuestions(List<String> listquestions);
}
