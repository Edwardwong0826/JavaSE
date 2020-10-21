package com.style;

import com.feature.Annotation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Annotation(hoursPerDay = 3, startHour = 1)
public class Clothing extends Brand implements Returnable, ReturnableTwo{

    private int size;
    private double price;
    private String type;
    int age;

    public  Clothing(){}

    // In Constructor if got throws Exception, the inherit class constructor only valid for throws superclass of this exception
    // or same exeception, and can declared other exception as well
    public Clothing(int size, double price, String type) // throws FileNotFoundException
    {
        super("LV");
        this.setSize(size);
        this.setPrice(price);
        this.setType(type);


    }
    // Overloading constructor based in passed in parameters when instantiate the object
    public Clothing(int size, double price)
    {
        super("LV");
        this.setSize(size);
        this.setPrice(price);
        this.type = "Overloaded constructor choose";
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // method with no access modifier , only accesible within package
    // static method need to have method body
    // The overriding method cannot throw any checked exception other than what the overridden method throws
    int returnQuantity()  throws RuntimeException {
        int v=1;
        return v;
    }


    public List<? extends Clothing> getList(int id){
        return null;
    };

    public List<? super Clothing> getList2(int id){
        return null;
    }


    @Override
    public String toString() {
        return "This size is about size of: " + this.size + " with price of : " + this.price + "and is a " +this.type+ " type";
    }


    @Override
    public void doReturn() {

    }

    // The doclean() exists in Returnable and Cleanble interface, but will only implements the returnable one as there
    // two method same name and same arguements will be treats as one method
    @Override
    public void doClean() {

    }

    // when a class implements two interface have same signature conflict default method,
    // the compiiler will treats as abstract method
    @Override
    public int getNum() {
        return 0;
    }

    @Override
    public ArrayList<? extends Clothing> returnOfList(){
        return new ArrayList<Clothing>();
    }

    // List<? super Number> will be subtype of List<? super Integer>, the more restrictive class will be subtype
    // Opposite with extends wilcards
    @Override
    public  List<? super Number> returnOfNumber() {
        return null;
    }

    public ArrayList<Number> returnNumber(){
        return null;
    };

    @Override
    public <T> Collection<T> transform(Collection<T> list) { return new HashSet<T>(); };


    // Notice the override annotation does not force to be write it when overrides, the annotation is make
    // sure the compiler go to check the overriden method
    public Trousers returnCloths(){
        return new Trousers();
    };

    // This is from the Cleanble interface, although is the same name m1() as in Returnable interface,
    // but notice there are two different method because the method arugments is different, so the class must implements this both
    @Override
    public void m1(int i) {

    }
    @Override
    public Integer m1() {
        return null;
    }

    @Override
    public int getQuote(){
        // use interfaceName.super.xxx() can called the interface method
        // if the class implement mulitply interface have same default method signature
        // interfaceName.super.xxx() also can call default method on which interface we want
        return Returnable.super.getQuote();
    }

    public static void main(String[] args){

        // superclass method takes priority over interface default method when calling
        // when no superclass method only choose the interface default method
        Clothing c = new Clothing();
        System.out.println(c.getQuote());
    }

}





