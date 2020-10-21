package com.life;

import com.feature.ArrayAnnotation;
import com.style.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Generics<T, U>{

    // Collection frameworks use to do filter, map, reduce job
    // <T,U> is just like Object class in Java
    private T t; // private Object t
    private U u; // private Object u

    public <t,u> Generics(T t, U u){
        this.t = t;
        this.u = u;
    }

    public T getT() {
        return t;
    }

    public U getU() { return u;};

    public void setT(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public static <T> void display(List<T> input){

        for(T element: input){
            System.out.println(element);
        }
        System.out.println();

    }

    public static <U extends Exception> void printException(U u)
    {
        System.out.println(u.getMessage());
    }


    // <?> cannot in left side, and <?> means unknown type for generic,
    public static <T> void unboundedWildcard(List<?> inList){

    }

    public String toString() { return t.toString() + ":" + u.toString(); }

    // the list without generics <> is assume to be able to take in Object type
    // if want specific what type the list can take on, should use <> to specific to enforce compiler check for us
    public static List getList(){
        return null;
    }

    public static void testComparator(){

        List<Jeans> j = new ArrayList<>();
        j.add(new Jeans(30.0));
        j.add(new Jeans(10.0));
        j.add(new Jeans(20.0));

        Collections.sort(j, new JeansComparator());

        for(Jeans jeans : j ){
            System.out.println(jeans);
        }
    }

    public static void ListMethod(){
        //concurrent collection CopyOnWriteArrayList<>()
        List<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(3);

        num.remove(0); // methods remove the first matching value in the ArrayList or remove the element at a specified index
        num.set(0,2); // this one changes one of the elements by index of the ArrayList without changing the size
        System.out.println(num.isEmpty()); //this check the list size
        System.out.println(num.size()); // this give the size of list
        num.clear(); // this remove all elements in list
        num.contains(3); // this checks whether a certain value is in the list, return boolean

        String[] str = {"haha"};

        // this arrays.as list will make the list backed by the array, either changes will reflect in both
        // but no removed or add, set as the array is fixed size even the list is dynamic size
        List<String> nums = Arrays.asList(str);

        // this will create the list that will not affected by array changes
        List<String> nums1 = new ArrayList<String>(Arrays.asList(str));

        // this list.of return the list that are unmodifiable, can't use set() at all
        List<String> test = List.of(str);
        // test.set(0,"GG");

        // this list.copyOf return immutable list with copy of original collection values
        List<String> copy = List.copyOf(nums);

        //copy.add("haha"); - cause unsupportedoperation exception

        List nonGeneric = new ArrayList<Integer>(); // since list does not specific any generic type, it will be list of object

        nums.set(0,"lol");
        System.out.println();
        System.out.println(nums);

        System.out.println(str[0]);

        System.out.println(nums1);

        System.out.println(copy);


    }

    public static void Map(){


        // Command method of Collection is below
        // 1. add()
        // 2. remove()
        // 3. isEmpty() and size()
        // 4. clear()
        // 5. contains()
        // TreeMap and TreeSet is sorted and calls compareTo()
        // HashMap and HashTree not sorted and calls hashCode()
        // concurrent collection
        // SkipListSet, SkipListMap = TreeMap, TreeSet

        //list.of, set.of , map.of return unmodifiable collections
        List<String> keys = List.of("E");
        // A map cannot contain duplicate keys, it can get the value by giving the keys or also called key pairs, and is un-order
        // TreeMap is sorts the key, the values not sorted

        Map<String, String> map = new TreeMap<>();
        map.put("first", "first pair value");
        map.put("third", "third pair value");
        map.put("second", "second pair value");

        System.out.println();
        System.out.println("Map--------------------------------");

        for(String k: map.keySet()){
            System.out.println(k);
            // if when remove element during iterate, it will throws ConcurrentModificationException
            // because not Concurrent collection
            map.remove("first");
        }

        System.out.println("\n" + map.get("first"));
        map.forEach((k,v)-> System.out.println(v));
        map.entrySet().forEach(e-> System.out.println(e.getKey() + ":" +e.getValue()));

        // Common map method
        // 1.replace(), replaceAll()
        // 2.pufIfAbsent() - set a value in the map but skips if the value is set and not null
        // 3.getOrDefault()
        // 4. merge()
        Map<String,String> hMap = new HashMap<>();
        hMap.put("lion","meat");
        hMap.put("giraffe","leaf");
        hMap.put("123",null);
        System.out.println(hMap.containsKey("lion"));
        System.out.println(hMap.containsValue("grass"));


        // get() return will return null if not presented, getOrDefault() will return the defaultValue if not found
        System.out.println("This is " +hMap.get("123"));
        System.out.println("This is " +hMap.getOrDefault("Tiger","Tiger"));

        BiFunction<String,String,String> mapper = (v1 ,v2) -> v1.length() > v2.length()? v1:v2 ;
        Map<String,String> favorites = new HashMap<>();
        favorites.put("Hello",null);
        favorites.merge("Tom","Skyride",mapper); // mapping function need to have two value t decide
        favorites.merge("Hello","Skyride",mapper); // if map value is null, if will just the mapper value
        System.out.println(favorites);  // if mapping is called and return null, the key will removed from map


    }

    public static void set(){

        System.out.println();
        // A set contains no duplicate elements, only holds unique value or elements, TreeSet is sorted
        // The tree collection require the class to implements comparable to sort, else it will hit run time exception
        Set<Character> letters = Set.of('z','o','a');
        Set<Character> copy = Set.copyOf(letters);

        // concurrent collection CopyOnWriteArraySet<>()
        Set<Integer> intSet = new HashSet<>();
        intSet.add(10);
        intSet.add(20);
        intSet.add(40);
        intSet.forEach(System.out::println);

        // TreeSet is always sorted order
        Set<Jeans> set;
        set = new TreeSet<>();  // in () can put the comparator method to sort the order you want
        //set.add(new Jeans(50.00));
        set.forEach(System.out::println);
        Comparator<Jeans> byLength= (j1 ,j2) -> (int)(j1.getLength() - j2.getLength());
    }

    public static void queue(){

        // queue normally is FIFO order, also can LIFO

        System.out.println("Queue-------------------------");
        Queue<Integer> queue = new LinkedList<>();
        System.out.println(queue.offer(10));
        System.out.println(queue.offer(4)); // offer adds an element to back and boolean for successful
        System.out.println(queue.peek());
        System.out.println(queue.poll()); // poll() remove and turn next element or return null if empty
        System.out.println(queue.poll());
        System.out.println(queue.peek()); // peek() return next element or return null if empty


    }


    public static void methodReference() throws IOException{

        // MethodReference use for called single-method lambda expression
        // there are four formats for method reference
        // 1. Static methods  2. Instance methods on a particular instance
        // 3. Instance methods on a parameter to be determined at runtime
        // 4. Constructors


        // lambda expression can store in interface and passed to method parameter
        // first is lambda version, second is method reference version
        FunctionalInterface FI = i -> System.out.println(i);
        FunctionalInterface Fi =  System.out::println;

        // This is Instance Methods on a Particular Object
        // first and two are the lambda version
        Jeans j1 = new Jeans();
        Predicate<Integer> pred = i -> i > j1.getPrice();
        Predicate<String> predStr = s -> s.isBlank();

        // This is the method reference,Instance Method on a Parameter
        // method reference does not passed the parameters
        Predicate<String> predIns = String::isBlank; //ObjectType::instanceMethod

        // this is static method reference,
        Predicate<Double> predSta = Jeans::compare; //Class::staticMethod

        // Constructor method reference
        Supplier<List<Jeans>> s = () -> new ArrayList<>();
        Supplier<List<Jeans>> s1 = ArrayList::new; //ClassName::new

            throw new IOException();

    }


    public static void main(String[] args){

        Shirt Shirt = new Shirt(10, 250, "T-Shirt", "Cool");
        Shirt Shirt1 = new Shirt(10, 250, "T-Shirt");
        Trousers tourse1 = new Trousers(15,250,"long-sheeve",43);
        Clothing clo1 = new Trousers(15, 200,"cow-boy",40);
        Clothing clo2 = new Shirt(9,160,"Shirt","Formal");
        Brand b1 = new Brand("LV");

        // use collections interface as reference type to allow in future to changed to other concrete implementation class
        // use extends wildcard get the object, use super wildcard to add the object
        // Generic collections type cannot add subtype instance, must be exact type that <> specify on

        var varlist1 = new ArrayList<Integer>(); // this will be Integer var Arraylist
        var varlist2 = new ArrayList<>(); // this will be raw type object var Arraylist

        List<? extends Clothing> clothsExtendList;
        List<? super Brand> clothsSuperList;
        List<Clothing> cloths = new ArrayList<>(); //prior to java 11, right hand side <> can be empty
        List<Shirt> shirts = new ArrayList<>();

        shirts.add(Shirt);
        shirts.add(Shirt1);

        // <? extends ... > upper-bounded generics and <?> unbounded generics are immutable, means cannot perform remove or add
        clothsExtendList = new ArrayList<Shirt>();
        // Clothing returnObj = clothsExtendList.get(0);

        //<? super xxx> lower-bounded wildcard guarantee too add instance of xxx or subclass of instance xxx only
        clothsSuperList = new ArrayList<>();
        clothsSuperList.add(b1);
        clothsSuperList.add(clo1);
        clothsSuperList.add(Shirt1);
        clothsSuperList.add(tourse1);
        System.out.println(clothsSuperList.get(0).toString());

        display(clothsSuperList);

        testComparator();
        ListMethod();
        set();
        queue();
        Map();

        // Generic Classes <T,U>, the class not need to know what is passed in, T is just like Object class
        String str= "iPHONE";
        Integer price = 4899;
        Generics<String, Integer> GC = new Generics<>(str,price);
        System.out.println();
        System.out.println(GC.getT());
        System.out.println(GC.getU());

        System.out.println( new Generics<String,Boolean>("Str",true).toString());
        var number = new HashSet<Number>();
        number.add(null);
        number.add(309L);
        number.add(Integer.valueOf(85));
        Iterator iter = number.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

        Generics.<NullPointerException>printException(new NullPointerException("C"));
        List<Integer> one = List.of(8,9,10);
        var copy = List.copyOf(one);
        var copyOfCopy = List.copyOf(copy);
        var thirdCopy = new ArrayList<>(copyOfCopy);

        var map = new HashMap<Integer,Integer>();
        map.put(1,10);
        map.put(2,20);
        map.put(3,null);
        map.merge(1,3,(a,b) -> a + b); // merge the map value with the give value based on the key , if is null
        map.merge(3,3,(a,b) -> a + b); // direct put the given value to map value
        System.out.println(map);

        List<?> q = List.of("mouse","parrot"); // this ? treats as object list, so method not object can not use
        var greetings = new LinkedList<String>();

    }

    // 1.BE 2.D 3.E 4.B 5.BCF 6.C 7.AC 8.C 9.E 10.D 11.A 12.ABD 13.ABE 14.E 15.C 16.BDF 17.BCD 18.ABCF 19.A 20.A 21.AF 22.B 23.BE
    // 24. BCF 25.F



}
