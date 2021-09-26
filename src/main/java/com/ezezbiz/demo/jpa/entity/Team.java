package com.ezezbiz.demo.jpa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long teamId;
    private String teamName;
    @OneToMany(mappedBy = "team")
    private List<User> users;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team() {

    }

    public Long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", users=" + users +
                '}';
    }
}
