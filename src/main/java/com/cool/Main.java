package com.cool;

import com.style.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.style.Trousers;
import com.style.Clothing;


public class Main {

    static String url = "www.test.com";
    String haha = "nono";
    static Main getMain() {
        System.out.println("Getting Main");
        return null;
    }

    public static void main(String[] args) {

        // append,replace method end index exact, no need add extra one length
        // String and StringBuilder both implements CharSequence interface
        // insert end offset is right before index, example insert(4,-) -animals- become -ani-mals-
        String s1 = new StringBuilder("world").insert(0, "hello", 0,4).toString();

        String s2 = new StringBuilder("hello").append(" world123 ", 0, 6).toString();


        // this one used method chaining, and actually return two string already
        String s3 = "  hello World  ".trim().replace('h', 'H');

        String s4 = "  Hello World  ";

        // replace end offset is right before index, if end offset is beyond the length, it will treats as the whole end
        String s5 = new StringBuilder("hello WORLD").replace(6,11, "world").toString();

        // delete end offset is right before index, if end offset is beyond the length, it will treats as the whole end
        StringBuilder s6 = new StringBuilder("Hello World").delete(5,6);


        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        // strip and trim same, removing blank space from begin and end,  removed \t \n \r, strip is new in 11 and support unicode
        System.out.println(s4.strip());
        System.out.println(s4.trim());
        System.out.println(s4.stripLeading());
        System.out.println(s4.stripTrailing());
        System.out.println();
        System.out.println("StringBuilder Replace: " + s5);
        System.out.println("StringBuilder Delete : " + s6);

        System.out.println("index of w is at index of hello world in " + s2.indexOf('w'));

        // String substring method end index need to have one extra length
        System.out.println("Substring of 3 length start with w in hello world: " + s2.substring(6,11));


        List<String> al = new ArrayList<String>(Arrays.asList(s1, s2));
        List<String> al1 = Arrays.asList(s1);

        System.out.println(al.get(0));

        // The Java objects is all passed by value, even thought it looks like pass by reference,
        // when passing to method, the method local object actually points to the original object memory,
        // when modify it can updates, but when it set to null the local object is changed to point to null only
        // hence the original object does not affected
        String s = " aaa ";
        StringBuilder sb = new StringBuilder("bbb");
        testRefs(s, sb);
        System.out.println("s=" + s + " sb=" + sb);
        System.out.println("\n");

        System.out.println(getMain().url);
        arrayCompare();

        // + operator  overloads with string or literals, not other type, below will not compiled
        //System.out.println(null+true);

        int num = 5;
        for(int i=0; i<3; i++)
        {
            num = num + i;
        }
        System.out.println(num);
        bNotC();
        System.out.println();
        stringOperatorPlus();
        stringPool();


    }

    public static void testRefs(String str, StringBuilder sb) {

        str = str + sb.toString();
        str.strip(); // all string method will return new string
        sb.append(str);
        // set this StringBuilder to null does not affected to the original StringBuilder object, but it can update the original object
        // set to null only means pointer points to null only
        //sb = null;
        //str = null;

    }

    public static void arrayCompare(){

        // if replace equals , it will actually return the same string instead of new string
        if ("String".replace('g', 'g') == "String") {
            System.out.println(true);
        }

        // Java 11 Array added compare and mismatch methods, will be two flavors method for the exam
        // return the - based on how many elements less than, and also + how many elements more than
        // null < any type
        // prefix with extra elements >  prefix
        // number < uppercase letters < lowercase letters , when same type the left side is bigger than right size, 0<1, a<b, A<B
        char[] a1 = {'h', 'e', 'A', 'l','o'};
        char[] b2 = {'h', 'e', 'Z'};
        char[] a3 = {'h', 'e', 'A', 'l','o'};
        char[] b4 = {'h', 'e', 'A'};

        // mismatch return the index of array when the array difference occur
        int x = Arrays.compare(a1, b2);
        int y = Arrays.mismatch(a1, b2);
        int z = Arrays.compare(a3, b4);
        System.out.println("Array Compare char[] : " + x ); // positive if first is bigger , negative if first is smaller
        System.out.println("Array Compare char[] : " + z);
        System.out.println("Array Mismatch char[]: " + y);


        // int compare if first array smaller return -1 ,same when second array return 0, bigger return 1
        // null < any type
        int[] ia1 = {0, 1, 6, 6};
        int[] ia2 = {0, 1, 5};
        System.out.print("Array Compare for int[]: " + Arrays.compare(ia1, ia2) + "\n");


    }

    public static void bNotC(){

        Clothing clo1 = new Clothing();
        System.out.println((clo1 instanceof Clothing) && (!(clo1 instanceof Trousers)));
    }


    public static void stringOperatorPlus(){

        // 1. If both operands are numeric, + means numeric addition.
        // 2. If any/either operand is a String, + means concatenation
        // 3. The expression is evaluated left to right, this rule is important, order make different
        System.out.println(1 + 2);           // 3
        System.out.println("a" + "b");       // ab
        System.out.println("a" + "b" + 3);   // ab3
        System.out.println(1 + 2 + "c");     // 3c , 1 + 2 is numeric, so addition, and then 3 + "c", so is concatenation
        System.out.println("c" + 1 + 2);     // c12 , first is "c" + 1string + numeric, thus concatenation, after that is string "c1" + 2, rule 3

        String s = "1";
        s += 2;
        System.out.println(s); //this gives "12", as rule 2
        System.out.println();

    }

    public static void stringPool(){

        // String pool, java reuse the string literal/object and store in pool or intern pool, the jvm will create only one object
        // in memory for repeated string reference that have same value instead of different object memory

        String x = "Hello World"; // this store in string pool
        String y = "Hello World";//  use the string pool same string object
        String z = new String("Hello World"); // new String() is create object without store in string pool
        String first = "rat" + 1; // concentration with be in string pool
        String second = "r" + "a" + "t" + "1";
        String third = "r" + "a" + "t" + new String("1"); // once there is with new string(), then this no longer to be compile-time constant

        System.out.println(x == y); // true
        System.out.println(x == z); // false
        System.out.println(first == second); // true
        System.out.println(first == second.intern());  //intern() will use the string pool object if there is available, else will create in string pool
        System.out.println(first == third); // false
        System.out.println(first == third.intern()); //true

    }



}


