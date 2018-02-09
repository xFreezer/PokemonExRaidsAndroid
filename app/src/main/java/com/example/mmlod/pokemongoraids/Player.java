package com.example.mmlod.pokemongoraids;

/**
 * Created by MMlod on 07.02.2018.
 */

public class Player {
    private String id;
    private String username;
    private String team;
    private int lvl;
    private String permission;
    private String userId;

    public Player() {

    }

    public Player(String id, String userId, String username, String team, int lvl, String permission) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.team = team;
        this.lvl = lvl;
        this.permission = permission;
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
