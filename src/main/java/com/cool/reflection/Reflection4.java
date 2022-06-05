package com.cool.reflection;

public class Reflection4 {

    static{
        System.out.println("main class loaded");
    }

    public static void main(String[] args) throws ClassNotFoundException {

        // method that generate class reference - will happen class init
        //1. active reference主动引用
        //Child child = new Child();

        //2. reflection also will cause 主动引用
        //Class c1 = Class.forName("com.cool.reflection.Child");

        // method that not generate class reference - will not happen class init
        // this is because in the jvm class loader linking phase, the static and final variable already being init in metaspace
        Class<Child> childClass = Child.class;

        System.out.println(Child.b);

        System.out.println(Child.M);

    }
}

class Parent{
    static int b = 2;
    static{
        System.out.println("parent class loaded");
    }
}

class Child extends Parent{
    static {
        System.out.println("child class loaded");
        m = 300;
    }

    static int m = 100;
    static final int M = 1;
}
