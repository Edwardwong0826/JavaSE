package com.style;

public class AsiaTShirt {


    public AsiaTShirt(int age){}
    // below is the explicit constructor , if without explicit to add this constructor, any class that tries to extends this class
    // without created any constructor will calling the no args constructor provide by the JVM automatically with together
    // first line called parent Super() no args constructor , those subclass will failed to compile because super class/
    // class that have own constructor, JVM doesn't automatically generate no args constructor anymore
    public AsiaTShirt(){};

}
