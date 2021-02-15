package com.some.game1.Entities.Regions;

import com.some.game1.Entities.Estates.Estate;
import com.some.game1.Entities.MainComponents.Gov;

import java.io.Serializable;

public class Regions implements Serializable {
    private Gov gov;

    public Regions(Gov gov) {
        for (int i = 0; i < 4; i++){
            regions[i] = new com.some.game1.Entities.Regions.Region(-1, i, gov);
        }
        this.gov = gov;
    }

    private com.some.game1.Entities.Regions.Region[] regions = new com.some.game1.Entities.Regions.Region[4];

    public int profit(){
        int res = 0;
        for (com.some.game1.Entities.Regions.Region value: regions){
            res += value.getPop()*value.getType();
        }
        return res;
    }

    public void turn(){
        for (com.some.game1.Entities.Regions.Region region: regions){
            region.getPopulation().turn();
            double infL = 0;
            double infD = 0;
            for (Estate estate: gov.getEstates().getEstates()){
                if (estate.getLoyalty() < 45){
                    infD += estate.getInfluence()[region.getId()] * (50 - estate.getLoyalty());
                }
                if (estate.getLoyalty() > 55){
                    infL -= estate.getInfluence()[region.getId()] * (50 - estate.getLoyalty());
                }

            }
            region.getUnrest().turn(infL, infD);
        }
    }


    public Region[] getRegions() {
        return regions;
    }
}
