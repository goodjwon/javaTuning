package com.ezezbiz.demo.pattern.strategy;

public class Car {
    private CarMoveBehavior carMoveBehavior;

    public Car(CarMoveBehavior carMoveBehavior) {
        this.carMoveBehavior = carMoveBehavior;
    }

    public void move() {
        carMoveBehavior.action();
    }

    public void setCarMoveBehavior(CarMoveBehavior carMoveBehavior) {
        this.carMoveBehavior = carMoveBehavior;
    }
}
