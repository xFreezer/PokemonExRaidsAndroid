package com.example.mmlod.pokemongoraids;

import java.util.ArrayList;

/**
 * Created by MMlod on 07.02.2018.
 */

public class Group {
    private String id;
    private boolean open;
    private ArrayList<String> playersId;
    private ArrayList<Boolean> premiumPass;

    public Group() {

    }

    public Group(String id, boolean open, ArrayList<String> playersId, ArrayList<Boolean> premiumPass) {
        this.id = id;
        this.open = open;
        this.playersId = playersId;
        this.premiumPass = premiumPass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public ArrayList<String> getPlayersId() {
        return playersId;
    }

    public void setPlayersId(ArrayList<String> playersId) {
        this.playersId = playersId;
    }

    public ArrayList<Boolean> getPremiumPass() {
        return premiumPass;
    }

    public void setPremiumPass(ArrayList<Boolean> premiumPass) {
        this.premiumPass = premiumPass;
    }
}
