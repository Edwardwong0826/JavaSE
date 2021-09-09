package com.style;

import com.life.Jeans;

import java.sql.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        System.out.println(new Object());

        boolean test = false;

        Shirt Shirt = new Shirt(10, 250, "T-Shirt", "Cool");
        Shirt Shirt1 = new Shirt(10, 250, "T-Shirt");
        Trousers tourse1 = new Trousers(15,250,"long-sheeve",43);
        Clothing clo1 = new Trousers(15, 200,"cow-boy",40);
        Clothing clo2 = new Shirt(9,160,"Shirt","Formal");

        // operator precedence
        // 1. parentheses, ++ or -- operators ,multiplication or division evaluated from left to right
        // addition or subtraction operators evaluated from left to right
        int c = (((25 - 5) * 4 ) / (2 - 10))  + 4;
        System.out.println(c);

        // Surprisingly, the string and char can actually store the integer number by adding, but not assigning it
        String str1 = "";
        str1 += 2;
        System.out.println("\nPrinting String variable add int: " + str1);
        System.out.println("\n");

        int x1 = 10;
        char c1 = ((char) 10);
        char c2 = 2;
        x1 = c2;

        System.out.println(c2 + "--------------------");
        System.out.println(x1 + "--------------------");

        Shirt.setPrice(300);

        System.out.println(Shirt);
        System.out.println(Shirt1);
        System.out.println(tourse1.getCountry());

        interfaceReturn(clo1);

        List<Shirt> shirts = new ArrayList<>();

        Brand b1 = new Brand("LV");
        shirts.add(Shirt);
        shirts.add(Shirt1);

        // Called the static method within the same class can put the class name. or without also can,
        // but not for other class static method
        try {
            System.out.println("\n" + Main.countQuantity(shirts));
        }catch (Exception e)
        {
            System.out.println(e);
        }


        // this will hit java.lang.ArithmeticException: / by zero runtime exception
        // double result1 = divide(15, 0);
        try {
            double result = divide(15, 0);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Cannot divide by zero");
        }

        // System.out.println(countQuantity2(shirts));

        produceExtendsConsumerSuper();
        System.out.println(Returnable.computeAlcoholPercent());

    }

    public static void interfaceReturn(Clothing clo1)
    {
        Returnable r1 = new Shirt(11, 300, "T-Shirt");
        List<Returnable> returnableList = new ArrayList<>();
        returnableList.add(r1);

        System.out.println("\n");

        // By default, in the r1 interface reference object List, will only can get the interface abstract methods, if want the
        // instance methods when inside the interface List, need to casting down back the list object
        for(Returnable r: returnableList){

            System.out.println(((Shirt)r).getPattern());

        }

        // If Returnable object instantiate the Shirt object, if can directly get the Shirt object
        // because at run time it was already known is the Shirt object
        System.out.println("Returnable with Shirt casting: "+ ((Shirt) r1).getPattern());

        // Noted this is not super class toString method, clo1 have overrides the toString within own toString method,
        // therefore in this case the polymorphism is invoked, ability of an object to take on many forms.
        clo1.toString();

        // left side should be object and right side should be class
        if(clo1 instanceof Trousers){
            System.out.println(((Trousers) clo1).getCountry());

        }
        else
        {
            System.out.println("This object is not a trousers object, not casting");
        }
        // called interface method
        r1.doReturn();
    }

    public static double divide(int x, int y) throws Exception
    {

        return x / y;

    }

    // < ? extends xxxx> is to let method parameter able to accepts the parameter subclass in collection as well
    public static <T> List<? extends Clothing> countQuantity(List<? extends Clothing> inClothing) throws Exception
    {
        int val=0;
        for(Clothing c1: inClothing){
            val+=c1.returnQuantity();

            System.out.println(((Shirt)c1).getPattern());

        }

        ArrayList<Clothing> returnCloths = new ArrayList<>();
        returnCloths.addAll(inClothing);
        System.out.println("? -------------------------------------------------");
        return returnCloths;
    }

    public static int countQuantity2(List<Clothing> inClothing) throws Exception {
        int val=0;
        for(Clothing c1: inClothing){
            val+=c1.returnQuantity();
        }

        return val;

    }

    public static void produceExtendsConsumerSuper()
    {
        // use extends wildcard get the object, we cannot add anything into < ? extends T> because we cannot guarantee
        // what kind of object will point at, and only can get or read T because any list that could assign will only
        // be T or subclass of T
        List<? extends Clothing> clothsExtendList;
        ArrayList shirtList = new ArrayList<>();
        shirtList.add(new Shirt(10,60.00,"Shirt"));

        clothsExtendList = shirtList;

        Clothing returnObj = clothsExtendList.get(0);
        System.out.println(returnObj);


        // use super wildcard to add the object, we can only add T or subclass of T into <? super T> because when
        // new ArrayList<Y>(), Y cloud be same as/point at <? super T>, which the super class of T cannot add into subclass Y
        // and guarantee only get instance of object or subclass of object
        List<? super Brand> clothsSuperList;

        clothsSuperList = new ArrayList<>();
        clothsSuperList.add(new Brand("brand"));
        clothsSuperList.add(new Clothing(10,50.00));
        clothsSuperList.add(new Shirt(10, 60.00,"T-shirt"));
        clothsSuperList.add(new Trousers());
        System.out.println(clothsSuperList.get(0));

    }


}
