package com.exceptionHandling.springbootexceptionhandling.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String msg){
        super(msg);
    }
}
