package com.ezezbiz.demo.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long teamId;
    private String teamName;
    @OneToMany(mappedBy = "team")
    private List<User> users = new ArrayList<User>();

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
}
