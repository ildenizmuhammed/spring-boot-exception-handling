package com.exceptionHandling.springbootexceptionhandling.exception;

public class NameAlreadyExistsException extends RuntimeException{
    public NameAlreadyExistsException(String msg){
        super(msg);
    }
}
