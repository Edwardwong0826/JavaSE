package com.style;

import java.io.FileNotFoundException;

public class Trousers extends Clothing {

    private int inch = 0;
    protected static int testProVar = 0;
    protected static int lol = 101;
    int def = 0;

    static
    {
        System.out.println("Trousers initializer running ");
    }

    public Trousers(){}

    public Trousers(int size, double price, String type, int inch) {
        super(size, price, type);
        this.setInch(inch);
    }

    protected static void printStr(){};

    public int getInch() {
        return inch;
    }

    public String getCountry(){
        return "No country";
    }

    public int getNumber( int a){
        return 2;
    }

    public void setInch(int inch) {
        this.inch = inch;
    }

    public int getCode(){ return 2;}


    @Override
    public Integer m1() {
        return null;
    }


    // If the super class overridden method declared the throws clause , the overriding can choose to declared same throws clause
    // or without as well
    @Override
    protected int returnQuantity()  throws NullPointerException {
        return 0;
    }

    @Override
    public void doReturn() {
            System.out.println("Returning the Trousers to shop...");
    }

    @Override
    public String toString(){
        return super.toString() + " with inch: " + this.inch;
    }

    public static int testStatic = 12;
}
