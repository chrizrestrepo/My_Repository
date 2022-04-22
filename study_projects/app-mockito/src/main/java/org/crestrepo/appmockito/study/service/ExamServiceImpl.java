package org.crestrepo.appmockito.study.service;

import org.crestrepo.appmockito.study.Repository.ExamRepository;
import org.crestrepo.appmockito.study.Repository.QuestionRepository;
import org.crestrepo.appmockito.study.model.Exam;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ExamServiceImpl implements ExamService{

    private ExamRepository examRepository;
    private QuestionRepository questionRepository;

    public ExamServiceImpl(ExamRepository examRepository, QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Exam findByName(String name) {
        return examRepository.findAll().stream()
                .filter(exam -> exam.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("the element doesn't exist"));
    }

    @Override
    public Exam findQuestionsByExamName(String name){
        Optional<Exam> examOptional = Optional.of(findByName(name));
        Exam exam = null;
        if(examOptional.isPresent()){
            exam = examOptional.orElseThrow();
            List<String> questions = questionRepository.findQuestionByExamId(exam.getId());
            exam.setQuestions(questions);
        }
        return exam;
    }

    @Override
    public Exam save(Exam exam) {
        if(!exam.getQuestions().isEmpty()){
            questionRepository.saveQuestions(exam.getQuestions());
        }
        return examRepository.save(exam);
    }

}
