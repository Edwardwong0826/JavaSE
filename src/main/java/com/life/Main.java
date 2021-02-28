package com.life;
import com.style.Brand;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class Main {


    public static void main(String[] args) {

        // Chaining to use a value of a variable at the time of declaration is not allowed, only allowed if other been declared
        int a = 100, b, c;
        a = b = c = 100;


        System.out.println(100l / 9.9);
        System.out.println(100 / 10.0);
        System.out.println("\n");

        Brand brand1 = new Brand();
        Jeans jeans1 = new Jeans();
        System.out.println(brand1);
        System.out.println(jeans1);

        Jeans j1 = new Jeans(15, 250, "long-sheeve", 43, 15.00);
        Jeans j2 = new Jeans(15, 250, "long-sheeve", 43, 15.00);
        Jeans j3 = new Jeans(16.00);
        // the static variable will be initialize by the static initializer when the class loader is invoked
        System.out.println(j1);
        System.out.println(Jeans.testInt);
        System.out.println(Jeans.info);
        System.out.println(new Jeans());
        System.out.println(j3);

        Jeans.jeansSales();
        System.out.println();


        System.out.println("\n");
        sublist();

        // is ok to call the function without parameter, because it were declared to take variable number of arguments,
        someMethod();
        arrays();

        //Overloading Widening
        byte test = 0;
        m2(test);


        //Overloading Reference
        m3(50.0);  // why this goes to Object? when this method tries to find the double parameter method, when it can't find one,
        // it will autobox to Double wrapper class and keep looking, since it still doesn't find one, it will go to Object Class parameter
        // Every class is inherit Object, that why Double Wrapper class can go to this Object class
        m3(100);  // why this is error? Same as above, just that after Autoboxing to Integer Class, Integer cannot fit with either
        // Double or Long Wrapper Class, it only can go either exact class or super class, Integer is not extends Double but Number



    }


    enum Flavors{

        FRUIT, BERRIES{public boolean isHealthy(){return true;}};

    }

    public static void sublist() {

        List s1 = new ArrayList();
        s1.add("a");
        s1.add("b");
        s1.add(1, "c");
        List s2 = new ArrayList(s1.subList(1, 1));
        s1.addAll(s2);
        System.out.println("\n" + s1);

    }


    public static void m1() throws IndexOutOfBoundsException {

        System.out.println("m1 Starts");
        throw new IndexOutOfBoundsException("Big Bang ");

    }

    public static void testPrimitiveDataType(Map events, int j, int k) {
        byte b = 1;
        char c = 'a';
        short s = 1;
        int i = 1;
        float f = 1.0f;
        double d = 1.0;
        Long l = 1l;

        String store = "";
        store += j;
        System.out.println("\n" + store);

        Collection Collect = events.values();

    }

    public static void arrays() {
        // Noted array 1, is just array of int, but the others 2,3,4,5 ihe arrays of arrays int
        // int[] i , j vs int i[], j, the first case all is array, the second case only i is array
        int[] array1, array2[];
        int[][] array3;
        int[] array4[], array5[];

        // the array size only valid in Right Hand Size not LHS, and if give the elements can't the size.
        int[] array6 = new int[10];
        int[] array7 = {1,2,3,4,5};

        // we can't specify the array size and element at the same time in declaration, either one only
        //int[] array7 = new int[1] {1,2};

        // var is not allowed as an element type of an array, should be only var v ... only, an array initializer must
        // explicit target-type if the variable is declared using var declaration. With out it, the compiler can't deduce the
        // type of array of var

        var v = new int [][] {{1,2,3},{4,5,6}};
        // var v [][] .... is not valid

        // array is allow to specify the first dimensional size first, later can specify other dimensional size
        var v1 = new int [1][2][];

        // always remember, after array initialization , need to remember when access the array index is start 0,
        // thus array of [2][2] largest index will be up to [1][1] accessible only
        double[][] daa = new double[2][2];
        daa[1][1] = 200;

        // this will be ArrayList<Object> if no type is put in <>  and does compile
        var list = new ArrayList<>();
        // sort number > letters > uppercase > lowercase
        String[] strings = {"10","9","100"};
        Arrays.sort(strings);

    }

    public static void someMethod(Object... params){ System.out.println(params.length); }

    // 1. When primitives overloading , Exact Match > Widening > Autoboxing > Varargs
    // 2. When Reference Object overloading, Specific class > General class
    // 3. if primitive after autoboxing does not find match type, if can go to object
    public static void m2(int i) { System.out.println("Widening to int"); }

    public static void m2(Integer i) {
        System.out.println("Boxing to Integer wrapper type");
    }

    public static void m2(double d) {
        System.out.println("Widening to double");
    }

    public static void m2(int... i) { System.out.println("Varargs to int  "); }

    //public static void m3(Integer s) { System.out.println("Boxing to Integer"); }

    //public static void m3(Long s) { System.out.println("Boxing to Long"); }

    public static void m3(Double s) { System.out.println("Boxing to Double "); }

    public static void m3(Number s) { System.out.println("Go to Number "); }

    public static void m3(String s) { System.out.println("String"); }

    public static void m3(Object o) { System.out.println("Object"); }

    public static void m3(Double... d) { System.out.println("Varargs to double"); }

    public void varags(int[] lengths){ } // Java treats varags and normal array as same instead of overloading, it will hits error
    //public void varags(int... lengths){}

    public void varags(int i, int...lengths){} // varargs parameter must be in last position and only one varargs, else compile error

    // Method optional specifiers order does not matter, but must be like <optional specifiers>,... <return type> <method name> (){};
    public static final void walk(){};
    public final static void walk1(){};
    final static public void walk2(){};

    public static int howMany(boolean b, boolean... b2) {
        return b2.length;
    }

    // 1 BC,2 AD,3 ACD,4 ABF,5 DF,6 D,7 BCDF,8 ABE,9 BCD,10 D,11 BE,12 D,13 B,14 D,15 E,16 B,17 BDE,18 BCE,19 AE, 20 ACF, 21 ABC
    // 1 E,2 BC, 3F, 4C, 5A, 6BE, 7AC, 8C, 9 BF, 10D, 11BCE, 12ABEF, 13AG, 14BC, 15D, 16B, 17F, 18CF, 19CF, 20F, 21E, 22D, 23B, 24ADF
    // 182943 25 B,

}


