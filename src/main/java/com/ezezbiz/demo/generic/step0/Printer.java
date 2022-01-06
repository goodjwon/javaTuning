package com.ezezbiz.demo.generic.step0;

public class Printer {

    String message;

    public Printer() {
    }

    public Printer(String message) {
        this.message = message;
    }

    public void print(String message) {
        System.out.println(message + "Wawooo !!!!!");
    }
}
