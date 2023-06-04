package com.cool.reflection;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// all type of class that have class object
public class Reflection2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

        //typeOfClass();

        // get class name
        Class c1 = Class.forName("com.cool.reflection.User");
        System.out.println(c1.getName());
        System.out.println(c1.getSimpleName());

        // get class fields
        Field[] fields = c1.getFields(); // only can get public fields
        for (Field field : fields) {
            System.out.println(field);
        }

        Field[] declaredFields = c1.getDeclaredFields(); // can get all access fields
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        System.out.println("---------------------------");

        //get class methods
        Method[] methods = c1.getMethods();
        for (Method method : methods) { // can this class and its parent class all public method
            System.out.println(method);
        }

        System.out.println("----------------------------");

        methods = c1.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("-----------------------------");

        // get specified method
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName);
        System.out.println(setName);

        // get specified constructor
        System.out.println("-------------------------------");
        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        Constructor[] declaredConstructors = c1.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println("specified: " + declaredConstructor);
    }

    public static void typeOfClass(){
        Class c1 = Object.class; // class
        Class c2 = Comparable.class; // interface
        Class c3 = String[].class; // 1d array
        Class c4 = int[][].class; // 2d array
        Class c5 = Override.class; // annotation
        Class c6 = ElementType.class; // enum
        Class c7 = Integer.class; // primitive
        Class c8 = void.class; // void
        Class c9 = Class.class; // class

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);
        System.out.println();

    }
}
