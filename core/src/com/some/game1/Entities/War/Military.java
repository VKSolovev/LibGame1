package com.some.game1.Entities.War;

import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.Regions.Region;

import java.io.Serializable;
import java.util.ArrayList;

public class Military implements Serializable {
    private double maxExp;
    private ArrayList<Army> armies = new ArrayList<>();
    private Gov gov;
    private WarMods warMods = new WarMods();
    private double tactic = BS.baseTactic;

    public Military(Gov gov) {
        this.gov = gov;
        maxExp = BS.baseExperience;
        createArmy(gov.getRegions().getRegions()[0]);
        armies.get(0).setGunAmount(1000);
        armies.get(0).setSize(500);
        armies.get(0).setTankAmount(100);

    }

    public void turn(){
        countExp();
        for (Army army: armies){
            army.setModifiers(warMods.getMods());
            army.turn(maxExp, tactic);
        }
        warMods.turn();
    }

    public void countExp(){
        tactic = BS.baseTactic + warMods.getMods()[0];
        maxExp = BS.baseExperience + warMods.getMods()[9] + tactic;
    }

    public ArrayList<Army> getArmiesInRegion(Region region){
        ArrayList<Army> res = new ArrayList<>();
        for (Army army: armies){
            if (army.getLocation() == region) {
                res.add(army);
            }
        }
        return res;
    }

    public void createArmy(Region region){
        if (BS.costOfCreation* (1 + warMods.getMods()[3]) < gov.getEconomy().getMoney() ) {
            gov.getEconomy().addMoney(-(int) (BS.costOfCreation* (1 + warMods.getMods()[3])));
            armies.add(new Army(BS.gunBase, BS.tankBase, BS.menBase, region, gov));
            armies.get(armies.size()-1).setModifiers(warMods.getMods());
        }
    }

    public int costCreate(){
        return (int) (BS.costOfCreation* (1 + warMods.getMods()[3]));
    }

    public WarMods getWarMods() {
        return warMods;
    }

    public double getTactic() {
        return tactic;
    }
}
