package com.megiapp.springjwt.exception;

public class InvalidRequestsParameters extends Throwable {
    public InvalidRequestsParameters(){

    }

    public InvalidRequestsParameters(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
