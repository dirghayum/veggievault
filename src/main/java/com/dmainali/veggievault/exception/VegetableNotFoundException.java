package com.dmainali.veggievault.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.ConnectException;

public class VegetableNotFoundException extends RuntimeException{

    Throwable t;
    String message;
    Exception e;

    public VegetableNotFoundException(Throwable t){
        this.t = t;
    }

    public VegetableNotFoundException(String message, Exception e){
        super(message);
        this.message = message;
    }

    public VegetableNotFoundException(String message, JsonProcessingException e) {
        super(message);
        this.message = message;
    }

}
