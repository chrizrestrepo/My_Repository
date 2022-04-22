package org.crestrepo.appmockito.study.Repository;

import org.crestrepo.appmockito.study.Data;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuestionRepositoryImpl implements QuestionRepository{
    @Override
    public List<String> findQuestionByExamId(Long id) {
        System.out.println("QuestionRepositoryImpl.findQuestionByExamId()");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Data.QUESTIONS;
    }

    @Override
    public void saveQuestions(List<String> listquestions) {
        System.out.println("QuestionRepositoryImpl.saveQuestions()");
    }
}
