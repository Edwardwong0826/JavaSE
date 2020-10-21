package com.life;

public enum EnumTest {

    LA("Basic"), Boston("loan"), Paris("Full"), KL("Full");

    private String serviceLevel;
    EnumTest(String serviceLevel){
        this.serviceLevel = serviceLevel;

    }

    public String getServiceLevel() {
        return serviceLevel;
    }
}
