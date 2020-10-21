package com.cool;

import java.util.List;

public abstract class AbstractTest {


    private String brand;
    int price;

    static { System.out.print("A"); }

    { System.out.print("B"); }

    // constructor rules apply in abstract class as well
    // the abstract class cannot be instantiated, but the its constructor called via its non abstract sub class
    public AbstractTest(){
        System.out.println();
        System.out.println("Constructor : ");
        a1(10);
    };

    public AbstractTest(String brand){ this.brand = brand; }
    
    private final void fly() { System.out.println("Bird"); }


    // Abstract method rules
    // 1. abstract method does not have method body{}
    // 2. abstract method cannot be private, because subclass cannot override that method doesn't exist
    // 3. abstract method cannot be final, final makes subclass cannot override or implementing it
    // 4. static method cannot be overridden, public abstract static int at1() does not compile
    // 5. abstract class must explicit add abstract modifier to become abstract method
    // 6. abstract method required ;
    protected abstract int at1();

    public List<CharSequence> play() { return null; }

    public static void test(){
        System.out.println("abstract test");
    }

    public void a1(int inInt){ System.out.println("abstractTest method " +inInt); };

    @Override
    public String toString() {
        return this.brand;
    }

}
