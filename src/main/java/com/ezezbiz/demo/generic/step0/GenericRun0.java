package com.ezezbiz.demo.generic.step0;

public class GenericRun0 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();

        CatPrinter catPrinter = new CatPrinter();
        DogPrinter dogPrinter = new DogPrinter();

        catPrinter.print(cat);
        dogPrinter.print(dog);

        GenPrinter genPrinter = new GenPrinter();

        genPrinter.print(cat);
        genPrinter.print(dog);


    }
}
