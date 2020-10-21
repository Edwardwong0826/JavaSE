package com.cool;

public class LoopAndSwitch {

    public static void forLoop() {
        // is ok to leave 1 and 2 expression to empty, which will be loop forever
        // Second expression must return boolean value
        // Third expression only allow i++, i--, ++i, --i, =, method Call () and ClassInstanceCreationExpression new (...), and variable assignment
        int count = 0;
        System.out.println();

        OUTER_LOOP: for (; Math.random() < 10; ) {

            System.out.println("Outer Loop Now: " + count++);
            // for(int i=0, k=0; xxx;xxx) only correct , multiple variable type declaration only one type
            INNER_LOOP: for(int i=0; i < 5; i ++) // i++ and ++ i no difference , will increment after body execution
            {
                if(i == 3)
                    break OUTER_LOOP; // with optional label, we can break the loop where this label place at, it can break most outer loop
                // without this, will only break the inner loop only
                System.out.println("Inner loop: " + i);
            }
            // if we put some unreachable code, and intends to use the variable inside in, the compiler will not compile
            // return and break is the same, continue skip the current iteration
        }

        System.out.println();

    }

    public static void labelledBreakLoop(){

        // use labelled break will breaks the loop even it is the outer most loop,
        // without labelled break will only breaks out the current loop example in nested loop
        int c = 0;
        JACK: while (c < 8){
            JILL: System.out.println("c = "+c);
            for(int k = 0; k<c; k++){
                System.out.println(" k = "+k+" c = "+c);
                if (c > 3) break JACK;
            }
            c++;
        }
    }

    public static void switchCase(int... inInt) {

        short shr = 100;
        int iNt = 4;
        final int INT = 5;
        final var vr = 10;
        shr = (short) iNt;

        char a = 10;
        char b = '1';
        System.out.println((int)b);
        System.out.println(a);


        // switch case will executed the statement that matches the condition, if that statement without break, it will fall
        // through and executed until reaches the break
        switch (shr) { //switch statement will apply numeric promotion rule 2 as well

            // The default will be executed if the condition doesn't match with any case
            // but is optional, the switch case can without the default and will not print anything as well

            // default : xxxxxx , if default is at above, and switch statement is 20 , it will not print the default
            // case statement can only use final String or primitive type , other wrapper object cannot
            case vr:
                System.out.println("Switch Case: " + 10);
                // case 3 : 4, is not allowed, but this default : case 3 is allowed
            default :  case 3: System.out.print("Switch case default and: " + 5 + " ");
            case INT: // we only can use literal values, local final variable and enum in case expression only, not local variable
                // and cannot use final method parameter
                break;

            // case 101: 102: This is not valid, will not compile, case expression cannot have multiple value without case
            // case 101 || 102 does not compile


        }
    }

    public static void main(String... args){
        forLoop();
        labelledBreakLoop();
        switchCase();
    }

}
