package com.cool.reflection;

public class Reflection3 {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);
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

    public A() {
        System.out.println("class a no args constructor init");
    }

}
