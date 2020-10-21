package com.cool;


import com.feature.Annotation;
import com.feature.ArrayAnnotation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;
//import static java.util.Arrays.*;

@Annotation("Yes") // this shorthand will convert to the long form with the value() element name only
// @Annotation({"Yes"}) // this four all are valid and same for the default value() with element array type
// @Annotation(value = "Yes")
// @Annotation(value = {"Yes"})
public class Subclass extends Superclass {

    private int key;
    static boolean s = true;
    int price = 0; // this is hiding variables , no matter is it static or not, java not allow variable override, when call it will based on refer type
    // hiding variables will be called based on reference types
    Object o;
    private static final List<Integer> values = asList(1,2,3);

    // the order of the static initializer and instance initializer
    // 1. static variable and initializer first and only one time per class loaded,
    // 2. super class instance variable , instance initializer and constructor every instance
    // 3. child class instance variable , instance initializer and constructor every instance
    public Subclass()
    {
        System.out.println(3);
    }

    { System.out.print("9"); }

    static { System.out.print("8"); }

    // JVM will generate no args argument constructor if no any constructor is defined. Got private constructor will counted as
    // explicitly as well, and will not generated no args constructor
    public Subclass(int key){

        // if we don't called any constructor of super class, Super() or This() will be implicit insert to constructor,
        // so need to make sure the super class contain no args constructor
        this.key = key;
        super.price = 100;

    }


    // this will not be consider overload, at run time, the JVM will remove generic type at runtime thus both method
    // are the same and not consider overriding
    // @Override
    // public void m1(List<Integer> input) {
    //}

    // this is redeclare private method instead of override
    private void p1()
    {

    }

    // Hiding static method
    // if super class got static method and we declare same static class here is not override but hiding static method
    // trying use instance to override either static method or use static method override instance method will get compile error
    public static void s1()
    {
        System.out.println("Subclass s1 static method");
    }

    // Object parameter can accepts any variables, included primitives when passed as parameter will autoboxing to wrapper class
    // wrapper class is a sub type of object as well, since Object is the root super of all type of class
    public void setObject(Object o ){
        this.o = o; // even though pass in object is null, the code will success compile
    }



    @Override
    public final int getHeight() { return 8; }

    // overriding method parameter must be the same, the return type can be covariant type
    // if method parameter is different type and @Override is not present, then will be consider overloaded only
    //@Override
    public ArrayList s1(Object o) throws IOException
    {
        return null;
    }


    // 1. sub class method that is overrides super class method called overriding method
    // 2. sub class overriding method cannot declared any new checked exception
    // 3. sub class overriding method can declared any runtime exception but not boarder check exception than super class
    @Override
    void print(int input) throws ArrayIndexOutOfBoundsException
    {
        System.out.println("Subclass overriding method: " + input);

    }

    @ArrayAnnotation(genres="EMD") // Annotation array value element may written without {} with single value
    @ArrayAnnotation(genres={"Classic"}) // multiple annotation
    @ArrayAnnotation(genres={"Vocal","Pop"})
    @SuppressWarnings("unchecked") //ignore warnings related to the use of raw types such as List instead List<String>
    void print(double input)
    {

        System.out.println("Subclass overloading method double: " + input);

    }

    @SuppressWarnings("deprecation") // ignore warnings related to types or method marked with @Deprecacted
    static void print(long input) // overloaded does not matter is it static or not
    {

        System.out.println("Subclass overloading static method long: " + input);

    }

    // @SafeVarargs only can be used in method contains varargs parameter, and not able to to be overridden
    // any method of static, private, final
    @SafeVarargs // Annotation with no value
    public static void safeMethod(List... inInt){

    }



    public static void main(String[] args) throws IOException{

        Superclass super1 = new Subclass();
        Subclass sub1 = (Subclass)super1; // Although this code will compile, but at run JVM will throw class cast exception as
        // if super1 instance is not actually the Subclass


        Superclass super2 = new Subclass(10); // this constructor called super.price to 100
        System.out.println();
        System.out.println(super2.price); // price will print 100 instead of 0, because is hiding variable by reference type
        System.out.println();
        super2.print((short)10);
        super2.print(20);
        super2.printLong(20l);
        super2.printLong(5L);
        //super2.printInt(50.0); // this hit error, because in parent class does not have overloaded double parameter

        // hiding variable
        System.out.println(super1.price); // instance variable is depending reference type is it,
        System.out.println(sub1.price); // and print that reference class variable
        super2.s1();
        s1();

        // we can use instance or class to called static variable or method
        super1.ints = 100;
        super2.ints = 200;
        Superclass.ints = 300;
        System.out.println("Superclass static ints: " + Superclass.ints);
        new Subclass().printInfo(); // all the overriding method has been replace at run time


    }
}
