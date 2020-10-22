package com.some.game1.Entities.Politics;


import com.some.game1.Entities.Estates.Estate;
import com.some.game1.Entities.Estates.Estates;
import com.some.game1.Entities.MainComponents.BS;

public class Population {
    private int population;
    private Politics politics;

    public Population(int population) {
        this.population = population;
        politics = new Politics();
    }

    public int getPopulation() {
        return population;
    }

    public void turn(){
        if (BS.turn %12 == 1) {
            if (population < 1000) {
                population *= (1.01 + (1000.0 - population) * (1000.0 - population) / 100000);
            } else {
                population *= 1.01;
            }
        }
        politics.turn();
    }

    public double getBP(boolean[] oppLows, Estates estates){
        return politics.getBP(oppLows, estates);
    }

    public Politics getPolitics() {
        return politics;
    }
}
