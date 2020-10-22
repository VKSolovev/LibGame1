package com.some.game1.Entities.Regions;

import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.Technical.Texts.Event;
import com.some.game1.Entities.War.Army;
import com.some.game1.Screens.MainScreen;

import java.util.ArrayList;

public class Unrest {
    private int discontent = 0;
    private int loyalty = 0;
    private boolean unrest;
    private boolean revolt;
    private ArrayList<String> riseDis = new ArrayList<>();
    private ArrayList<String> riseLoy = new ArrayList<>();
    private int gunsAmount = 0;
    private int tanksAmount;
    private com.some.game1.Entities.Regions.Region region;
    private int revoltStrength = 0;
    private int revoltSize = 0;
    private double morale;
    private com.some.game1.Entities.Regions.KeyPlace[] keyPlaces;
    private double experience;
    private Gov gov;
    private String lastFight = "";
    private double riseD = 0;
    private double riseL = 0;

    public Unrest(Region region, Gov gov) {
        this.region = region;
        this.gov = gov;
    }

    public void turn(double inflL, double inflO){
        recountGovStr();
        keyPlacesConstruct(region.getType());
        riseD = 4.0*inflO/150;
        riseL = 1.0*inflL/100;
        discontent += riseD + BS.baseRiseUnrest;
        loyalty += riseL + BS.baseRiseLoy;
        if (discontent < 0){
            discontent = 0;
        }
        if (loyalty < 0){
            loyalty = 0;
        }
        gunsAmount += 1.0*(region.getPop() -gunsAmount)/10 + (int) (Math.random() * 5) + 5;
        updateInfo();
        unrest();
        revolt(inflO);
        if (unrest){
            discontent += 3;
            riseDis.add("Discontent more than 50 (5)");
        }
        fight();
        if (morale > 150){
            morale = 150;
        }
        recountStr();
        recountGovStr();
    }

    private void updateInfo(){
        riseDis.clear();
        riseDis.add("Base rise (" + BS.baseRiseUnrest + ")");
        riseDis.add("Disloyal parties (" + riseD + ")");
        riseLoy.clear();
        riseLoy.add("Base rise (" + BS.baseRiseLoy + ")");
        riseLoy.add("Loyal parties (" + riseL + ")");
    }

    private void keyPlacesConstruct(int type){
        if (type == 0){
            keyPlaces = new com.some.game1.Entities.Regions.KeyPlace[4];
            keyPlaces[0] = new com.some.game1.Entities.Regions.KeyPlace(1,0);
            keyPlaces[1] = new com.some.game1.Entities.Regions.KeyPlace(1,1);
            keyPlaces[2] = new com.some.game1.Entities.Regions.KeyPlace(1,2);
            keyPlaces[3] = new com.some.game1.Entities.Regions.KeyPlace(4,3);
        }
        if (type == 1){
            keyPlaces = new com.some.game1.Entities.Regions.KeyPlace[6];
            keyPlaces[0] = new com.some.game1.Entities.Regions.KeyPlace(1,0);
            keyPlaces[1] = new com.some.game1.Entities.Regions.KeyPlace(3,1);
            keyPlaces[2] = new com.some.game1.Entities.Regions.KeyPlace(3,2);
            keyPlaces[3] = new com.some.game1.Entities.Regions.KeyPlace(4,3);
            keyPlaces[4] = new com.some.game1.Entities.Regions.KeyPlace(4,4);
            keyPlaces[5] = new com.some.game1.Entities.Regions.KeyPlace(4,5);
        }
        if (type == 2){
            keyPlaces = new com.some.game1.Entities.Regions.KeyPlace[4];
            keyPlaces[0] = new com.some.game1.Entities.Regions.KeyPlace(1,0);
            keyPlaces[1] = new com.some.game1.Entities.Regions.KeyPlace(3,1);
            keyPlaces[2] = new com.some.game1.Entities.Regions.KeyPlace(2,2);
            keyPlaces[3] = new KeyPlace(2,3);
        }
    }

    private void unrest(){
        if (discontent > 50 && !unrest && !revolt && discontent > loyalty){
            if (Math.random()*100 > discontent - 50){
                if (!unrest){
                    Event revol = BS.events.get(2).clone();
                    revol.setAddingName(" " + region.getId());
                    MainScreen.events.add(revol);
                }
                unrest = true;
            }
        }
    }

    private int totStr = 0;

    private void recountGovStr(){
        totStr = 0;
        for (Army army: gov.getMilitary().getArmiesInRegion(region)){
            totStr += army.getStrength() * (army.getMorale())/100;
        }
    }

    private void fight(){
        if (revolt){
            System.out.println("fight");
            int initiative = (int) (Math.random() * 3); // 2 - government attack, 1 - revolt army attack, 0 - nothing
            double strRate = 1.0*totStr/revoltStrength;
            System.out.println("totStr" + totStr);
            if (revoltStrength < 1.0*totStr/10){
                revolt = false;
                unrest = false;
                discontent = 0;
                return;
            }
            lastFight = "Nothing happened";
            String result = "";
            String initi = "";
            //TODO change it
            if (initiative > 0){
                if (initiative == 2){
                    initi = "Government attacks and";
                    strRate /= 1.5;
                } else {
                    initi = "Government defends and ";
                    strRate *= 1.5;
                }
                spendGuns(strRate);
                if (strRate > 1){//победили войска правительства
                    endBattle(strRate, 2);
                    for (Army army: gov.getMilitary().getArmiesInRegion(region)){
                        army.endBattle(1.0/strRate, 0.5, loseGun(strRate));
                    }
                    result = "win " + strRate;
                } else {
                    endBattle(strRate, 0.5);
                    int totAG = 0;
                    for (Army army: gov.getMilitary().getArmiesInRegion(region)){
                        totAG += army.endBattle(1.0/strRate, 2, 0);;
                    }
                    gunsAmount += totAG;
                    result = "lose " + strRate;
                }
                lastFight = initi + result;
                String[] res = new String[1];
                if (result.equals("lose " + strRate)){
                    res[0] = "Damn";
                } else {
                    res[0] = "Perfect";
                }
                Event fight = BS.events.get(4).clone();
                fight.setAddingName(" " + region.getId());
                fight.setAnswers(res);
                fight.setText(lastFight);
                MainScreen.events.add(fight);
            }

            if (morale < 20){
                revolt = false;
            }
        }
    }

    private void endBattle(double defeat, double win){
        revoltSize -= revoltSize * defeat * win /20;
        if (defeat <= 1){
            morale += 10*(1-defeat);
        } else {
            morale -= 10*(defeat - 1);
        }
    }

    private void spendGuns(double defeat){
        double loseRate = 0.8;
        if (defeat > 9.5){
            loseRate = 0.99;
        } else {
            loseRate += defeat/50;
        }
        gunsAmount *= loseRate;
    }

    private int loseGun(double defeat){
        int lose = (int) (gunsAmount * 0.1 * defeat);
        gunsAmount -= lose;
        return lose/4;
    }

    private void recountStr(){
        revoltStrength = (int) (revoltSize* Math.exp(experience) * (Math.log10(1.0*gunsAmount/revoltSize + 1)+
                Math.log10(BS.tankEff * experience *tanksAmount/revoltSize + 1)));
    }

    private void revolt(double inflO){
        if (discontent > 100 && !revolt){
            Event revol = BS.events.get(3).clone();
            revol.setAddingName(" " + region.getId());
            MainScreen.events.add(revol);
            revolt = true;
            revoltSize = (int) (region.getPop() * inflO);
            experience = BS.baseExperience/5;
            recountStr();
            morale = 100;
        }
    }

    public boolean isUnrest() {
        return unrest;
    }

    public boolean isRevolt() {
        return revolt;
    }

    public String getLastFight() {
        return lastFight;
    }

    public ArrayList<String> info(){
        ArrayList<String> res = new ArrayList<>();
        res.add("Discontent " + discontent);
        res.add("Loyalty " + loyalty);
        res.add( "Gun amount " + gunsAmount);
        res.add("Tank amount " + tanksAmount);
        res.add("Current situation is peace");
        if (unrest){
            res.set(4, "Current situation is unrest");
        }
        if (revolt){
            res.set(4, "Current situation is revolt");
            res.add("Revolt strength " + revoltStrength);
            res.add("Current morale " + morale);
        }
        res.add("Info about disloyal");
        res.addAll(riseDis);
        res.add("Info about loyal");
        res.addAll(riseLoy);

        return res;
    }

    public void totalPeace(){
        unrest = false;
        revolt = false;
        loyalty = 0;
        discontent = 0;
    }

    public int getTotStr() {
        return totStr;
    }
}
