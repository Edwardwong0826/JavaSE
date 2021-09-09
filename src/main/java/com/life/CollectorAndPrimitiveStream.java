package com.life;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.*;
import java.util.stream.*;

public class CollectorAndPrimitiveStream {

    static Stream<String> codeName = Stream.of("Alpha", "Beta", "Hotel");

    public static void primitiveStream(){

        // XXXStream average() return optionalDouble
        // findAny() return optional<T>
        // sum() return primitive <T>
        // Stream<T> or primitive<T>Stream is only can used once and will closed, next times attempt wil throws exception

        // Three common types for primitive supplier types
        // int,double,long

        //Stream<T> - mapToDouble(), mapToInt(), mapToLong() - return xxxStream is from Stream to Stream
        // if xxxStream will to create back own same type Stream just map()
        // example IntStream - IntStream() just map() will create  back IntStream

        System.out.println("primitiveStream-----------------------------------");
        IntStream range = IntStream.range(1, 6);
        range.forEach(System.out::println);
        IntStream maxRange = IntStream.range(1, 6);
        System.out.println(maxRange.max());
        System.out.println("-----------------------");

        Stream<String> objStream = Stream.of("penguin", "fish");
        IntStream intStream = objStream.mapToInt(s -> s.length()); // this mapToInt covert stream to IntStream

        Stream<String> objStream1 = Stream.of("penguin", "fish");
        DoubleStream dStream = objStream1.mapToDouble(s -> s.length());
        OptionalDouble OptDouble = dStream.findAny();
        System.out.println(OptDouble.getAsDouble());
        System.out.println();

        Stream<String> stream = Stream.iterate("",s->!s.isEmpty(), (s) -> s + "1");
        System.out.println(stream.limit(2).map(x -> x + "2")); // this prints java.util.stream.ReferencePipeline$3@7a5d012c

        List<String> copy = Arrays.asList("Alpha","Hotel","Echo");
        List<String> newCopy = copy.stream().sorted((a,b)-> a.compareTo(b)).collect(Collectors.toList());


        OptionalInt Int = intStream.findFirst();// Use primitiveStream short-circuit terminal operations return OptionalInt
        IntStream is = IntStream.empty();

        System.out.println(Int.getAsInt()); // Use OptionalInt to check ifPresent() or empty, or prints out the primitive value
        //ifPresent() expects Consumer function
        Int.ifPresent(x-> System.out.println(x));
        System.out.println(Int.orElseGet(()-> 10));

        IntStream stat = IntStream.rangeClosed(1,10);
        IntSummaryStatistics stats = stat.summaryStatistics();
        System.out.println("Min - Max: " + (int)(stats.getMin()-stats.getMax()));

    }

    public static void StreamSummaryStatistics(){

        System.out.println("SummaryStatistics------------------------");
        List<Jeans> j = new ArrayList<>();
        j.add(new Jeans(50.00));
        j.add(new Jeans(60.00));
        j.add(new Jeans(70.00));
        j.add(new Jeans(80.00));
        j.add(new Jeans(90.00));

        DoubleSummaryStatistics doubleStats = j.stream().mapToDouble(s->s.getLength()).summaryStatistics();
        System.out.println(doubleStats);

        DoubleSummaryStatistics doubleStats2 = new DoubleSummaryStatistics();
        j.stream().mapToDouble(s->s.getLength()).forEach(doubleStats2); // this foreach is equal to foreach(a - >xxxSummaryStatistics.accept(a))
        System.out.println(doubleStats2);

        DoubleSummaryStatistics doubleStats3 = // this was the same as SummaryStatistics(), use collect to do with summaryStatistics accept() and combine() method
        j.stream().mapToDouble(s->s.getLength()).collect(DoubleSummaryStatistics::new,DoubleSummaryStatistics::accept,DoubleSummaryStatistics::combine);
        System.out.println(doubleStats3);

        DoubleSummaryStatistics doubleStats4 = j.stream().collect(Collectors.summarizingDouble(k->k.getLength()));
        System.out.println(doubleStats4);


    }

    public static void primitiveFunctionalInterface(){

        // primitive-specific functional interface
        System.out.println("primitive functional interface------------");
        BooleanSupplier b1 = () -> true;
        System.out.println(b1.getAsBoolean());

        DoubleUnaryOperator d = (b) -> 2 * b;
        System.out.println(d.applyAsDouble(6));

        DoubleToIntFunction dToInt = x -> (int)x;
        System.out.println(dToInt.applyAsInt(30.0));

    }

    public static void streamCollector(){

        // we can use predefined collector or create own collector , better use custom collector in downstream

        System.out.println("streamCollector-----------------------------");
        var ohMy = Stream.of("lions", "tigers", "bears");
        String basic = ohMy.collect(Collectors.joining(", "));
        System.out.println(basic);

        var ohMy1 = Stream.of("lions", "tigers", "bears");
        double rDouble = ohMy1.collect(Collectors.averagingInt(s->s.length()));
        System.out.println(rDouble);

        // Collecting to set
        Stream<String> team = Stream.of("lions", "tigers", "bears");
        TreeSet<String> result = team.filter(s -> s.startsWith("t")).collect(Collectors.toCollection(() -> new TreeSet<String>()));
        System.out.println(result);

        // Collecting to map
        Stream<String> animals1 = Stream.of("lions", "tigers", "bears");
        //Map<String, Integer> map = animals1.collect(Collectors.toMap(s -> s, s-> s.length())); // lambdas version
        //Map<String, Integer> map = animals1.collect(Collectors.toMap(s -> s, String::length)); // method reference version
        Map<Integer, String> map = animals1.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
        //TreeMap<Integer, String> map = animals1.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2, TreeMap::new));
        System.out.println(map); // map.get("Lions")

        Stream<String> animals2 = Stream.of("lions","tigers","bears").parallel();
        ConcurrentMap<Integer,String> map1 = animals2.collect(Collectors.toConcurrentMap(s->s.length(),k->k,(s1,s2)-> s1 + "," + s2));
    }

    public static void collectsByGroupPartitionMap() {

        // Both grouping by and partitioning by can stand alone or use a downstream collector
        // standalone collector collects each group of values into a list

        System.out.println("collectsByGroupPartitionMap-----------------------");
        Stream<String> team1 = Stream.of("Alpha", "Beta", "Hotel");
        Map<Integer,List<String>> mapTeam1 = team1.collect(Collectors.groupingBy(s -> s.length()));
        System.out.println(mapTeam1);

        // grouping by second parameter set the map value to be set
        Stream<String> team2 = Stream.of("Alpha", "Beta", "Hotel");
        Map<Integer, Set<String>> mapTeam2 = team2.collect(Collectors.groupingBy(s -> s.length(), Collectors.toSet()));
        System.out.println(mapTeam2); // {5=[lions, bears], 6=[tigers]}

        // groupingBy second parameter changed the return map type
        Stream<String> team3 = Stream.of("Alpha", "Beta", "Hotel");
        Map<Integer, Set<String>> mapTeam3 = team3.collect(Collectors.groupingBy(s -> s.length(), TreeMap::new, Collectors.toSet()));
        System.out.println(mapTeam3);


        // groupingBy second parameter overload to be downstream collector method
        Map<Integer, Long> mapTeam4 = codeName.collect(Collectors.groupingBy(s->s.length(), Collectors.counting()));
        System.out.println(mapTeam4);


        Stream<String> team4 = Stream.of("Alpha", "Beta", "Hotel");
        Map<Object, Map<Object, List<String>>> mapTeam5 = team4.collect(Collectors.groupingBy(s->s.length(),Collectors.groupingBy(s->s.length()) ));
        System.out.println(mapTeam5);// {4={4=[beta]},5{5=[Alpha,Hotel] }}

        Stream<String> team5 = Stream.of("Alpha", "Beta", "Hotel");
        Map<Object, Map<Object, TreeSet<String>>> mapTeam6 = team5.collect(Collectors.groupingBy(s->s.length(), HashMap::new ,Collectors.groupingBy(s -> s.length(), Collectors.toCollection(() -> new TreeSet<String>()))));
        System.out.println(mapTeam6.getClass()); // {4={4=[beta]},5{5=[Alpha,Hotel] }} HashMap value Map value of TreeSet


        // partitioning split a list into two parts based on the true and false group
        // normally false group will be first and true group will be second
        // no guarantees on type, mutability,serializable or thread safe of map return
        Stream<String> partition1 = Stream.of("Alpha", "Beta", "Hotel");
        Map<Boolean, List<String>> mapPartition1 = partition1.collect(Collectors.partitioningBy(s -> s.length() <= 4));
        System.out.println(mapPartition1);  // {false=[tigers], true=[lions, bears]}

        Stream<String> partition2 = Stream.of("Alpha", "Beta", "Hotel");
        Map<Boolean, Set<String>> mapPartition2 = partition2.collect(Collectors.partitioningBy(s -> s.length() <= 4, Collectors.toSet()));
        System.out.println(mapPartition2);

        Stream<String> partition3 = Stream.of("Alpha", "Beta", "Hotel");
        Map<Boolean, Map<Boolean, List<String>>> mapPartition3 = partition3.collect(Collectors.partitioningBy(s -> s.length() <= 4, Collectors.partitioningBy(s -> s.length() <= 4)));
        System.out.println(mapPartition3); // { false={false=[Alpha,Hotel], true=[]}, true={false=[] ,true=[Beta]}}

        Stream<String> partition4 = Stream.of("Alpha", "Beta", "Hotel");
        Map<Boolean, Map<Boolean, Map<Object, Integer>>> mapPartition4 = partition4.collect(Collectors.partitioningBy(s -> s.length() <= 4, Collectors.partitioningBy(s -> s.length() <= 4, Collectors.toMap(s -> s, String::length))));
        System.out.println(mapPartition4);

    }

    
    public static void main(String[] args){

        //primitiveStream();
        streamCollector();
        //StreamSummaryStatistics();
        collectsByGroupPartitionMap();
        //primitiveFunctionalInterface();
        System.out.println();
        System.out.println(Stream.iterate(1, x -> ++x).limit(5).map(x -> "" + x).collect(Collectors. joining()));

        Stream<String> team4 = Stream.of("Alpha", "Beta", "Hotel");

    }

    // 1.D 2.F 3.F 4.AB 5.ABF 6.B 7.F 8.DE 9.D 10.F 11.ABCD 12.AFG 13.B/F 14.C 15.DE 16.C 17.BE 18.BD 19.ACE 20.B

}
