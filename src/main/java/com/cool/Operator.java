package com.cool;

public class Operator {

    public static void localVar(){

        // var can only use in scope, method, loop, not constructor parameter, method parameter, instance or static fields
        // the local variable type inference is determined at the line of declaration, and type cannot be changed during run time
        var v1 = "";
        v1 = "test";
        System.out.println(v1);
        v1 = null;

        // we can't assign the null at the line of declaration, only after the value to var to make it string or object only can
        // value of var can changed during run time
        var v2 = 3;
        // v2 = null;
        // var v3 = null;
        v2 = 5;

        // Multiple  var declaration is not allowed
        // var v4 = 2, var v5 =3;

    }

    public static void numericPromotion(){

        // 1. java will automatically promote the smallest data types to bigger data types
        // 2. If one is the integral and one is the floating-point , it will promotes the integral to floating point
        // 3. byte, short and char are first to promoted to int with binary arithmetic operator, + - * / %, even neither not an int

        // 1. Autoboxing, the primitive type can be assign wrapper class by JVM
        // 2. Unboxing, the wrapper class can assign to primitive type by JVM
        int x = 1;
        Long y = 3l;
        var z = x * y ; // this is long


        double x1 = 39.21;
        float y2 = 0.0f; // if we put 0.0 next line will error, because float expect to have f, 0.0 means double
        var z2 = x1 + y2; // this is double, small type when perform binary arithmetic operator will promoted to larger type

        short s1 = 10, s2 = 20;
        var z3 = s1 + s2; // this will be result as default int as rule 3 above, unless we expilicty cast as short then will be short
        // notice var z3 = (short)s1 + s1 will return int , why? () cast operator has more precedence then +
        // var z3 = (short)(s1 + s2); only short

        if(50 == 50.0) // the 50 is promoted to double as rule 2
        {
            System.out.println(true);
            int start;
            start = (byte)(Byte.MAX_VALUE + 1); // if try to increment the max type value, it will become last negative type value

            System.out.println(start);
        }

    }

    public static void operator(){
        double test = 0.0;
        long goat = 10;
        short sheep = 5;
        sheep *= goat; // compound operator will cast sheep to larger type, and do multiplication, cast the result to the based on the left type

        System.out.println(((Object)sheep).getClass().getName() + " " +sheep);

        boolean b1 = true;
        boolean b2 = true;
        boolean result = b1 || b2; // this is short circuit operators, will only check right expression if left expression condition is not met
        boolean asleep = b1 && b2;
        boolean awake = b1 ^ b2; // this is logical operator , will check both expression no matter what left expression
        System.out.println(result);  // true
        System.out.println(asleep);   // true
        System.out.println(awake); // false, ^ is XOR, true only when one value is true and other one is false

        short start;
        // this will print 129 instead of -128, because the () casting is higher order then +,
        // so it will automatically promotes both to int instead of cast the results to byte
        start = (byte)Byte.MAX_VALUE + 2;
        System.out.println(start);

    }

    public static void main(String[] args){
        localVar();
        numericPromotion();
        operator();
    }
}
