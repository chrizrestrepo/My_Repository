package com.study.validation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleException(IllegalArgumentException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setMessage(e.getMessage());
        response.setDate(LocalDate.now());
        response.setErrors(Arrays.asList(status.getReasonPhrase()));
        return new ResponseEntity<ErrorResponse>(response, status);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ErrorResponse> handleException(NoSuchElementException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildResponseEntity(httpStatus, e, Arrays.asList(httpStatus.getReasonPhrase()));
    }

    @ExceptionHandler(InvalidDataException.class)
    protected ResponseEntity<ErrorResponse> handleException(InvalidDataException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> response = e.getResult()
                .getFieldErrors()
                .stream()
                .map(error -> "the field " + error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return buildResponseEntity(httpStatus, new RuntimeException("invalid data"), response);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(HttpStatus httpStatus, Exception exp, List<String> erros){
        ErrorResponse response = new ErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(exp.getMessage());
        response.setDate(LocalDate.now());
        response.setErrors(erros);
        return new ResponseEntity<>(response, httpStatus);
    }
}
