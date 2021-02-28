package com.cool;

import com.feature.Annotation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// we need to declared the element value as inside this annotation got specifying element
// () are not required only when only if no values are included, use comma to separate element value
@Annotation (hoursPerDay = 3, startHour = 1) // this annotation with required value and optional value
public class Superclass {

    static boolean s = false;
    static int ints;
    int price = 1 ;
    // final variable must initialized a value before use, either in static/initialize or constructor
    final static int finalVar;

    // final references can call methods, just cannot assign to other object
    private static final ArrayList<Integer> values = new ArrayList<>();

    public Superclass(){
        System.out.println("4");


    }

    { System.out.print("2"); }

    static
    {
        finalVar = 100;
        System.out.print("1");
    }

    public Superclass(long shadow)
    {
        super();

    }

    private void p1()
    {

    }

    // static method cannot be override, only can be hidden static method
    // subclass extends the superclass can directly called the superclass method by just method name
    public static void s1()
    {
        System.out.println("Superclass s1 static method");
    }

    public int getHeight() { return 3; }

    public List s1(Subclass s)
    {

       return null;
    }

    public final int s2(){
        return 0;
    }

    protected void printLong(Long input)
    {

        System.out.println("Superclass overloaded method: " + input);
    }

    // super class method that is being overrides called overridden method
    void print(int input) throws IOException
    {
        System.out.println("Superclass overriden method: " + input);

    }

    public void printInfo(){

        // this getHeight() is overridden in Subclass , even here it seems like calling it owns class getHeight() ,
        // the getHeight has been replaced to subclass getHeight() during runtime as polymorphism
        System.out.println(getHeight());
        System.out.println(this.getHeight()); // adding this.xxxx does not affect

    }

    public static void main(String[] args) throws IOException {
        Superclass s = new Superclass();
        s.printInfo();
    }

}
