package org.crestrepo.appmockito.study;

import lombok.experimental.UtilityClass;
import org.crestrepo.appmockito.study.model.Exam;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class Data {
    public static final List<Exam> EXAMS = Arrays.asList(new Exam(5L, "history"), new Exam(3L, "lenguages"), new Exam(10L, "Math"));
    public static final List<String> QUESTIONS = Arrays.asList("who was kennedy", "what is the capital of colombian", "when the last time that human visit the moon");
    public static final Exam EXAM = new Exam(40L, "marketing");
}
