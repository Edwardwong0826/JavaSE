package com.style;

public interface Cleanable{

     void doClean();

     void m1(int i);

     default int getNum(){
          return 1000;
     }

}
