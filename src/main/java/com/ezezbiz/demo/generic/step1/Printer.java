package com.ezezbiz.demo.generic.step1;

public class Printer <T extends Animal> {

    T thingToPrint;

    public Printer(T thingToPrint){
        this.thingToPrint = thingToPrint;
    }


    public void print() {
        System.out.println(thingToPrint.bark() + "!");
    }


}
