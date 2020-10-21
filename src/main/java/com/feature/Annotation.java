package com.feature;


import java.lang.annotation.*;

// @xxx annotation can be in the same of declaration or not same, either was acceptable
// public or package-private(default) access modifier, annotation can ce applies in many places
enum UnitOfTemp {C,F}
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)//stored in.class file and but not available at runtime(default compiler behaviour)
@Inherited // subclass will inherit the annotation type and information found in the parent class
public @interface Annotation {

    // Annotation variable implicitly public, static and final
    public static final int MAX_VOLTAGE = 18;

    // 1. if Annotation does not have any element, is called Marker Annotation
    // 2. Annotation support primitive type, A String, a Class, an enum , annotation, an array of these types
    // 3. Annotation element are implicitly to be public , try to modified access will compile error
    // 4. final, static modifier are not allowed in value element
    // 5. Annotation element return type cannot be void
    // 6. An data that are not likely to changed should be as metadata, attribute data as part of object will
    //    changed frequently, not suitable as annotation metadata

    @ArrayAnnotation(genres={"EMD"})
    public abstract int startHour() default 6; //-- this called Optional element

    // this is element in Annotation, although looks like a lot of abstract method
    // for an element to be optional, need to included default value, the default value must be a non-null
    // constant expression, cannot be like new String("") or null
    int hoursPerDay() default 2;

    // this is the only way to create Annotation element value with Annotation
    SuppressWarnings warning() default @SuppressWarnings("no need ");


    // Java will covert shorthand notation to long notation
    // Example @Annotation("Goes") is equal to @Annotation(value="Goes")
    // when use shorthand notation, need to no require element and with element named value()
    // default value of annotation must be a non-null constant expression
    String[] value() default "No";

    //Element type that supported
    // 1. primitive types, String, Class and enum, another annotation, or an array of these types
    // 2. [] is supported, [][] not supported
    // 3. element type default value can be enum like enum.XXXX

    UnitOfTemp unit() default UnitOfTemp.C;

    //1.E 2.DF 3.DE 4.CD 5.B 6.EG 7.ABCDEF 8.G 9.ACD 10.C 11.ADF 12.BCD 13.AD 14.G 15.C 16.F 17.E 18.CDEF
    // 19.AF 20.G 21.G 22.F 23.B 24.E 25.C
}
