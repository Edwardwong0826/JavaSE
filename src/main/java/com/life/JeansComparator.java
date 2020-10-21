package com.life;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.Function;

public class JeansComparator implements Comparator<Jeans>, Comparable<Jeans> {

    // Comparable specify compare by what or criteria, and Comparable can be used with import java package
    // when using this better to check data it is null and throw exception
    // Comparator specify by what order than the default
    // 0 return if both equal,return positive if current > compare object,return negative if current < compare object

    @Override
    public int compare(Jeans o1, Jeans o2) {

        if (o1.getLength() == o2.getLength())
            return 0;
        else if (o1.getLength() > o2.getLength())
            return 1;
        else
            return -1;

    }

    // x.equals(y) is true while x.compareTo(y) equals 0, vice versa as well x.equals(y) is false,
    // x.Compare(y) not 0, keeping compareTo() and equals() consistent. why? because the compare parameter
    // might not unique, that's why need to be consistent
    @Override
    public int compareTo(Jeans o) {
        return 0;
    }

    public static void main(String[] args) {
        List<Jeans> jeans = new ArrayList<>();
        jeans.add(new Jeans(4,200.00,"Shirt",9,19.5));
        jeans.add(new Jeans(5,100.00,"Shirt",8,39.5));
        jeans.add(new Jeans(6,300.00,"T-Shirt",10,9.5));

        Comparator<Jeans> byLength = (J1, J2) -> (int) (J1.getLength() - J2.getLength());

        // use method reference easily compare with multiply fields, default is ascending order
        // we can use .naturalOrder() and .reverseOrder() to sort order that we want at the beginning or
        // use .reverse() at chaining comparator method to descending order
        Comparator<Jeans> byPriceAndType = Comparator.comparing(Jeans::getPrice).thenComparing(Jeans::getType);
        Comparator<Jeans> byReversed = Comparator.comparing(Jeans::getPrice).thenComparing(Jeans::getType).reversed();
        Comparator<Jeans> byTypeAndPrice = Comparator.comparing(Jeans::getType).thenComparing(Comparator.comparing(Jeans::getPrice)).reversed();
        // Comparator<Jeans> naturalOrder = Comparator.naturalOrder() or Comparator.reverseOrder();

        //Collections.sort(jeans, byLength);
        Collections.sort(jeans, byPriceAndType);


        Function<Jeans, Double> f = s -> s.getPrice();

        jeans.forEach(System.out::println);
        System.out.println();

        jeans.sort(byTypeAndPrice);
        jeans.forEach(i-> System.out.println(i));
        List<?> list = new ArrayList<>(); // ? cannot directly instantiated directly on right side

    }

    // 1.BD 2.E 3.CF 4.F 5.BF 6.B 7.AD 8.BDF 9.E 10.E 11.A 12.ABD 13.BE 14.D 15.A 16.BDF 17.BCD 18.ABCF
    // 19.AF 20.E 21.D 22.B 23.BE 24.AF 25.F

}