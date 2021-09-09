package com.style;

import java.util.ArrayList;
import java.util.List;

public class Shirt extends Clothing {
    
    private String pattern = "No Pattern";

    public Shirt(int size, double price, String type, String pattern){
        super(size, price, type);
        this.setPattern(pattern);
    }

    public Shirt(int size, double price, String type){
        super(size, price, type);
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public ArrayList<? extends Shirt > getList(int id) {
            return null;
    }


    @Override
    public ArrayList<? super Brand> getList2(int id){
        return null;
    }


    @Override
    public String toString(){
        return super.toString() + " and with pattern of " + this.pattern;

    }

    @Override
    public void doReturn() {
        System.out.println("Returning the Shirt to shop...");
    }

    // the default hashCode from Object class will generate unique hashcode even if two object is equals() or override equals already
    // we need to either override two hashCode() and equals() or don't override two at all, because if two objects when equals,
    // the hash codes also should be same, below using 17 and 31 hash code way
    @Override
    public int hashCode() {
        int result=17;
        result=31*result+age;
        result=31*result+(pattern!=null ? pattern.hashCode():0);
        return result;
    }

    // if we override the equals we also need to override the hashcode as well, this is to ensure in the hash-based collection like
    // HashMap, HashTable, HashSet those can function properly
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Shirt))
            return false;
        Shirt shirt = (Shirt) obj;
        return shirt.getSize() == this.getSize()
                && shirt.getPrice() == this.getPrice()
                && shirt.getName().equals(this.getName()) && shirt.getPattern().equals(this.getPattern()) && shirt.getType().equals(this.getType());
    }
}
