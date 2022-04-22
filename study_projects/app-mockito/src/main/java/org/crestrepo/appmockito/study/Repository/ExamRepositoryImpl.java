package org.crestrepo.appmockito.study.Repository;

import org.crestrepo.appmockito.study.Data;
import org.crestrepo.appmockito.study.model.Exam;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamRepositoryImpl implements ExamRepository{

    @Override
    public Exam save(Exam exam) {
        System.out.println("examRepositoryImpl.save()");
        return Data.EXAM;
    }

    @Override
    public List<Exam> findAll() {
        System.out.println("examRepositoryImpl.findAll()");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Data.EXAMS;
    }
}
