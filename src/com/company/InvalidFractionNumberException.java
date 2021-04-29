package com.company;

public class InvalidFractionNumberException extends Exception{

    InvalidFractionNumberException(){
        super("Invalid number for a fraction!");
    }

    InvalidFractionNumberException(String message){
        super(message);
    }
}
