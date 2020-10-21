package com.cool;

public class inValidLengthException extends java.lang.Exception {

    private int length;



    public inValidLengthException(){

    }

    public inValidLengthException(String message){

        super(message);
    }

    public inValidLengthException(String message, Throwable cause){

        super(message, cause);

    }

    public inValidLengthException(String message, int length){

        this(message);
        setLength(length);

    }

    public int getLength() {
        return length;
    }

    public void setLength(int amount) {
        this.length = amount;
    }



}
