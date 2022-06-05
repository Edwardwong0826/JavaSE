package com.cool.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Reflection7 {

    public void test1(Map<String, User> map, List<User> list){

        System.out.println("test01");

    }

    public Map<String, User> test2(){
        System.out.println("test2");
        return null;
    }

    // get generic types
    public static void main(String[] args) throws NoSuchMethodException {

        Method method = Reflection7.class.getMethod("test1", Map.class, List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();

        for (Type genericParameterType : genericParameterTypes) {
            System.out.println(genericParameterType);


            if(genericParameterType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();

                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("actual type argument: " + actualTypeArgument);
                }
            }
            System.out.println();
        }
    }
}
