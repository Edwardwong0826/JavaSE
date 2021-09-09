package com.cool;

import com.style.Brand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambdas {
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


        // The predicate can either declare explicitly  or directly in method that requires predicate parameter use lambda
        // And return boolean as a result
        Predicate<Integer> pred = p -> p == 0;

        mylist2.removeIf(pred);
        mylist2.removeIf(p -> p == 10);

        // Return keyword will require the method body, multiply line will need to use return keyword
        brandList.removeIf((Brand b) -> {
            return b.getName().equalsIgnoreCase("Rolex");
        });

        // If try to remove the list that is back by array will cause error, but may change the elements using list.set
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
        // 2. More than one parameter and if one parameter and stated type should have ()
        // 3. no parameter should have ()
        // 4. Can omit {} when only a single statement
        // 5. when have braces should contain the return and semicolon, { return xxx ;}

        // Function interface accept one argument and return results, <Integer, String> Where first is argument, and second is
        // return type
        Function<Integer, String> f = s -> "hello" + s;
        Function<Integer, Integer> f2 = s -> { return s + s; };

        // Consumer takes something and does something
        Consumer<String> printConsumer1 = s -> System.out.println(s + " Programming" + space  + "language!" + exp);
        Consumer<String> printConsumer2 = (String s) -> System.out.println(s + " Programming" + space  + "language!" + exp);
        Consumer<String> printConsumer3 = (String s) -> { System.out.println(s + " Programming" + space  + "language!" + exp); };
        // Supplier takes nothing and return something
        Supplier<List<Brand>> listSupplier =  ArrayList::new;
        Supplier<List<Brand>> listSupplier1 = () -> new ArrayList<Brand>();

        //Predicate take argument and return boolean value
        Predicate<Integer> pred = (Integer p) -> p > 100;


        // lambda can access an instance methods, method parameter or local variable if they not set after initialized,
        // need to be effectively final. But if really want lambda to access, better add the final to the variable
        // instance or static or lambda variable are always allowed
        // effectively final only refer to local variables, not included instance variable or static variable
        Predicate<Integer> predic = p -> space.length() == p;

        // working with lambda variables, parameter list, local variable inside lambda body, variable referenced from body
        // class variables always allowed, instance variable are allowed if lambda used inside instance method
        int u = 150;

        Predicate<Integer> predc = p -> {
            int o = 100;
            return p >= u;
        };



        System.out.println(strArrList.toString() + "\n");
        strArrList.stream().forEach(printConsumer1); // forEach takes consumer lambda parameter
        intArrList.stream().filter(pred);
        System.out.println();
        print(intArrList,pred);
        System.out.println();
        System.out.println(intArrList);
        System.out.println();
        System.out.println(listSupplier1.get().add(new Brand()));


    }

    public static void print(List<Integer> intArrList,  Predicate<Integer> pred ){
        for(int i: intArrList){
            if(!pred.test(i))
                System.out.println(i);
        }
    }


    public static void main(String[] args){

        lambdaWithPredicate();
        lambdaForOther();

    }

    // 1.ABC 2.D 3.CE 4BF 5.E 6.CE 7.F 8.AC 9.F 10.E 11.A/F 12.CD 13.A 14.E 15.G 16.AD 17.C 18.CDG 19.D 20.ABCDEF
    // 21.BED 22.DF 23.A 24.CD 25.E
}
