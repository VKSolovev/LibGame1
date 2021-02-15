package com.some.game1.Entities.Politics;

import com.some.game1.Entities.Estates.Estates;

import java.io.Serializable;

public class Politics implements Serializable {
    public Politics() {
        int numPol = 2;
        politics = new Politic[numPol];
        politics[0] = new Politic("Militarism", 0, 60);
        politics[1] = new Politic("Free market", 1, 60);
    }

    private Politic[] politics;
    private boolean support = false;

    public double getBP(boolean[] oppLows, Estates estates){
        double res = 0;
        for (int i=0; i < politics.length; i++){
            if (oppLows[i]){
                res += 1.0* politics[i].getSupportShare() /estates.numEstPol(i);
            }
            else{
                res += 1.0*(100 - politics[i].getSupportShare())/(estates.getEstates().length - estates.numEstPol(i));
            }
        }
        return res/politics.length;
    }

    public void support(String name, boolean against){
        if (support){
            for (Politic pol: politics){
                pol.desupport();
            }
        }
        support = true;
        for (Politic pol: politics){
            if (pol.getName().equals(name)){
                pol.setSupport(against);
            }
        }
    }

    public void unSupport(String name, boolean against){
        support = false;
        for (Politic pol: politics){
            pol.desupport();
        }
    }

    public Politic getPolitic(String name){
        for (Politic pol: politics){
            if (pol.getName().equals(name)){
                return pol;
            }
        }
        return null;
    }

    public String supportOld(String name, boolean against){
        int ag = 1;
        if (against){
            ag = -1;
        }
        if (support){
            return "You have supported some politics";
        } else {
            for (Politic pol: politics){
                if (pol.getName().equals(name)){
                    pol.addShare(ag*(Math.sqrt(Math.abs(50-pol.getSupportShare()))+0.5));
                    support = true;
                    return "Success";
                }
            }
        }
        return "There no such politic";
    }

    public void turn(){
        for (Politic politic: politics){
            politic.turn();
        }
        support = false;
    }

    public Politic[] getPolitics() {
        return politics;
    }

    public boolean isSupport() {
        return support;
    }
}
