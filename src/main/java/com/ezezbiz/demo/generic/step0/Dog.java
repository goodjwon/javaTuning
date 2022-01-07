package com.ezezbiz.demo.generic.step0;

public class Dog extends Animal {
    public Dog() {
        this.name = "멍멍이";
    }

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
