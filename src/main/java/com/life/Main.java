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
        tryCatch();
        switchCase();
        lambdaWithPredicate();
        sublist();
        lambdaForOther();

        // is ok to call the function without parameter, because it were declared to take variable number of arguments,
        someMethod();
        arrays();
        labelledBreakLoop();
        forLoop();

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


    public static void forLoop() {
        // is ok to leave 1 and 2 expression to empty, which will be loop forever
        // Second expression must return boolean value
        // Third expression only allow i++, i--, ++i, --i, =, method Call () and ClassInstanceCreationExpression new (...), and variable assignment
        int count = 0;
        System.out.println();

        OUTER_LOOP: for (; Math.random() < 10; ) {

            System.out.println("Outer Loop Now: " + count++);
            // for(int i=0, k=0; xxx;xxx) only correct , multiple variable type declaration only one type
            INNER_LOOP: for(int i=0; i < 5; i ++) // i++ and ++ i no difference , will increment after body execution
            {
                if(i == 3)
                    break OUTER_LOOP; // with optional label, we can break the loop where this label place at, it can break most outer loop
                                     // without this, will only break the inner loop only
                System.out.println("Inner loop: " + i);
            }
            // if we put some unreachable code, and intends to use the variable inside in, the compiler will not compile
            // return and break is the same, continue skip the current iteration
        }

        System.out.println();

    }

    public static void labelledBreakLoop(){

        // use labelled break will breaks the loop even it is the outer most loop,
        // without labelled break will only breaks out the current loop example in nested loop
        int c = 0;
        JACK: while (c < 8){
            JILL: System.out.println("c = "+c);
            for(int k = 0; k<c; k++){
                System.out.println(" k = "+k+" c = "+c);
                if (c > 3) break JACK;
            }
            c++;
        }
    }

    enum Flavors{

        FRUIT, BERRIES{public boolean isHealthy(){return true;}};

    }

    public static void switchCase(int... inInt) {

        short shr = 100;
        int iNt = 10;
        shr = (short) iNt;
        // primitive byte,short,char,int enum and String can be used, wrapper class Character,Byte,Short,Integer
        // switch case will executed the statement that matches the condition, if that statement without break, it will fall
        // through and executed until reaches the break
        switch (iNt) { //switch statement will apply numeric promotion as well

            // The default will be executed if the condition doesn't match with any case
            // but is optional, the switch case can without the default and will not print anything as well

            // default : xxxxxx , if default is at above, and switch statement is 20 , it will not print the default 
            case 10:
                System.out.println("Switch Case: " + 10);
            // case 3 : 4, is not allowed, but this default : case 3 is allowed
            default :  case 3: System.out.print("Switch case default and: " + 5 + " ");
            case 100: // we only can use literal values, local final variable and enum in case expression only, not local variable
                      // and cannot use final method parameter
                break;

            //case 101: 102: This is not valid, will not compile, case expression cannot have multiple value without case


        }
    }

    public static void tryCatch() {

        // Try must come with catch to caught the exception, the finally is optional, but once added,
        // it will executed no matter there is an exception caught,
        // unless in Try or Catch block the System.exit method is called, then finally will not be executed
        // it can be compiled the try without the catch block, just that the exception without caught will be thrown,
        try {

            m1();
            // After m1() throws the Exception, the remaining codes int try will skip and go to that exception catch block
            throw new NullPointerException();

        } catch (IndexOutOfBoundsException e) {
            System.out.println("1");

            // only the exception in try could be catch, those in catch block will not be handle
            // after executed finally block will throw the error

            // throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println("2");

        // we can't put the relationship exception in the same catch block
        // catch(NullPointerException|RuntimeException)
        } catch (Exception e) {
            System.out.println("3");
        } finally {
            System.out.println("4");

            // If try catch thrown the exception, eventually the control will come to the finally block,
            // if here is thrown any exception, the method will throws the finally thrown exception,
            // Other exceptions thrown by the code prior to this point are lost.
            //throw new CloneNotSupportedException();
        }
        System.out.println("END");

    }

    public static void lambdaWithPredicate() {
        int[] names = {0, 2, 4, 6, 8, 10};

        Brand[] brands = new Brand[]{new Brand("Omega"), new Brand("Rolex")};
        List<Brand> brandList = new ArrayList<>(Arrays.asList(brands));
        List<Brand> brandList1 = Arrays.asList(brands);
        List<Integer> mylist1 = new ArrayList(Arrays.asList(names));
        ArrayList<Integer> mylist2 = new ArrayList<>();

        mylist2.add(0);
        mylist2.add(2);
        mylist2.add(4);
        mylist2.add(6);
        mylist2.add(8);
        mylist2.add(10);

        // The predicate can either declared explicitly  or directly in method that requires predicate parameter use lambda
        // And return boolean as a result
        Predicate<Integer> pred = p -> p.intValue() == 0;

        mylist2.removeIf(pred);

        // Return keyword will required the method body, multiply line will need to use return keyword
        brandList.removeIf((Brand b) -> {
            return b.getName().equalsIgnoreCase("Rolex");
        });

        // If try to remove the list that is back by array will cause error, but may changed the elements using list.set
        brandList1.removeIf(b -> b.getName().equalsIgnoreCase("LV"));

        System.out.println("\n");
        for (int i : names) {
            mylist1.add(i);
        }

        for (Integer i : mylist2) {
            System.out.println(i);

        }

        System.out.println(brandList.get(0).toString());

    }

    public static void lambdaForOther() {

        List<String> strArrList = new ArrayList<>(Arrays.asList("Java", "C++", "React"));
        List<Integer> intArrList = new ArrayList<>(Arrays.asList(80, 90, 56, 65, 150));

        final String exp= "!!";
        String space = " ";

        //Rules of lambda parameter parentheses and braces
        // 1. Can omit () if only one parameter and type is not stated
        // 2. More than one parameter should have ()
        // 3. if one parameter and stated type should have ()
        // 4. Can omit {} when only a single statement
        // 5. when have braces should contain the return and semicolon, { return xxx ;}

        // Function interface accept one argument and return results, <Integer, String> Where first is argument, and second is
        // return type
        Function<Integer, String> f = s -> "hello" + s;
        Function<Integer, Integer> f2 = s -> s + s;

        // Consumer takes something and does something
        Consumer<String> printConsumer = s -> System.out.println(s + " Programming" + space  + "language!" + exp);

        // Supplier takes nothing and return something
        Supplier<List<Brand>> listSupplier = ArrayList::new;

        //Predicate take argument and return boolean value
        Predicate<Integer> pred = (Integer p) -> p > 100;


        // lambda can access an instance methods, method parameter or local variable if they not set after initialized,
        // need to be effectively final. But if really want lambda to access, better add the final to the variable
        Predicate<Integer> predic = p -> space.length() == p;


        System.out.println(strArrList.toString() + "\n");
        strArrList.stream().forEach(printConsumer);
        intArrList.stream().filter(pred);
        System.out.println(intArrList);
        System.out.println();
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


