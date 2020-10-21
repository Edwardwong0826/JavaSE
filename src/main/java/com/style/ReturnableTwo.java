package com.style;

@FunctionalInterface
public interface ReturnableTwo {

    // if there is other interface extends form this functional Interface, and do not declare any
    // other abstract method, that extended interface will be count as functional interface as well

    // same signature method with different return type in concrete class need to have to have covariant return type
    Number m1();

    public default int getNum(){ // only public access modifier
        count();
        return 500;
    }

    public default int getQuote() // the latest subtype interface default method will get choose
    {
        return 100;
    }

    // making private method and this intends to use in multiple default method to reduce duplication for same code
    private void count()
    {

    };


}
