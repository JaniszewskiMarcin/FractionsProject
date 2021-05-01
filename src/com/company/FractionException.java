package com.company;

public class FractionException extends Exception{

    FractionException(){
        super("Invalid number for a fraction!");
    }

    FractionException(String message){
        super(message);
    }
}
