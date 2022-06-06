package com.cool.reflection;

import java.util.Date;

public class Reflection3 {


    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);
        System.out.println(a.testLocalVariable());
    }

    public void test(){
        double price = 130.5; // double and long use two slot in local variable table, other type and reference type use 1 slot
        Date date = new Date();
        System.out.println(date);
        System.out.println(price);

    }



}

class A{

    // first load to memory, generate a class object accordingly
    // static variable value init related to the order
    // static variable stored in metaspace, in linking phase prepare default value
    // and in init phase <clinit>() method assign the value given

    static{
        System.out.println("class a static initializer init");
        m = 300;
    }

    static int m = 300;

    private int num = 1;

    public A() {
        System.out.println("class a no args constructor init");
    }

    public int testLocalVariable(){
        this.num = 100;
        return num;
    }

}
