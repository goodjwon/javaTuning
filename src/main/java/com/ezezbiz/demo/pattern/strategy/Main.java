package com.ezezbiz.demo.pattern.strategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("================Java Strategy Pattern=================");

        //Up
        Car car1 = new Car(new UpBehavior());
        car1.move();

        //Down
        Car car2 = new Car(new DownBehavior());
        car2.move();

        //Left
        car2.setCarMoveBehavior(new LeftBehavior());
        car2.move();

        //Right
        car2.setCarMoveBehavior(new RightBehavior());
        car2.move();
    }
}
