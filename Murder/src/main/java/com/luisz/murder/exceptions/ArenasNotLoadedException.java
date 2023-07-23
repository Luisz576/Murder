package com.luisz.murder.exceptions;

public class ArenasNotLoadedException extends Exception{
    public final String message;
    public ArenasNotLoadedException(String message){
        this.message = message;
    }
}