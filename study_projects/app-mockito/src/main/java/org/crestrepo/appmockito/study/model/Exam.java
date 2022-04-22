package org.crestrepo.appmockito.study.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Exam {
    private long id;
    private String name;
    private List<String> questions;

    public Exam(long id, String name) {
        this.id = id;
        this.name = name;
        this.questions = new ArrayList<>();
    }
}
