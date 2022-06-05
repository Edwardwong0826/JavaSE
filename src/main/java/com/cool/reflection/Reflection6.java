package com.cool.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// create object dynamically by reflection
public class Reflection6 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class c1 = Class.forName("com.cool.reflection.User");

        // it was using the class no args constructor
        // c1.newInstance(); this one only in jdk 8
        // after jdk 9 need to use below way
        User o = (User)c1.getConstructor().newInstance();
        System.out.println(o);

        // by using constructor create object
        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        User user = (User)declaredConstructor.newInstance("leon", 1, 20);
        System.out.println(user);

        // by using reflection to use normal method
        // need to use invoke to call the method by using reflection instance
        User user2 = (User)c1.getDeclaredConstructor().newInstance();
        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(user2,"Lee");
        System.out.println(user2.getName());

        // by using reflection operate fields
        User user3 = (User)c1.getDeclaredConstructor().newInstance();
        Field name = c1.getDeclaredField("name");

        // normally private member is not accessible by other class, but by using reflection
        // we can set the accessible to true to make it allow to access all access of members
        // this will disable security check, use wisely
        name.setAccessible(true);
        name.set(user3,"Lee2");
        System.out.println(user3.getName());


    }
}
