package com.ezezbiz.demo.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String career;

    public User(int id, String firstNamne, String lastName, String career) {
        this.id = id;
        this.firstName = firstNamne;
        this.lastName = lastName;
        this.career = career;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCareer() {
        return career;
    }

    @Override
    public String toString() {
        return "Billionaires{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", career='" + career + '\'' +
                '}';
    }
}
