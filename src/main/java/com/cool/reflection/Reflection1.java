package com.cool.reflection;

import com.feature.ArrayAnnotation;

// feature of reflection
// in runtime determine any object its class
// in runtime construct any class object
// in runtime determine a class fields and methods
// in runtime get generic information
// in runtime call any object fields and method
// in runtime handle annotation
// generate dynamic proxy
public class Reflection1{

    public static void main(String[] args) throws ClassNotFoundException {

        // use reflection to get class object in JVM
        // a class will only have one class object in JVM
        // JVM in metaspace construct class template object, according this template object instantiate one class instance in heap
        // once a class being loaded, entire class structure will 封装in class object
        // in object monitor, the Klasspointer will point to class object that belongs

        // method 1: through object to get class object
        Person person = new Student();
        Class c1 = person.getClass();
        System.out.println(c1.hashCode());

        //method 2: use forName to get class object
        Class c2 =  Class.forName("com.cool.reflection.Student");
        System.out.println(c2.hashCode());

        // method 3: use <class name>.class to get class object
        Class c3 = Student.class;
        System.out.println(c3.hashCode());

        //method 4 : primitive internal wrapper class have one Type field
        Class c4 = Integer.TYPE;
        System.out.println(c4);

        // get parent class type
        Class c5 = c1.getSuperclass();
        System.out.println(c5);
    }

}

class User {

    private String name;
    private int id;
    private int age;

    public User() {
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ArrayAnnotation
    public void testMethodAnnotation(){

    }

    @Override
    public String toString() {
        return "Reflection{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}

@ArrayAnnotation
class Person{
    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person{
    public Student() {
        this.name = "student";
    }
}

class Teacher extends Person{
    public Teacher() {
        this.name = "teacher";
    }
}
