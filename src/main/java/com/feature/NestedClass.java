package com.feature;

public class NestedClass {

    private int i;

    public static void callStatic(){

    }

    interface InnerInterface{
        int testInt();
    }

    interface InnerInterface2{

    }

    public class InnerClass{

        // inner class cannot static declarations, only inner static class can have
        // only allow final static variable not static variable
        public int repeat = 1;
        // public static int repeat = 10;

        public void go()
        {
            System.out.println("Go");
        }


    }

    public static class NestedStatic implements InnerInterface2{
        private int price = 6;

    }

    public void localInnerClass(){
        final int price = 1;
        final int times = 4;

        // the local inner class rules
        // 1.no access modifier
        // 2.cannot declared static and static fields or methods but static final fields
        // 3.only have access to local variable for those final or effectively final
        // 4.local inner class exist when the method being called, out of scope after method return
        class LocalInner{

            public void times(){
                System.out.println(price * times + i);
            }
            //because separate class has no way to access to local variables, so need to be final to compile and pass

        }

        LocalInner localInner = new LocalInner();
        localInner.times();

        privateMethod();

    }

    interface SaleTodayOnly{
        int dollarOff();
    }

    public void callInner(){
        InnerClass inner = new InnerClass();
        inner.go();
    }
    private void privateMethod(){
        System.out.println("Nested class private method");
    }

    public void admission(){
        InnerInterface iInterface = new InnerInterface() {
            @Override
            public int testInt() {
                return 10;
            }
        };

        System.out.println(iInterface.testInt());
    }

    public static void main(String[] args)
    {
        NestedClass outer = new NestedClass();

        outer.callInner();
        // if we want to directly create inner class instance, we need to go thought the Outer class object to instantiated it
        // because in static method cannot called instance method or instance level class

        var g = new NestedClass().new InnerClass(); // two method equals the same instance of enclosing
        InnerClass inner = outer.new InnerClass(); // normal inner class need instance of enclosing



        // static inner class no need instance of enclosing to access inner class, below two was the same
        NestedClass.NestedStatic nested= new NestedClass.NestedStatic();
        NestedStatic nested1= new NestedStatic();



        inner.go();
        outer.localInnerClass();
        outer.admission();

        // why interface and abstract class can instantiate? here was using anonymous class with body, new xxx {]
        // directly we provide the body and indicating is an anonymous class
        // local variable need to end with semicolon; as end of below braces
        SaleTodayOnly sale = new SaleTodayOnly() {
            @Override
            public int dollarOff() {
                return 3;
            }
        };




    }
}
