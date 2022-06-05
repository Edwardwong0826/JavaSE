package com.cool.reflection;

public class Reflection5 {
    public static void main(String[] args) throws ClassNotFoundException {

        // get system class loader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // get system class loader parent loader -> extension class loader
        ClassLoader parent = systemClassLoader.getParent(); // after jdk 9, extension class loader have change name to platform class loader
        System.out.println(parent);

        // get system class loader parent loader -> bootstrap class loader(c/c++)
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        // test current class was loaded by which class loader
        ClassLoader classLoader = Class.forName("com.cool.reflection.Reflection5").getClassLoader();
        System.out.println(classLoader);

        classLoader =  Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

        System.out.println(System.getProperty("java.class.path"));

        // in java there is parents delegate, to ensure will load the core class exists in java core jar file first, even user self defined same name class file as java core class name
        // example java.lang.String
    }
}
