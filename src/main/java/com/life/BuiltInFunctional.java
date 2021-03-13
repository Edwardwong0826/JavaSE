package com.life;

import com.style.Brand;
import com.style.Clothing;
import com.style.Shirt;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuiltInFunctional {

    public static void lambdasExpression(Predicate<Integer> inPred, List<Integer> inList){

        // lambda expression rely on notion of deferred execution, means the code specified now run later
        for(Integer i: inList)
        {
            if(inPred.test(i))
            {
            System.out.println("This is the wanted value: " + i);
            }
        }

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
        Function<Integer, String> f = s ->  "hello" + s;
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
        Predicate<Integer> predic = p -> strArrList.size() == p;

        // if want to use var, must be in () and all formal parameters be var as well,
        // cannot mix with omit parameter or other type
        Predicate<Integer> predic1 = (var p) -> space.length() == p;

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

    public static void binaryTypes(){

        Jeans j1 = new Jeans(50.00);
        Clothing c1 = new Clothing(10, 100);

        // binary types interface use to take in two object to perform lambda functional interface
        BiPredicate<Jeans, Clothing> calBiPred = (t, s) ->t.getLength() < s.getPrice();

        if(calBiPred.test(j1,c1)){
            System.out.println(j1.getLength() + " Jeans length is less than clothing price " + c1.getPrice());
        }
        else
        {
            System.out.println(j1.getLength() + " Jeans length not less than clothing price " + c1.getPrice());
        }


        Map<String, String> map = new HashMap<>();
        BiConsumer<String, String> b1 = map::put; // example of two parameters using method reference
        BiConsumer<String, String> b2 = (k,v) -> map.put(k,v);

        b1.accept("Hello","world");
        b1.accept("Java","SE");

        System.out.println(map);

        //the first parameter used as the instance to called the method,
        //the second parameter is passed to the startsWith() method inside
        BiPredicate<String, String> BiPred = String::startsWith; // this method references combine two techniques
        BiPredicate<String, String> BiPred1 = (s, p) -> s.startsWith(p);

        System.out.println(BiPred.test("chicken","chick"));

        BiFunction<String, String, String> BiFunction1 = String::concat;
        BiFunction<String, String, String> BiFunction2 = (s, c) -> s.concat(c);
        System.out.println(BiFunction1.apply("Hello","World"));

        // Unary and Binary Operator are special class that require all type of parameter same, both extends Function and BiFunction
        BinaryOperator<String> BiOperator = String::concat;
        System.out.println(BiOperator.apply("Hello","World"));

        // Unary like function, just that argument and result is the same type
        UnaryOperator<String> UnrayOperator = s -> s + "no";


        BiConsumer<StringBuilder,StringBuilder> sb = (t ,b) -> t.append(b);


    }

    public static void convenienceMethod(){

        // convenience method are combining functional interface
        // Function use andThen() - parameter Function to run sequence functional interface
        // Function use compose() - parameter Function
        // Predicate use and() - parameter Predicate to combine two functional interface
        // Predicate use negate() - parameter none
        // Predicate or() - - parameter Predicate

        Predicate<String> brownEggs = s->s.contains("egg") && s.contains("brown");
        Predicate<String> otherEggs = s->s.contains("egg") && s.contains("brown");

        Predicate<String> brown = brownEggs.and(otherEggs);
        Predicate<String> other = brownEggs.and(otherEggs.negate());

        Consumer<String> c1 = x-> System.out.println("1: " + x);
        Consumer<String> c2 = x-> System.out.println(",2: " + x);

        Consumer<String> combined = c1.andThen(c2); // run in sequence and independent of each other
        combined.accept("Annie");

        Function<Integer,Integer> before = x -> x + 1;
        Function<Integer,Integer> after = x -> x * 2;

        Function<Integer,Integer> combine = after.compose(before); // before run first only after run
        System.out.println(combine.apply(3));

    }

    public static void optionalType(int... t){

        // we use Optional to on hold if there is some value maybe empty or null yet
        // get() - throw exception when empty, else return value
        // ifPresent(Consumer c) - does nothing when empty, else call consumer functional interface
        // isPresent() - return false when empty, else return value
        // orElse(T other) - return other parameter when empty, else return value
        // orElseGet(Supplier s) - return supplier when empty, else return value
        // orElseThrow() - throw onSuchElementException when empty, else return value

        Optional<Integer> opt = t == null? Optional.empty() : Optional.of(t.length);
        opt.ifPresent(System.out::println);

        Optional<Double> optDouble = Optional.of(99.00);
        System.out.println(optDouble.orElse(Double.NaN));
        System.out.println(optDouble.orElseGet( ()-> Math.random()));
        System.out.println(optDouble.orElseThrow(RuntimeException::new));


//        Optional<Integer> optionalInteger = Optional.ofNullable(null);
//        System.out.println(optionalInteger.orElseThrow());



    }

    public static void overrideEqualAndHashCodes()
    {
        Shirt s1 = new Shirt(10,50, "T-shirt");
        Shirt s2 = new Shirt(10,50, "T-shirt");
        HashSet<Shirt> shirts = new HashSet<>();
        shirts.add(s1);
        System.out.println(shirts.contains(s2));
        System.out.println("shirt1 hashcode: " + s1.hashCode() + " ------ shirt2 hashcode: " +s2.hashCode());
    }


    public static void main(String[] args){

        List<Integer> ints = new ArrayList<>();
        List<Integer> ints1 = new ArrayList<>();
        ints.add(10);
        ints.add(50);
        ints.add(70);
        ints.add(90);
        ints.add(120);
        ints1.add(120);

        // lambda expression can store in interface and passed to method parameter
        // first and two are the lambda version
        Predicate<Integer> pred = i -> i > 100;

        ints.removeIf(pred);
        System.out.println(ints);
        lambdasExpression(pred,ints1);
        binaryTypes();
        optionalType();
        overrideEqualAndHashCodes();

        // 1.D 2.F 3.E 4.AB 5.AC 6.B 7.F 8.DE 9.BD 10.FG 11.BD 12.FDH 13.G 14.BD 15.B 16.C 17.E 18.AD 19.A
        // 20.ACE 21.DE 22.CE
    }

}
