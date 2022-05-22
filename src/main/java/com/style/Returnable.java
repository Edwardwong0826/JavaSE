package com.style;


import java.util.Collection;
import java.util.List;

// interface can extend more than one interface because there is no implementation
 public interface Returnable extends Cleanable, CleanableTwo{

    // An interface is a contract and way of defining shared behaviour, it is a way of abstracting out the shared capability
    // across multiple different class that does not have common super class but common method

    // the variable will be implicitly to be public static final only, not allowed to be private and protected
    public static final int n1 = 100;

    // interface do not have protected method or members

    void doReturn();

    @Override
    void doClean();

    // Start from Java 9, the interface was allowed to have private methods or private static methods,
    // but not protected or protected static methods


    // private method must mark with private and have method body, can only being called in
    // default and private(non-method) methods within interface definition
    private void count1()
    {
        // can be used to reduce duplication code when have many default method doing same logic
    };

    // static method need to have method body, can with or without ; at the end of { }
    private static void count()
    {
        // same to reduce duplication code in static methods within interface definition
    }


    // Starting from Java 8, Interface allow adding concrete static method
    // the extended class that wish to called interface method need to add interface name
    // like format interfaceName.methodName()
    public static double computeAlcoholPercent()
    {

        return 0.0;
    }

    // default methods must have a body, purpose is to add new method to interface without needs the class
    // to implements and break other class, noted default method is an instance method
    // rules of default method
    // 1. only interface allow adding default keyword as default method
    // 2. if class inherits two or more default method with same method signature, the class must override it
    // 3. default method cannot be marked abstract and final
    // 4. default method must have body
    public default int getQuote() // the latest subtype interface default method will get choose
    {
        int val1 = 100;
        int val2 = 200;

        int totalValue;
        totalValue = val1 + val2;

        return totalValue;

    }

    // if we want to called super interface default method instead of sub interface,
    // use format like this interfaceName.super.methodName(), Cleanable.super.getNum();
   public default int getNum(){
      return 500;
   }


    // normal interface abstract method should have : at the end { }
    // interface method implicit to public abstract, cannot be other
    public abstract Integer m1();

    //Hierarchy 1 : A<S> <<< A<? extends S> <<< A<? extends T> wildcard
    //Example: Since Integer is a subtype of Number, List<Integer> is a subtype of List<? extends Integer> and List<? extends Integer> is a subtype of A<? extends Number>.
    //Thus, if an overridden method returns List<? extends Integer>, the overriding method can return List<Integer> but not List<Number> or List<? extends Number>.


    //Hierarchy 2 : A<T> <<< A<? super T> <<< A<? super S> wildcard
    //Example: List<Number> is a subtype of List<? super Number> and List<? super Number> is a subtype of A<? super Integer>
    //Thus, if an overridden method returns List<? super Number>, the overriding method can return List<Number> but not List<Integer> or List<? super Integer>.

    // the overriding method allows to return the subtype of method return type, in this case which is subtype of list
    // can be ArrayList<xxx> but not list <subtype> , the list <subtype> is specifies by the wildcard bounds
    // only super class<? extends supertype> allow subclass overriding method return <? extends subtype>
    List<? extends Clothing> returnOfList();

    // Integer is a child of Number Class, but in ? super wildcards, the more specific class will actually the super class
    // How? List<? super Integer> can contain start from Integer to Number ... , but  List<? super Number> can contains
    // only start form only Number, the more restrictive type is the subtype
    List<? super Integer> returnOfNumber();

    List<Number> returnNumber();

    public <T> Collection<T> transform(Collection<T> list);

    Clothing returnCloths();


}
