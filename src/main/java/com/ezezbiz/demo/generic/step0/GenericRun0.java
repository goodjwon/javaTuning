package com.ezezbiz.demo.generic.step0;

public class GenericRun0 {
    public static void main(String[] args) {
        Printer<Dog> printDog = new Printer<>(new Dog("멍멍이"));
        Printer<Cat> printCat = new Printer<>(new Cat("나비"));
        printDog.print();
        printCat.print();
    }
}