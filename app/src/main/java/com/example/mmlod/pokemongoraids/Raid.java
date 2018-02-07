package com.example.mmlod.pokemongoraids;

import java.util.ArrayList;

/**
 * Created by MMlod on 07.02.2018.
 */

public class Raid {
    private String id;
    private String date;
    private String time;
    private int tier;
    private String pokemon;
    private ArrayList<String> groupsId;

    public Raid() {

    }

    public Raid(String id, String date, String time, int tier, String pokemon, ArrayList<String> groupsId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.tier = tier;
        this.pokemon = pokemon;
        this.groupsId = groupsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }

    public ArrayList<String> getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(ArrayList<String> groupsId) {
        this.groupsId = groupsId;
    }

    public int points(){
        int points = 0;
        //TODO function to find number of players in raid
        return points;
    }

    public int numberOfGroups(){
        int numberOfGroups = groupsId.size();
        return numberOfGroups;
    }
}
