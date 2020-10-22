package com.some.game1.Entities.Regions;


import com.some.game1.Entities.Estates.Estates;
import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.Politics.Politic;
import com.some.game1.Entities.Politics.Population;

public class Region {
    private int type; //farm - 0, city - 1, mines - 2
    private int domEstate;
    private int id;
    private Population population;
    private Unrest unrest;

    public Region(int domEstate, int id, Gov gov) {
        this.type = (int) (Math.random()*(2+1));
        this.domEstate = domEstate;
        this.id = id;
        unrest = new Unrest(this, gov);
        population = new Population((int) (Math.random()*1000 +200));
    }

    public double getBP(boolean[] oppLows, int id, Estates estates){
        double domMod = 1;
        if (id == 0){
            domMod += 0.2;
        }
        if (domEstate == id){
            return population.getBP(oppLows, estates) * 1.1 * domMod;
        }
        else{
            return population.getBP(oppLows, estates) * domMod;
        }
    }

    public String[] getData(){
        String[] res = new String[2];
        res[0] = "Type of region is ";
        if (type == 0) {
            res[0] += "farming lands";
        } else if (type == 1){
            res[0] += "city";
        } else if (type == 2){
            res[0] += "mines";
        }
        res[1] = "Total population is " + population.getPopulation();
        return res;
    }

    public String[] getPolitics(){
        String[] res = new String[BS.numPolitics];
        for (Politic politic: population.getPolitics().getPolitics()){
            res[politic.getId()] = politic.getName() + " is supported by " + politic.getSupportShare() + "%";
        }
        return res;
    }

    public int getType() {
        return type;
    }

    public int getPop() {
        return population.getPopulation();
    }

    public Population getPopulation() {
        return population;
    }

    public int getDomEstate() {
        return domEstate;
    }

    public int getId() {
        return id;
    }

    public Unrest getUnrest() {
        return unrest;
    }
}
