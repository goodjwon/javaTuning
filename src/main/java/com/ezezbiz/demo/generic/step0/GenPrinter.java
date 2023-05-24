package com.ezezbiz.demo.generic.step0;

public class GenPrinter <T extends Animal> {

    public GenPrinter() {
    }

    T t;
    public GenPrinter(T t) {
        this.t = t;
    }

    public void print(T t) {
        System.out.println(t.getName()+ "!");
    }
}
