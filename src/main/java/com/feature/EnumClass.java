package com.feature;

import com.cool.Subclass;

import java.sql.SQLOutput;

public class EnumClass {

    public enum Season{
        // enum require to have ; after the list of values is there is anything else in the enum
        WINTER("low"),SPRING("Medium"),SUMMER("High"),FALL("Medium");
        private final String expectedVisitor;


        private Season(String expectedVisitor) {
            this.expectedVisitor = expectedVisitor;
        }

        public void printExpectedVisitor(){
            System.out.println(expectedVisitor);
        };
    }

    public enum Season2{

        // enum values with ability to call the method
        WINTER{public String getHours() {return "10am-3pm";}},
        SPRING{public String getHours() {return "9am-5pm";}},
        SUMMER{public String getHours() {return "9am-7pm";}},
        FALL{public String getHours() {return "9am-5pm";}};

        // abstract method enforce enum values to implement method
        public abstract String getHours();
    }

     enum QUALITY{

        A(100),B(75),C(50);

        private final int score;

        private QUALITY(int score){this.score = score;};

    }


    enum Flavors{

        VAN, BERRIES{public boolean isHealthy(){return true;}}

    }

    // (Season2 num)
    public static void switchCase(QUALITY num) {


        // switch case will execute the statement that matches the condition, if that statement without break, it will fall
        // through and executed until reaches the break
        switch (num) { //switch statement will apply numeric promotion rule 2 as well

            // The default will be executed if the condition doesn't match with any case
            // but is optional, the switch case can without the default and will not print anything as well

            // default : xxxxxx , if default is at above, and switch statement is 20 , it will not print the default
            // case statement can only use final String or primitive type , other wrapper object cannot
            case A: // WINTER
                System.out.println("Switch Case: " + num.score); // num.getHours()
                break;
                // case 3 : 4, is not allowed, but this default : case 3 is allowed
                    // SUMMER
            case C: System.out.print("Switch case: " + num.score); // num.getHours()
             // we only can use literal values, local final variable and enum in case expression only, not local variable
                // and cannot use final method parameter
                break;
            default :
                break;

            // case 101: 102: This is not valid, will not compile, case expression cannot have multiple value without case
            // case 101 || 102 does not compile


        }
    }

    public static void main(String[] args){


        System.out.println();
        EnumClass.Season summer = EnumClass.Season.SUMMER;
        for(EnumClass.Season s: EnumClass.Season.values()){
            System.out.println(s.name() + " " + s.ordinal());
        }
        System.out.println("print expected visitor-----------");
        EnumClass.Season.SPRING.printExpectedVisitor();

        System.out.println(EnumClass.Season.SUMMER);

        System.out.println(EnumClass.Season2.WINTER); // this will just print WINTER
        System.out.println(EnumClass.Season2.WINTER.getHours());

        System.out.println(QUALITY.valueOf("B").score);

        switchCase(QUALITY.C);
        System.out.println();
        switchCase(QUALITY.valueOf("A"));

        //switchCase(Season2.WINTER);


    }


}
