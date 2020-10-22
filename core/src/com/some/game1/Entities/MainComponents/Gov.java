package com.some.game1.Entities.MainComponents;


import com.some.game1.Entities.Economy.Economy;
import com.some.game1.Entities.Estates.Estates;
import com.some.game1.Entities.Lows.Laws;
import com.some.game1.Entities.Regions.Region;
import com.some.game1.Entities.Regions.Regions;
import com.some.game1.Entities.War.Military;


public class Gov {
    public Gov() {
        estates = new Estates(this);
        laws = new Laws(this);
        regions = new Regions(this);
        economy = new com.some.game1.Entities.Economy.Economy(this);
        military = new Military(this);
        for (int i = 0; i < 11; i++){
            turn();
        }
        for (Region region: regions.getRegions()){
            region.getUnrest().totalPeace();
        }
    }

    private Estates estates;
    private Laws laws;
    private Regions regions;
    private com.some.game1.Entities.Economy.Economy economy;
    private Military military;
    private EventListener eventListener = new EventListener(this);


    private int influence=100;


    public void incInfl(){
        influence += 50;
        if (influence > 1000){
            influence = 1000;
        }
    }

    public void turn(){
        BS.turn += 1;
        incInfl();
        economy.turn();
        estates.turn();
        laws.turn();
        regions.turn();
        military.turn();
        eventListener.turn();
    }

    public void addInf(int i){
        influence += i;
        if (influence > 1000){
            influence = 1000;
        }
    }

    public Regions getRegions() {
        return regions;
    }

    public Estates getEstates() {
        return estates;
    }

    public Laws getLaws() {
        return laws;
    }

    public int getInfluence() {
        return influence;
    }

    public Economy getEconomy() {
        return economy;
    }

    public Military getMilitary() {
        return military;
    }

    public EventListener getEventListener() {
        return eventListener;
    }
}
