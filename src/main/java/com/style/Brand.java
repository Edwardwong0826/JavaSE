package com.style;

public class Brand {
    private String name;

    public Brand(){}
    public Brand(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getQuote(){

        return 400;
    }

    @Override
    public String toString() {
        return "com.style.Brand{" +
                "name='" + name + '\'' +
                '}';
    }
}
