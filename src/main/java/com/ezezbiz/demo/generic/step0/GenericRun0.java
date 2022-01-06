package com.ezezbiz.demo.generic.step0;

public class GenericRun0 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();

        Printer printer = new Printer();
        printer.print(cat.getName());
        printer.print(dog.getName());

    }
}
