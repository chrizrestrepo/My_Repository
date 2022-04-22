package org.crestrepo.junitapp.ejemplo.exception;

public class InsufficientAmmount extends RuntimeException{

    public InsufficientAmmount(String message){
        super(message);
    }
}