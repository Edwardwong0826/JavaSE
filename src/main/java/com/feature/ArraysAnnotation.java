package com.feature;

import com.feature.ArrayAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// container class is use to allow to have same annotation class with different values
@Target({ElementType.METHOD}) // container class should have same targets with @Repeatable annotation
@Retention(RetentionPolicy.RUNTIME) // same as above
public @interface ArraysAnnotation {

    // Even the annotation is array value of element, only one [] bracket require in here
    // does not required default value, only single value() element
    ArrayAnnotation[] value();

    // /**
    //  * Method to xxxx
    //  * @deprecated Use xxx.xxx (String... data) instead - use Javadoc annotation to give alternatives
    //  */
    //@Deprecated(since="1.8", forRemoval=true)
    //public void plan() {}


}
