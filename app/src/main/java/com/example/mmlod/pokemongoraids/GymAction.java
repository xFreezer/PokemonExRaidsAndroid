package com.example.mmlod.pokemongoraids;


/**
 * Created by MMlod on 03.02.2018.
 */

public class GymAction {
    private String name;
    private String startDate;
    private String endDate;

    public GymAction(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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
}
