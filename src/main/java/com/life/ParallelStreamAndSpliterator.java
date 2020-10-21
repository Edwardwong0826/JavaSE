package com.life;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParallelStreamAndSpliterator {

    static List<Jeans> j = new ArrayList<>();


    public static void oldStyleAndNewStyle()
    {
        // old style of collection processing with loop
        j.add(new Jeans(50.00));
        j.add(new Jeans(60.00));
        j.add(new Jeans(70.00));
        j.add(new Jeans(80.00));
        j.add(new Jeans(90.00));

        double result = 0;

        for(Jeans jean: j)
        {
            if(jean.getLength() >= 80)
                result += jean.getLength();
        }

        System.out.println("Sum of jean length that > 80: " + result);

        // new style of collection processing with stream
        double streamSum = 0;
        streamSum= j.stream().filter(i->i.getLength()>=80).mapToDouble(i->i.getLength()).sum();

        System.out.println("Stream sum of jean length > 80: " + streamSum);


    }

    public static void parallel()
    {
        // two ways to call parallelStream, xxx.parallelStream or .parallel() as operation
        // parallel stream might not always faster, it does have overhead by called the fork join framework
        // large data set or complex computation benefits from parallel stream


        double result = j.parallelStream().filter(l -> l.getLength()>= 80)
                .mapToDouble(l->l.getLength()).sum();

        double result2 = j.stream().filter(l -> l.getLength()<= 70)
                .mapToDouble(l->l.getLength()).parallel().sum(); // we can choose parallel or sequential

        System.out.println("Produce by Parallel Stream: " + result);
        System.out.println("Produce by Parallel Stream: " + result2);
    }

    public static void Stateness()
    {

        // stateless is good for parallel stream, means no parameter and dependent on other threads
        // nature of type collection can affect the performance of parallel stream

        // in for each after the parallel stream split the stream and each thread do the filter, then each thread is
        // trying to access the same j2 ArrayList to add, this is shared state and will have synchronization issue
        List<Jeans> j2 = new ArrayList<>(); // this not a good parallel , bad
        j.parallelStream().filter(i -> i.getLength() >= 80).forEach(i-> j2.add(i));

    }


    // Reduction need take an associative function to parallelize cleanly,
    public static void StreamReduction()
    {
        // if passing non-associative function to reduce, will get the wrong answer as non deterministic

        // reduce T identity parameter, means the default value for the stream don't have anything
        // reduce BinaryOperator<T> accumulator
        // reduce BinaryOperator<U> combiner, use for parallel stream when split to multiply stream do accumulation
        // and combining these intermediate results together into one stream result
        int r2 = IntStream.rangeClosed(1,5).parallel().reduce(0,(sum, element)->sum+element);
        System.out.println("Result : " + r2);
    }

    public static void main(String[]args)
    {

        //Splittable iterator is default cover by the stream framework, and the standard one in JDK already very good
        // unless want to custom our own one, normally doesn't necessary to created custom spliterator
        // like  want to specify the split time of the collection

        oldStyleAndNewStyle();
        parallel();
        Stateness();
        StreamReduction();

    }
}
