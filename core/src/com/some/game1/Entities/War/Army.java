package com.some.game1.Entities.War;
/*
turn
fight
move
whatBetter
addTank
addGun
addMen
 */

import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.Regions.Region;

public class Army {
    private int gunAmount;
    private int tankAmount;
    private int size;
    private double experience;
    private double strength;
    private int morale;
    private Region location;
    private int way = 0;
    private boolean onAWay = false;
    private double tactic = 0;
    private Gov gov;

    //modifiers
    private double[] modifiers;

    public Army(int gunAmount, int tankAmount, int size, Region location, Gov gov) {
        this.gunAmount = gunAmount;
        this.tankAmount = tankAmount;
        this.size = size;
        experience = BS.baseExperience;
        morale = 50;
        this.location = location;
        strength = 0;
        this.gov = gov;
    }

    public void turn(double maxExperience, double tactic){
        this.tactic = tactic;
        if (!onAWay && !location.getUnrest().isRevolt()){
            morale += 5;
            if (morale > (100 + modifiers[2])*(1+modifiers[1])){
                morale = (int) ((100 + modifiers[2])*(1+modifiers[1]));
            }
        }
        if (experience < maxExperience){
            experience = Math.min(experience+0.05, maxExperience);
        }
        moveTurn();
        recountStrength();
    }

    private void moveTurn(){
        if (onAWay){
            way -= 1;
            if (way == 0){
                onAWay = false;
            }
        }
    }

    public boolean fight(int streng, boolean initiative){
        return false;
    }

    public void move(Region region){
        location = region;
        way = (int) (1.0*(BS.baseMovement - (int) modifiers[8])/tactic);
        onAWay = true;
    }

    private double countStrength(double experience, int gunAmount, int tankAmount){
        return (tactic * size * Math.exp(experience)* Math.log10(BS.gunEff*gunAmount/size+1)
                *Math.log10(1.0* BS.tankEff *experience *tankAmount / size+1));
    }

    private void recountStrength(){
        strength = countStrength(experience, gunAmount, tankAmount);
    }

    private double strIncTank(){
        return countStrength(experience, gunAmount, tankAmount + BS.tankInc) - strength;
    }
    private double strIncGuns(){
        return countStrength(experience, gunAmount + BS.gunInc, tankAmount) - strength;
    }

    private void spendGuns(double defeat){
        double loseRate = 0.8;
        if (defeat > 9.5){
            loseRate = 0.99;
        } else {
            loseRate += defeat/50;
        }
        gunAmount *= loseRate;
    }

    private int loseGun(double defeat){
        int lose = (int) (gunAmount * 0.1 * defeat);
        gunAmount -= lose;
        return lose/2;
    }

    public int endBattle(double defeat, double win, int guns){
        size -= size * win /20;
        morale -= 10*(defeat-1);
        if (morale < 0){
            morale = 0;
        }
        if (morale > 100){
            morale = 100;
        }
        spendGuns(defeat);
        gunAmount += guns;
        recountStrength();
        if (win == 2){
            return loseGun(defeat);
        } else {
            return 0;
        }
    }

    public int whatBetter(){
        if (strIncGuns() > strIncTank()){
            return 1;
        } else {
            return 0;
        }
    }

    public int costUpgrade(){
        return (int) (BS.costOfUpgrade * (1+modifiers[4]));
    }

    public int costAddMen(){
        return (int) (BS.costOfUpgrade * (1 + modifiers[6]));
    }

    public void addTank(){
        if (gov.getEconomy().getMoney() > BS.costOfUpgrade * (1+modifiers[4])) {
            gov.getEconomy().addMoney(-(int) (BS.costOfUpgrade * (1+modifiers[4])));
            tankAmount += BS.tankInc;
            recountStrength();
        }
    }

    public void addGun(){
        if (gov.getEconomy().getMoney() > BS.costOfUpgrade * (1+modifiers[4])) {
            gov.getEconomy().addMoney(-(int) (BS.costOfUpgrade * (1 + modifiers[4])));
            gunAmount += BS.gunInc;
            recountStrength();
        }
    }

    public void addMen(){
        if (gov.getEconomy().getMoney() > BS.costOfUpgrade * (1+modifiers[6])) {
            gov.getEconomy().addMoney(-(int) (BS.costOfUpgrade * (1 + modifiers[6])));
            size += BS.menInc;
            experience = BS.baseExperience + (experience - BS.baseExperience) * (1 - 1.0 * BS.menInc / size);
            recountStrength();
        }
    }

    public Region getLocation() {
        return location;
    }

    public double getStrength() {
        return strength;
    }

    public int getMorale() {
        return morale;
    }

    public int getGunAmount() {
        return gunAmount;
    }

    public int getTankAmount() {
        return tankAmount;
    }

    public int getSize() {
        return size;
    }

    public void setGunAmount(int gunAmount) {
        this.gunAmount = gunAmount;
    }

    public void setTankAmount(int tankAmount) {
        this.tankAmount = tankAmount;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getExperience() {
        return experience;
    }

    public String way(){
        String res = "";
        if (onAWay){
            res = "On a way (" + way + ")";
        } else {
            res = "Dislocated";
        }
        return res;
    }

    public void setModifiers(double[] modifiers) {
        this.modifiers = modifiers;
    }
}
