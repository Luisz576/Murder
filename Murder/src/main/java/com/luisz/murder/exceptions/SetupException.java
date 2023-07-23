package com.luisz.murder.exceptions;

public class SetupException extends Exception{
    public final String reason;
    public SetupException(String reason){
        this.reason = reason;
    }
}