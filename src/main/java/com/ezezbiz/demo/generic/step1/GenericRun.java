package com.ezezbiz.demo.generic.step1;

public class GenericRun {
    public static void main(String[] args) {

        Printer<Dog> printer = new Printer<>(new Dog("초롱이"));
        Printer<Cat> catPrinter = new Printer<>(new Cat("나비는"));
        printer.print();
        catPrinter.print();

        shout("John");
        shout(47);
        shout(new Cat());
        shout("John", 47);
    }


    private static <T> void shout(T thingToShout){
        System.out.println(thingToShout+" !!!!");
    }

    private static <T, V> void shout(T t, V v){
        System.out.println(t);
        System.out.println(v);
    }
}
