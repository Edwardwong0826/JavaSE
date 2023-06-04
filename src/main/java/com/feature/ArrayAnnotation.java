package com.feature;

import java.lang.annotation.*;

// Rules of Annotation

// 1.Annotation ascribes custom information on the declaration where is defined
// 2.Annotation aren't utilized where they are defined
// 3.Annotation are optional metadata by themselves do not do anything

// Annotation-Specific Annotations as below

// Target value TYPE_USE can used anywhere there is
// java type with a few exception to be used only method return value or methods return void still need
// METHOD value define the annotation

@Target({ElementType.METHOD,ElementType.TYPE})

// Annotation may discarded by compile or at runtime, we can specify how they are handled by using @Retention
@Retention(RetentionPolicy.RUNTIME)  // stored in .class file and available at runtime
// @Retention(RetentionPolicy.SOURCE) // used only in the source file, discarded by compile

// if want to have more than one annotation to be applied, @Repeatable is required
@Repeatable(ArraysAnnotation.class)
// @Deprecated - this can applied almost all declarations
// @Documented

public @interface ArrayAnnotation {

    // Annotations support shorthand notation for providing an array contains single element
    // @ArrayAnnotation(genres = {}) or @ArrayAnnotation(genres = "xxx") also compile
    String[] genres() default {"Singer"};

}
