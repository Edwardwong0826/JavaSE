package com.NIO2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NIOBufferedReader {

    public static void main(String[] args)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("C:/Java/Count.txt"));
            System.out.println("===File===");
            reader.lines().forEach(line -> System.out.println(line));
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
