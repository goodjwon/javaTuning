package com.ezezbiz.demo.generic.step0;

public class Printer<T extends Dog> {

    T dog;

    public Printer(T bark){
        this.dog = bark;
    }

    public void print() {
        System.out.println(dog.name() + " Wawooo!!!!");
    }

}
