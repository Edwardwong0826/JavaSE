package com.cool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ExceptionAndTryResource extends Throwable {

    class test
    {
        public void m2(){
            System.out.println("haha");
        }
    }

    public static void tryCatch()  {

        // Try must come with catch or finally to caught the exception, the finally is optional, but once added,
        // it will executed no matter there is an exception caught,
        // unless in Try or Catch block the System.exit method is called, then finally will not be executed
        // it can be compiled the try without the catch block, just that the exception without caught will be thrown,
        try {

            // if we put catch block to catch same check exception that will not throws anyway, the jvm will complaint
            // since is no way to reach that blocks
            m1();
            throw new IOException();
            // After m1() throws the Exception, the remaining codes int try will skip and go to that exception catch block


        } catch (IndexOutOfBoundsException e) {
            System.out.println("1");


            // only the exception in try could be catch, those in catch block will not be handle
            // after executed finally block will throw the error

            // throw new NullPointerException();
        } catch (NullPointerException | IOException e) {
            System.out.println("2");
            throw new NullPointerException();

            // we can't put the relationship exception in the same catch block
            // catch(NullPointerException|RuntimeException)
            // catch(IndexOutOfBoundsException e |RuntimeException e1) not valid, two variable exist, only ibe variable allow
            // catch(IndexOutOfBoundsException e |RuntimeException e) not valid, two same variable exist
        } catch (RuntimeException e) {
            System.out.println("3");

            // throw new IOException(); - this will error, checked exception need to be in try or method declaration to throws it

        } finally {
            // if here throws exception, then below statement may not execute
            System.out.println("4");
            throw new NumberFormatException();


            // If try catch thrown the exception, eventually the control will come to the finally block,
            // if here is thrown any exception, the method will throws the finally thrown exception,
            // Other exceptions thrown by the code prior to this point are lost.
            //throw new CloneNotSupportedException(); - this is check exception
        }
        //System.out.println("END");

    }

    public static void tryWithResource()  throws IOException {

        // 1. this is implicit try with resource  have the finally blocks
        // 2. we can define our own finally blocks, the implicit will be called first
        // 3. try with resource can without catch and finally, but in try clause if got exception we need to handle
        // 4. multiple resources variables must used ; to separate and have each data type properly, var is allowed
        // 5. try with resource can declared those implements AutoClosable or Closable interface
        // 6. AutoClosable interface declares Exception, Closable interface declares IOException
        // 7. resource are closed at the closed braces that time and by reversed order

        // is possible to use final or effectively final resources type to be use in try-with-resource
        final FileInputStream out = new FileInputStream("output.txt");

        // if is in here throw, the last resource throw will be primary exception, and the other is suppressed exception
        try(FileInputStream in = new FileInputStream("myfile.txt"); // require ; between resource declarations
            out;) // last ; is optional, cam omitted
        {

            // if in try there resource got throw exception and inside try catch{} got throw as well
            // inside {} will be primary exception and resource throw exception is suppressed exception
            // throw new RuntimeException("xxx"); -

            // even here throws exception, but will not go to catch blocks, as will called implicit finally first

        } // closed at Try closing } this time


    }

    public static void suppressedException(){



    }

    public static void m1() throws ClassCastException  {

        System.out.println("m1 Starts");
        //throw new IndexOutOfBoundsException("Big Bang ");

    }
    public static void main(String[] args)
    {

        tryCatch();


    }

    // 1.ACDF 2.BDE 3.G 4B 5C, 6E, 7C, 8G, 9E, 10B, 11D, 12A, 13.ABCDF, 14.ACDE, 15G, 16.ABDEF, 17BCF,
    // 18B, 19.ADEF, 20BCE, 21BDE, 22DF, 23 AE,24F ,25D

}
