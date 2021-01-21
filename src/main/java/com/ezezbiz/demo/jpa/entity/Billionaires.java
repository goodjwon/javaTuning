package com.ezezbiz.demo.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Billionaires {
    @Id
    private int id;
    private String firstNamne;
    private String lastName;
    private String career;

    public Billionaires(int id, String firstNamne, String lastName, String career) {
        this.id = id;
        this.firstNamne = firstNamne;
        this.lastName = lastName;
        this.career = career;
    }

    public Billionaires() {

    }

    public int getId() {
        return id;
    }

    public String getFirstNamne() {
        return firstNamne;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCareer() {
        return career;
    }
}
