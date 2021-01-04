package com.ezezbiz.demo.pattern.strategy;

class UpBehavior implements CarMoveBehavior{
    @Override
    public void action() {
        System.out.println("Up!!");
    }
}

class DownBehavior implements CarMoveBehavior{
    @Override
    public void action() {
        System.out.println("Down!!");
    }
}

class LeftBehavior implements CarMoveBehavior{
    @Override
    public void action() {
        System.out.println("Left!!");
    }
}

class RightBehavior implements CarMoveBehavior{
    @Override
    public void action() {
        System.out.println("Right!!");
    }
}