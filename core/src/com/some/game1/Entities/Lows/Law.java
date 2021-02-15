package com.some.game1.Entities.Lows;



import com.some.game1.Entities.MainComponents.BS;

import java.io.Serializable;
import java.util.ArrayList;

public class Law implements Serializable {
    public Law(String name, String ideology, int polId, boolean against, int id, int cLP, int clA) {
        this.name = name;
        this.politic = ideology;
        activated = false;
        this.against = against;
        this.id = id;
        changeLoyaltyAgainst = clA;
        changeLoyaltyPro = cLP;
        politicId = polId;
        info.add("Law Id "+id);
        info.add(name);
        info.add("Related politic " + ideology);
        info.add("Cooldown is 0");
        info.add("Law isn't active");
    }

    private String command;
    private int numSphere = -2;
    public void analise(String next){
        if (next.equals("Increase costs") || command.equals("Increase costs")){
            command = "Increase costs";
            if (numSphere == -2){
                numSphere = -1;
            } else if (numSphere == -1){
                numSphere = Integer.parseInt(next);
            } else if (numSphere > -1){
                increaseCosts[numSphere] = Double.parseDouble(next);
                command = "";
                info.add("Increase costs " + BS.economySpheres.get(numSphere-1)
                        + " on " +increaseCosts[numSphere]);
                numSphere = -2;
            }
        }


    }

    private int id;
    private String name;
    private String politic;
    private int politicId;
    private boolean against;
    private boolean activated;
    private int[] influence;
    private int cooldown = 0;
    private int changeLoyaltyPro;
    private int changeLoyaltyAgainst;
    private ArrayList<String> info = new ArrayList<>();

    //extra features
    private double[] increaseCosts = new double[BS.numberEconomySpheres];

    public boolean reform(){
        if (cooldown > 0){
            return false;
        }
        if (activated){
            activated = false;
        }
        else{
            activated = true;
        }
        cooldown = 5;
        return true;
    }

    public boolean accept(){
        if (!activated && !(cooldown > 0)){
            activated = true;
            cooldown = BS.baseCoolDownLawChange;
            return true;
        } else {
            return false;
        }
    }

    public boolean deny(){
        if (activated && !(cooldown > 0)){
            activated = false;
            cooldown = BS.baseCoolDownLawChange;
            return true;
        } else {
            return false;
        }
    }

    public void turn(){
        if (cooldown > 0){
            cooldown -= 1;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPolitic() {
        return politic;
    }

    public boolean isAgainst() {
        return against;
    }

    public boolean isActivated() {
        return activated;
    }

    public int[] getInfluence() {
        return influence;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getChangeLoyaltyPro() {
        return changeLoyaltyPro;
    }

    public int getChangeLoyaltyAgainst() {
        return changeLoyaltyAgainst;
    }

    public int getPoliticId() {
        return politicId;
    }

    public double[] getIncreaseCosts() {
        return increaseCosts;
    }

    public ArrayList<String> getInfo() {
        info.set(3, "Cooldown is " + cooldown);
        info.set(4, "Activated: " + activated);
        return info;
    }
}
