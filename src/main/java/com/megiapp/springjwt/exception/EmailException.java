package com.megiapp.springjwt.exception;

public class EmailException extends RuntimeException {

    public EmailException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
