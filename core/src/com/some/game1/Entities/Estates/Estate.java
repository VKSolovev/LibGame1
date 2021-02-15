package com.some.game1.Entities.Estates;


import com.some.game1.Entities.Lows.Law;
import com.some.game1.Entities.MainComponents.BS;

import java.io.Serializable;
import java.util.ArrayList;

public class Estate implements Serializable {

    private boolean[] constructLows(String ide){
        int numLows = BS.numLows;
        boolean[] res = new boolean[numLows];
        if (ide.equals("Communism")){
            res[0] = true;
            res[1] = false;
            name = "Narodnaya partia";
        }
        if (ide.equals("Liberalism")){
            res[0] = false;
            res[1] = true;
            name = "Fair Kvasia";
        }
        if (ide.equals("Socialism")){
            res[0] = false;
            res[1] = false;
            name = "Party of prosperity";
        }
        return res;

    }

    public Estate(int id, String ideology) {
        this.id = id;
        this.loyalty = 50;
        this.ideology = ideology;
        this.politics = constructLows(ideology);
        influence = new double[]{50, 50, 50, 50};
        estatesMods = new EstatesMods();
        changeLoyalty.add(""+50);
        changeLoyalty.add(""+0);
    }
    private int id;
    private String name;
    private double loyalty;
    private double[] influence;
    private double totInfluence;
    private String ideology;
    private boolean[] politics;
    private boolean ruleParty = false;
    private boolean support = false;
    private boolean suppress = false;
    private EstatesMods estatesMods;
    private ArrayList<String> changeLoyalty = new ArrayList<>();

    public void calcLoyalty(Law[] laws){
        changeLoyalty.clear();
        double stloy = loyalty;
        loyalty = estatesMods.getLoyaltyMod();
        for (Law law: laws){
            if (law.isActivated()){
                if (law.isAgainst()){
                    if (!politics[law.getPoliticId()]) {
                        loyalty += law.getChangeLoyaltyPro();
                    }
                    else {
                        loyalty += law.getChangeLoyaltyAgainst();
                    }
                }
                else
                {
                    if (politics[law.getPoliticId()]) {
                        loyalty += law.getChangeLoyaltyPro();
                    }
                    else {
                        loyalty += law.getChangeLoyaltyAgainst();
                    }
                }
            }

        }
        loyalty += BS.baseLoyalty;
        loyalty *= (1 + BS.baseTax - curTaxes) * taxMod;
        if (loyalty < 0){
            loyalty = 0;
        }
        if (ruleParty){
            loyalty += 20;
        }
        changeLoyalty.add(String.format("%.2f", loyalty));
        double change;
        if (loyalty > stloy){
            change = (loyalty-stloy)/10;
        } else {
            change = -(-loyalty+stloy)/10;
        }
        if (change < -5){
            change = -5;
        }
        if (change > 5){
            change = 5;
        }
        changeLoyalty.add(String.format("%.2f", change));
        loyalty = stloy + change;
    }

    private double taxMod = 1;
    private double potentialTaxMod = 1;
    private int time = 6;
    private double curTaxes = BS.baseTax;
    public void changeTaxes(double prev, double cur){
        time = 1;
        curTaxes = cur;
        if (ideology.equals("Communism")){
            if (cur > prev){
                taxMod = taxMod + (cur-prev)*(cur-prev)*100;
                potentialTaxMod = taxMod;
            } else {
                taxMod = taxMod - (cur-prev)*(cur-prev)*100;
                potentialTaxMod = taxMod;
            }
        } else if (ideology.equals("Liberalism")) {
            if (cur < prev){
                taxMod = taxMod + (cur-prev)*(cur-prev)*100;
                potentialTaxMod = taxMod;
            } else {
                taxMod = taxMod - (cur-prev)*(cur-prev)*100;
                potentialTaxMod = taxMod;
            }
        }
    }

    public void forgetTaxes(){
        time += 1;
        if (time > 5){
            potentialTaxMod = 1;
            taxMod = 1;
        } else {
            taxMod = (potentialTaxMod - 1) / time + 1;
        }
    }


    public void calcTotInfl(){
        totInfluence = 0;
        for (double i : influence){
            totInfluence += i;
        }
        totInfluence = 1.0* totInfluence / 4;
    }

    public void support(){
        estatesMods.activateID(2);
    }

    public void desupport(){
        estatesMods.deactivate(2);
    }

    public boolean[] getPolitics() {
        return politics;
    }

    public void suppress(){
        estatesMods.activateID(3);
    }

    public void desuppress(){
        estatesMods.deactivate(3);
    }

    public void setInfluence(int region, double value){
        influence[region] = value;
        calcTotInfl();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLoyalty() {
        return loyalty;
    }

    public double[] getInfluence() {
        return influence;
    }

    public double getTotInfluence() {
        return totInfluence;
    }

    public String getIdeology() {
        return ideology;
    }

    public boolean isSupport() {
        return estatesMods.isActive(2);
    }

    public boolean isSuppress() {
        return estatesMods.isActive(3);
    }

    public EstatesMods getEstatesMods() {
        return estatesMods;
    }

    public double getTaxMod() {
        return taxMod;
    }

    public void setRuleParty(boolean ruleParty) {
        this.ruleParty = ruleParty;
    }

    public boolean isRuleParty() {
        return ruleParty;
    }

    public ArrayList<String> getChangeLoyalty() {
        return changeLoyalty;
    }
}
