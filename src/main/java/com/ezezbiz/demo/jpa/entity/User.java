package com.ezezbiz.demo.jpa.entity;

import javax.persistence.*;

@Entity
public class User {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    @Column(name = "CAREER_OK", nullable = false, length = 60)
    private String career;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(int id, String firstNamne, String lastName, String career, UserType userType) {
        this.id = id;
        this.firstName = firstNamne;
        this.lastName = lastName;
        this.career = career;
        this.userType = userType;

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
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", career='" + career + '\'' +
                ", userType=" + userType +
                '}';
    }
}
