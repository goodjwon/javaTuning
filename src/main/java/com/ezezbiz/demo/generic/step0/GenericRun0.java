package com.ezezbiz.demo.generic.step0;

public class GenericRun0 {
    public static void main(String[] args) {
        Printer<Dog> printer = new Printer<>(new Dog("멍멍이는"));

        printer.print();
    }
}