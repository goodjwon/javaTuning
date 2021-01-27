package com.ezezbiz.demo.jpa.entity;

import javax.persistence.*;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    @Column(name = "CAREER_OK", nullable = false, length = 60)
    private String career;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public User(String firstName, String lastName, String career, UserType userType, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.career = career;
        this.userType = userType;
        this.team = team;
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

    public UserType getUserType() {
        return userType;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", career='" + career + '\'' +
                ", userType=" + userType +
                ", team=" + team +
                '}';
    }
}
