package com.example.mmlod.pokemongoraids;

/**
 * Created by MMlod on 07.02.2018.
 */

public class Player {
    private String id;
    private String username;
    private String team;
    private int lvl;
    private String perrmision;

    public Player() {

    }

    public Player(String id, String username, String team, int lvl, String perrmision) {
        this.id = id;
        this.username = username;
        this.team = team;
        this.lvl = lvl;
        this.perrmision = perrmision;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getPerrmision() {
        return perrmision;
    }

    public void setPerrmision(String perrmision) {
        this.perrmision = perrmision;
    }
}
