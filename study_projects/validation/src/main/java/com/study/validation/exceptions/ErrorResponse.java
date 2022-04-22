package com.study.validation.exceptions;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ErrorResponse {

    private int status;
    private String message;
    private LocalDate date;
    private List<String> errors;
}
