package com.NIO2;

import java.io.Serializable;

public class Animal implements Serializable {

    // static variable will lost value during Serializable
    // if the serialize class other class reference, then other class must implements Serializable as well
    // is was recommend to maintain serialVersionUID in our serialize class
    // Serializable interface is to mark the class is proper ready and don't have any methods
    // Java only calls the constructor of the first non-serializable parent class, which is Object class

    private static final long serialVersionUID = 1L; // static variable remains was set last in Serialization
    private String name;
    private int age;
    private transient int num;
    // transient marked will skipped during Serialization, and will reverts to default type value in deserialization

    // instance initialize would be skipped for Serialization
    {
        System.out.println(this.age);
    }

    public Animal(String inName, int inAge, int num)
    {
        this.name = inName;
        this.age = inAge;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Animal [" +
                "name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", num=" + num +
                ']';
    }


}
