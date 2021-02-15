package com.some.game1.Entities.Politics;

import java.io.Serializable;

public class Politic implements Serializable {
    public Politic(String name, int id, double supportShare) {
        this.name = name;
        this.id = id;
        this.supportShare = supportShare;
    }

    private double supportShare;
    private String name;
    private int id;
    private boolean support = false;
    private boolean against = false;

    public void setSupport(boolean against){
        this.against = against;
        support = true;
    }

    private void support(){
        if (support) {
            int ag = 1;
            if (against) {
                ag = -1;
            }
            addShare(ag * (Math.sqrt(Math.abs(50 - getSupportShare())) + 0.5));
        }
    }

    public void desupport(){
        support = false;
    }

    public double getSupportShare() {
        return supportShare;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void turn(){
        supportShare += Math.random()-0.5;
        if (supportShare > 100){
            supportShare = 100;
        }
        if (supportShare < 0){
            supportShare = 0;
        }
        support();
    }

    public void addShare(double value){
        supportShare += value;
        if (supportShare >100){
            supportShare = 100;
        }
    }

    public boolean isSupport() {
        return support;
    }

    public boolean isAgainst() {
        return against;
    }
}
