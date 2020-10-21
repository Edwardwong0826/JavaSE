package com.feature;

public interface GenericInterfaces<T> {

    // there is three way class can implementing the generic interface
    // 1. class xxx implements xxx<Jeans> - this means concrete class deals only with the specific class here
    // 2. class xxx implements xxx<T>  - this means concrete class allow caller to specify the type of generic
    // 3. class xxx implements xxx - this is not use generics at all, does compile will have Object parameter for
    // overriding method
    void ship(T t);

}
