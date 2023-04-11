package com.dmainali.veggievault.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.ConnectException;

public class VeggieException extends RuntimeException{

    Throwable t;
    String message;
    Exception e;

    public VeggieException(Throwable t){
        this.t = t;
    }

    public VeggieException(String message, Exception e){
        super(message);
        this.message = message;
        this.e = e;
    }

}
