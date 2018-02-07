package com.example.mmlod.pokemongoraids;


import java.util.ArrayList;

/**
 * Created by MMlod on 03.02.2018.
 */

public class GymAction {
    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private ArrayList<String> raidsId;

    public GymAction(){

    }

    public GymAction(String id, String name, String startDate, String endDate, ArrayList<String> raidsId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.raidsId = raidsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startString) {
        this.startDate = startString;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<String> getRaidsId() {
        return raidsId;
    }

    public void setRaidsId(ArrayList<String> raidsId) {
        this.raidsId = raidsId;
    }
}
