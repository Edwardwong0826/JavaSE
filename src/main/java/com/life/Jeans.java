package com.life;

import com.style.ReturnableTwo;
import com.style.Trousers;
import com.cool.inValidLengthException;

import java.util.ArrayList;
import java.util.Scanner;


public class Jeans extends Trousers implements Comparable<Jeans> {


    // Encapsulation
    // instance variables should private, getter and setter need to be public, other methods can be any access modifier

    // static and instance variables are automatically assigned the default value according each type and
    // can be left with out initialized
    public static int testInt;
    private double length;
    public static float flt; // the float default value is 0.0
    public static char c; // the char default value is blank
    static final ArrayList<String> values = new ArrayList<>(); // final is make we don't change the value or reassign point to different object, but are allowed to call method on reference methods
    private EnumTest enumtest;



    // static or non-static final variable must be explicitly be initialized before use
    // Remember  final can only set once in initializer or at declaration
    static final String info;
    final String feature; // instance latest need initialized on constructor, if there is multiply overloaded constructor,
    // then every constructor need set the final instance variable else will compile error


    // this static initializer will be called one time when the class loaded and first time use,
    // example when first time instance is created or called first time that class static method
    // this can be set the final or static variables value
    static
    {
        System.out.println("static initializer for");
        System.out.println("In Jeans");
        testInt = 3;
        info = "Mis leading";
        System.out.println();

    }

    // this instance initializer works for instance field only
    {
        feature = "Non";
    }

    public Jeans(){}

    public Jeans(double Inlength)
    {

        length = Inlength;

    }


    public Jeans(EnumTest enumTest){

        this.enumtest = enumTest;

    }

    public Jeans(int size, double price, String type, int inch, double length)
    {

        super(size, price, type, inch);
        this.length = length;
        //length = length;
        // without this. keyword, the local variable same name variable will shadow to instance variable
        // it will refers to local variable itself instead of instance field
        //length = length;

    }


    public double getLength() {
        return length;
    }

    public static boolean compare(double d1){
        return d1  > 100;
    }

    public static boolean compare(double d1, double d2){
        return d1 + d2 > 100;
    }

    public void printLength(){ System.out.println(this.length); }

    public void setLength(double length) throws inValidLengthException {


        if(length > 100 ){
                throw new inValidLengthException();
        }
        else{
            this.length = length;
        }
    }

    public static void jeansSales(){ System.out.println(testInt);}

    public int getNumber( int a, char ch){
        return 4;
    }

    public int getCode(){ return 3;}

    public EnumTest getEnumtest() {
        return enumtest;
    }

    public void setEnumtest(EnumTest enumtest) {
        this.enumtest = enumtest;
    }


    protected void getProtectedMethodAndField(){

        returnQuantity();
        System.out.println(testProVar);

        Jeans jean = new Jeans();
        jean.returnQuantity();
        System.out.println(jean.testProVar);

        Trousers tro = new Trousers();
        //tro.returnQuantity(); // Protected access only allowed same class, package and subclass that inherit can use it, even we declared the reference directly here
        //Trousers.testProVar; // same as above


        Trousers jean1 = new Jeans();
        //jean1.returnQuantity(); // Even the instance is the Jeans, but the reference is Trousers, we are not allowed to access since we are not in the same package and the reference type is not a subclass
        //Trousers.testProVar; // same as above


    }

    public static void accessingStatic()  {

        Jeans jean = new Jeans();
        Jeans jean1 = null;

        // we can use class to called the static field or use instance object to called static field
        System.out.println(jean.testProVar);
        System.out.println(Trousers.testProVar);
        System.out.println(jean1.testProVar); // even the instance is null, the compiler doesn't care for calling static field
        values.add("haha");


        System.out.println(lol);
        printStr();

    }


     static void defaultMethod(){}; // without any access modifier keyword will be default, below is wrong and will have compile error
    //default static void defaultMethod(){} // default keyword only allows in interface default method declaration

    @Override
    public int compareTo(Jeans o) {
        return 0;
    }


    @Override
    public String toString() {
        return super.toString() + " Jeans " +
                "length=" + length + " with " + testInt;

    }
    // Visibility , optional modifier, return type, method signature = name and parameters
    public static void main(String[] args)  {

        Jeans j = new Jeans(EnumTest.KL);
        System.out.println(j.getEnumtest().getServiceLevel());
        try {
            j.setLength(99);
        } catch (inValidLengthException e) {
            e.printStackTrace();
        }




    }


}
