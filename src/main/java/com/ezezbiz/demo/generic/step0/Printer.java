package com.ezezbiz.demo.generic.step0;

public class Printer<T extends Animal> {

    T name;

    public Printer(T name){
        this.name = name;
    }

    public void print() {
        System.out.println(name.name() + "는 Wawooo!!!!");
    }

}
