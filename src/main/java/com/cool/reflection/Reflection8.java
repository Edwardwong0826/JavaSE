package com.cool.reflection;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class Reflection8 {
    public static void main(String[] args) throws NoSuchMethodException {

        // we can use reflection to annotations from class, field or method, below was the example

        // use reflection to get class annotations
        Annotation[] annotations = Person.class.getAnnotations();
        System.out.println(Arrays.toString(annotations));

        // use reflection to get method annotations
        Annotation[] annotations1 = User.class.getMethod("testMethodAnnotation").getAnnotations();
        System.out.println(Arrays.toString(annotations1));

    }
}
