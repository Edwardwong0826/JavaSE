package com.feature;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AssertionAndLocalization {

    public static void DateAndTime(){

        // exam only need to know how to format for date and time
        // Common date/time symbols
        // y - year, M - month, d - Day
        // h -hour, m - minute, s - seconds
        // a - am/pm, z - Time Zone Name
        // Z Time Zone Offset
        // LocalDate can use y,m,d , LocalTime can use h,m,s,a LocalDateTime combine use with z and Z
        // ZonedDateTime can use any symbols in format
        // use ' in .ofPattern() to escape the string you want to include , '' to escape '

        LocalDate date = LocalDate.of(2020,8,26);
        LocalTime time = LocalTime.of(5,10,10);

        // LocalDate and LocalTime when parse, in second parameter only can accept DateTimeFormatter.ISO_LOCAL_XXX related to own local type
        LocalDate date1 = LocalDate.parse("2020-04-30",DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime time1 = LocalTime.parse("05:20:30",DateTimeFormatter.ISO_LOCAL_TIME);

        LocalDateTime dt = LocalDateTime.of(date,time);
        var f = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");

        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));

        System.out.println(dt.format(f));
        System.out.println();


    }

    public static void Internationalization() {

        Locale locale = Locale.CHINA;
        System.out.println(locale);

        Locale l = new Locale("en","CN");

        // builder design pattern can set in any order for language and region
        Locale l1 = new Locale.Builder().setLanguage("en").setRegion("US").build();
        System.out.println(l1);

        // in NumberFormat.getInstance() we put getDefault() or put the area that we want
        NumberFormat us = NumberFormat.getInstance(Locale.getDefault());
        System.out.println(us.format(3_200_000/12));


        double price = 50.00;
        String s = "50.00";
        NumberFormat pl = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println(pl.format(price));

        NumberFormat pl2 = NumberFormat.getInstance(Locale.CHINA);


        try
        {
            // when is parse, it parses based on the locale , and throw checked exception
            System.out.println(pl2.parse(s));
        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
        }

        double d = 123456;
        // we can create our own number format, # omit the position if no digit exists for it, 0 put a 0 in the position if no digit exists for it
        NumberFormat f1 = new DecimalFormat("###,###.0");
        System.out.println(f1.format(d));

        LocalDate date = LocalDate.of(2020,8,26);

        // use setDefault can change both both Category.DISPLAY and Category FORMAT based on parameter
        Locale.setDefault(new Locale("us","US"));

        System.out.println();
    }

    public static void ResourceBundle(){

        Locale china = new Locale("zh","CH");
        Locale us = new Locale("en","US");

        // ResourceBundle use to convert the text based on file name and locale to translate, it stores in properties files in properties jar or
        // folder load in classpath in the runtime, changing the default locale only lasts for single run of a program, because is change in memory,
        // the resource bundle value are read from the ResourceBundle directly


        // Example of properties files
        // Zoo_en.properties
        // hello = hello
        // Zoo_zh.properties
        // hello = 你好
        // if when call ResourceBundle.getBundle("zh") with no country, it will move to default locale, and finally default resource bundle

        // Exam need to know the order for picking the resource bundle
        // 1. look for the resource bundle for requested locale, followed by the default locale
        // 2. for each locale , check language/ country , followed by language
        // 3. use the default locale if not matching locale can be found
        // 4. A locale that only country without language is in valid locale

        var rb = ResourceBundle.getBundle("Zoo",china); // or .getBundle("Zoo") to get by file by default locale
        System.out.println(rb.getString("hello") +" : " + rb.getString( "welcome"));

        // it also possible to use list with pairs with stream
        rb.keySet().stream().map(k-> k + ":" + rb.getString(k)).forEach(s ->System.out.println(s));

        // we can put substitute variables into middle of a resource bundle String using MessageFormat()
        // Zoo_zh.properties
        // helloByName = Hello, {0} and {1}

        String format = rb.getString("helloByName");
        System.out.println(MessageFormat.format(format,"你好","吗"));

        // we can use properties deal with ResourceBundle like HashMap, it uses String for Keys and Values

        var props = new Properties();
        props.setProperty("name","OurZoo");
        props.setProperty("open","10am");

        System.out.println(props.get("ticket")); // properties.get() will get back if exist and return null if not exist
        System.out.println(props.getProperty("ticket","ticket")); // properties.getProperty() if get value empty, if will return the default value

        System.out.println();
    }

    public static void Assertion(){


        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();

        // Assert syntax assert xxx_xxx; or assert xxx_xxx: message - xxx_xxx is Boolean expression
        // Assertions are default disabled, need to go RUN -> Edit configuration -> VM Options add -ea command


        // if exam when command line does not enable -ea, the AssertionError will not throw unless command line enable -ea / -enableassertions
        // -da / disableassertions to disable assertion , below was example of this class how to enable or disable
        // java -da -ea:AssertionAndLocalization AssertionAndLocalization / this disable for all class, but enable only for this class
        // java -ea -da:AssertionAndLocalization AssertionAndLocalization / this enable for all class, but disable only for this class

        // when use assert, we should never alter the value in assert because it could lead to different result
        // like assert value++ < 20;
        assert value >= 0 && value <= 20 : "Invalid number: " + value; // assert (xxx < xxx) or assert xxx < xxx
        assert(false) : "Invalid Input"; // we also can directly assert to false or true

        System.out.println("value: " + value);
        System.out.println();
    }


    public static void main(String[] args){

        DateAndTime();
        Internationalization();
        //Assertion();

        var d = new testAutoCloseable();
        try(d;var w = new testCloseable();)
        {
            System.out.println("T");
            //throw new RuntimeException("B"); - even here throw exception, it will not directly go to catch block first, it will go to close try-with resource class
        }
        catch (Exception e){
            System.out.println("E");

        }
        finally {
            System.out.println("F");
        }

    }
    // 1.B 2.AD 3.B 4.A 5.B 6.AB 7.F 8.AD 9.BE 10.CD 11.B 12.AB 13.CD 14.F 15.E 16.CD 17.EF 18.C 19.D 20.B 21.CDE 22.E 23.AE 24.E 25.C
}
