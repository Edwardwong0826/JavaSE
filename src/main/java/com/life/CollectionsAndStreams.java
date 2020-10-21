package com.life;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class CollectionsAndStreams {

    public static void StreamAndFilter(){

        List<Integer> ages = new ArrayList<>();
        ages.add(10);
        ages.add(50);
        ages.add(70);
        ages.add(80);
        ages.add(90);

        List<Jeans> j = new ArrayList<>();
        j.add(new Jeans(50.00));
        j.add(new Jeans(60.00));
        j.add(new Jeans(70.00));
        j.add(new Jeans(80.00));
        j.add(new Jeans(90.00));


        // We should iterating over collections and data sources using stream rather than normal for loop prior after Java 8
        Predicate<Jeans> testLength = jean -> jean.getLength() > 50;
        System.out.println();
        for(Jeans jean : j){
            if(testLength.test(jean)){
                System.out.println(jean);
            }
        }

        // stream is a ordered pipeline of these aggregate operations that process sequence of elements
        // types of stream operations, terminal operations take all the intermediate operations and produce a result
        // stream will stop when requirements are matches using short-circuit operations,
        // Intermediate deals with Infinite Streams by return Infinite Streams
        // Intermediate - filter or map steps
        // Intermediate short-circuit
        // Terminal - reduce steps  - reduce steps
        // Terminal short-circuit

        // the filter takes out the elements into the stream, and stream can use the elements with the method perform action
        // filter, map, reduce operation
        // filter return stream object, stream then called for each method
        // for each does not terminate on infinite streams, and is not Reduction
        ages.stream().filter(i -> i > 20 ).forEach(i-> System.out.println(i));
        ages.stream().filter(i -> i > 60 ).forEach(Integer::intValue);

        // Stream forEach lambdas version
        j.stream().filter(o -> o.getLength() > 50 ).forEach(jeans -> System.out.println(jeans.getLength()));
        //stream terminal operation cannot directly print instance method values, only instance method inside print will display
        j.stream().filter(o -> o.getLength() > 50 ).forEach(Jeans::getLength);


    }

    public static void StreamAndMap(){

        List<Jeans> j = new ArrayList<>();
        j.add(new Jeans(50.00));
        j.add(new Jeans(60.00));
        j.add(new Jeans(70.00));
        j.add(new Jeans(80.00));
        j.add(new Jeans(90.00));

        // Stream map
        // map iterate each stream elements, and produce new stream contains elements, get something and transform to another thing
        // map takes Function<? super T, ? extends R> xxx as an argument
        List<String> alpha = Arrays.asList("a","b","c","d");
        List<String> collect = alpha.stream().map( s-> s.toUpperCase()).collect(Collectors.toList()); // ;lambdas version
        List<String> collect1 = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());

        // Stream peek
        // use to see what have the stream elements is currently, is a lazy operations
        List<Double> collect2 =  j.stream().map(Jeans::getLength)
                .peek(length -> System.out.println("Each Jeans length: " + length)).collect(Collectors.toList());
        System.out.println();

        System.out.println(collect1);
        System.out.println(collect2);
        System.out.println();
    }

    public static void StreamIntermediateOps(){

        // filter() - filter the elements based on Predicate function
        // distinct() - return a streams with duplicate values removed
        // limit() and Skip() - return a smaller stream , or make infinite stream to finite stream
        // map() -  one-to-one mapping from the elements in the stream to the elements of the next step in the stream
        // flatMap() - flatten [ [1,2,3],[4,5,6],[7,8,9] ] multi level to become [ 1,2,3,4,5,6,7,8,9 ] one level structure

        List<String> list = new ArrayList<>(Arrays.asList("Call","Call","of Duty"));
        list.stream().distinct().forEach(System.out::println);

        Stream<Integer> s = Stream.iterate(1, n -> n + 1);
        s.skip(5).limit(2).forEach(System.out::println); // skip the first five elements, and limit to print to 2 elements only


        List<Integer> list1 = List.of(1,2,3,4,5);
        List<Integer> list2 = List.of(6,7);
        Stream<List<Integer>> number = Stream.of(list1,list2);
        number.flatMap(m->m.stream()).forEach(n-> System.out.println(n));

    }

    public static void StreamTerminalOps(){

        // Reduction is look at each element and return single result
        // Count() - does not terminate for Infinite Streams, and is Reduction
        // min() and max() - does not terminate for Infinite Streams, and is Reduction
        // findAny() and findFirst - Terminate for Infinite Streams , and is not Reduction
        // allMatch()/anyMatch()/noneMatch() - Sometimes terminate for Infinite Streams, and not Reduction
        // forEach() - does not terminate for Infinite Streams, and is not Reduction
        // reduce() - does not terminate for Infinite Streams, combines a Streams into a single object
        // Collect() - special type of Reduction called a mutable reduction

        var i = LongStream.of(1,2,3);
        var opt = i.map(n -> n * 10).filter(n->n<5).findFirst();
        opt.ifPresent(System.out::println);

        System.out.println();
        //System.out.println("\n" + i.count());

        Stream<String> s = Stream.of("Alpha","Charile","Echo");
        Optional<String> min = s.min( (s1,s2) -> s1.length() - s2.length());
        min.ifPresent(System.out::println);
        //
        //
        //
        //        Stream<String> s1 = Stream.of("monkey", "gorilla", "bonobo");
        //        Stream<String> infinite = Stream.generate(() -> "chimp"); // this generate infinite elements by given supplier function
        //        s1.findAny().ifPresent(a -> System.out.println(a)); // monkey
        //        infinite.findAny().ifPresent(System.out::println); // chimp
        //
        //        // allMatch()/anyMatch()/noneMatch()
        List<String> list = Arrays.asList("Hello","1","World");
        Stream<String> infiniteString = Stream.generate(()-> "Hello");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
        System.out.println("\n" + list.stream().allMatch(pred));
        System.out.println(list.stream().anyMatch(pred));
        System.out.println(list.stream().noneMatch(pred));
        System.out.println(infiniteString.anyMatch(pred));

        // Streams can only use forEach to prints the elements, normal loop cannot don't implements Iterable interface
        Stream<String> s2 = Stream.of("Alpha", "Delta", "Hotel");
        System.out.println();
        s2.forEach(System.out::println);

        Stream<String> call6 = Stream.of("H","E","L","L");
        String word = call6.reduce("", (q, w)-> q + w);
        System.out.println();
        System.out.println(word);
        System.out.println();

    }

    public static void StreamAndCollect(){

        // collect() is a special reduction called mutable reduction

        System.out.println();
        BiConsumer<String, StringBuilder> b2 = (s,d) -> d.append(s);
        BiConsumer<StringBuilder, StringBuilder> b3 = (s,d) -> d.append(s);
        Supplier<StringBuilder> s1 = () -> new StringBuilder();

        // use collect() we can likely to get sequence result we want based on the BiConsumer<R,R> xxx

        Stream<String> stream = Stream.of("w","o","l","f");
        StringBuilder word = stream.collect(StringBuilder::new,StringBuilder::append,StringBuilder::append);
        System.out.println(word);

        // Use Collectors.toCollection() return result with encounter order
        // Collectors.toSet() does not guarantees which implementation of set will get, but mostly HashSet

        Stream<String> stream2 = Stream.of("w","o","l","f");
        TreeSet<String> treeSet = stream2.collect(Collectors.toCollection(()-> new TreeSet<>()));
        System.out.println(treeSet);

        Stream<String> stream3 = Stream.of("w","o","l","f");
        Set<String> set = stream3.collect(Collectors.toSet());
        System.out.println(set);

        // Function KeyMapper - a mapping Function to produce keys
        // Function ValueMapper - a mapping Function to produce value
        Stream<String> stream4 = Stream.of("w","o","l","f");
        Map<Object,String> map = stream4.collect(Collectors.toMap(s->s.charAt(0), s->s));
        System.out.println(map);

        // BinaryOperator<U >MergeFunction - used to resolve collisions between values associated with the same key
        Stream<String> stream5 = Stream.of("w","o","l","f","w");
        Map<Object,String> map2 = stream5.collect(Collectors.toMap(s->s.charAt(0), s->s, (s,a)-> s + ","+a));
        System.out.println(map2);

        // Supplier mapSupplier - a function return which new map that we want
        Stream<String> stream6 = Stream.of("w","o","l","f","w");
        HashMap<Object,String> map3 = stream6.collect(Collectors.toMap(s->s.charAt(0), s->s, (s,a)-> s + ","+a, HashMap::new));
        System.out.println(map3.getClass());

        Stream<String> stream7 = Stream.of("we","oa","l","f","w");
        StringBuilder list = stream7.collect(StringBuilder::new,StringBuilder::append,StringBuilder::append);
        System.out.println(list);
    }



    public static void main(String[] args){

        // Stream use with lambdas operations
        StreamAndFilter();
        StreamAndMap();
        StreamIntermediateOps();
        StreamTerminalOps();
        StreamAndCollect();

        // this line will hang and hit java heap space exception because it running forever
        // why? the sorted() waited all elements arrived only start sort, which is going forever
        // because stream is generate infinite elements, unless we kill it or limit before sorted()
        //Stream.generate(()-> "Elsa").filter(n->n.length() == 4).sorted().limit(2).forEach( System.out::println);

        List<String> numbers = Arrays.asList("one","two","three","four","five");
        String result = numbers.parallelStream().reduce("->",(one,two)-> one + "," + two, (one,two)-> one + ";" + two);
        System.out.println(result);
    }

}
