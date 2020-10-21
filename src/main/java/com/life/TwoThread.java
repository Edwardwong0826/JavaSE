package com.life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class TwoThread {


    public static void main(String[] args){
        System.out.println("Start Main ");
        ConcurrentThreadAndRunnable ct = new ConcurrentThreadAndRunnable();
        ct.start();

        new Thread(new ConcurrentThreadAndRunnable()).start();

        System.out.println("Check 0 Check");
        Thread t1 = new Thread();
        new Thread(t1).start();

        System.out.println("Check 1 Check");
        Thread t2 = new Thread();
        new Thread(t2).start();


    }

    // 1. AE 2.BCDF 3.DE 5.D 6.B 7.A  8.G 9.F 11.AF 12 AC 13. 14. 15.CEG 16.FH 17.C 18.F
    // 19.D 20.AD 21.FG 22.F
}
