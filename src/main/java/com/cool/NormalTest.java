package com.cool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// top level class can only be public or default
public class NormalTest extends AbstractTest {

    String name;
    int num;

    static { System.out.print("F"); }
    static int testStatic = 10;


    public NormalTest(String name, int num){

        this.name = name;
        this.num = num;

    }

    { System.out.print("H"); }


    //@Override
    //public int a2() { return 0; }

    // when overriding abstract method just follow normal rules
    @Override
    protected int at1() { return 0; }

    protected void fly() { System.out.println("Pelican"); }


    public ArrayList<CharSequence> play() {
        return null;
    }


    public static void test(){
        System.out.println("normal test");
    }


    // 1. The first concrete class extends abstract class must provide am implementation for the inherited abstract methods
    // 2. If the parent abstract class inherit an abstract class methods, and provides method implementation then the concrete class not required to override the method
    // 3. else the abstract class inherits abstract method will need to be implemented in concrete class
    @Override
    public void a1(int inInt)  {
        System.out.println("normalTest class method " +inInt);
    }

    // 1. the overloaded method that is not inherit or overridden from super class , the access modifier rules does not need to follow
    // 2. reference of this class will access overloaded method only, the super class could not access it

    void a1()throws IOException{
        System.out.println("normalTest class method");
    }

    @Override
    public String toString() { return this.name +" " +this.num; }

    public static void main(String[] args)  {
        new NormalTest("haha",1);
        System.out.println();
        System.out.println("haha");
        new NormalTest("haha",2);
        System.out.println();

        AbstractTest a1 = new NormalTest("haha",3);
        NormalTest a2 = new NormalTest("haha",4);

        System.out.println();

        // rules of instance of operator in interface
        // 1. The JVM check instance of with unrelated interface will not generate compile error as unrelated class
        // 2. the class that marked as final, will hits compile error as there is no chance to have subclass that implements that interface anymore
        if (a2 instanceof List){
            System.out.println("True");
        }
        else
        {
            System.out.println("False");
        }

    }

    // 1. inner class can be declared as public, protected, default, public and abstract , final as well
    public class normalTestInner{
    }


}
