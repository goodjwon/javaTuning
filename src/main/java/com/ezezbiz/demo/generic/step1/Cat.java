package com.ezezbiz.demo.generic.step1;

public class Cat extends Animal {
    private String litterPreference;

    public Cat() {
        this.name = "Nabi";
    }

    public Cat(String name){
        this.name = name;
    }

    public String getLitterPreference(){
        return litterPreference;
    }

    public void setLitterPreference(String litterPreference) {
        this.litterPreference = litterPreference;
    }
}
