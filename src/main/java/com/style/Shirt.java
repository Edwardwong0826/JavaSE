package com.style;

import java.util.ArrayList;
import java.util.List;

public class Shirt extends Clothing {
    
    private String pattern = "No Pattern";

    public Shirt(int size, double price, String type, String pattern){
        super(size, price, type);
        this.setPattern(pattern);
    }

    public Shirt(int size, double price, String type){
        super(size, price, type);
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public ArrayList<? extends Shirt > getList(int id) {
            return null;
    }


    @Override
    public ArrayList<? super Brand> getList2(int id){
        return null;
    }


    @Override
    public String toString(){
        return super.toString() + " and with pattern of " + this.pattern;

    }

    @Override
    public void doReturn() {
        System.out.println("Returning the Shirt to shop...");
    }
}
