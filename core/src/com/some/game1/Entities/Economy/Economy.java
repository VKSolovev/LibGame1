package com.some.game1.Entities.Economy;

import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.MainComponents.Gov;

/*
 */
public class Economy {
    public Economy(Gov gov) {
        this.gov = gov;
        for (int i = 0; i < BS.baseEconomySpheres.size(); i++){
            shares[i] = BS.baseEconomySpheres.get(i);
            minimumShares[i] = BS.baseEconomySpheres.get(i);
        }
        money = 1000;
    }

    private Gov gov;
    private double[] shares = new double[BS.numberEconomySpheres];
    private double sumShares = 0;
    private double[] minimumShares  = new double[BS.numberEconomySpheres];
    private int money;
    private int reserves;
    private int profit;
    private double taxRate = BS.baseTax;
    private double potentialTax = BS.baseTax;
    private double rate = 0.01;
    private int gdp;
    private EcMods ecMods = new EcMods();


    public void turn(){
        updateMinShares();
        updateShares();
        gov.getEstates().changeTax(lastTR, taxRate);
        lastTR = taxRate;
        if (BS.turn % 12 == 1) {
            makeMoney();
        }
    }

    public void makeMoney(){
        gdp = gov.getRegions().profit();
        profit = (int) (gdp * taxRate);
        money *= 1+rate;
        money += profit - gdp*sumShares;
        reserves += profit * shares[4];
    }

    public String changeShares(int num, double share){
        if (num >= shares.length || num < 0){
            return "index out of range";
        }
        if (share >= minimumShares[num]){
            shares[num] = share;
            return "New share is " + share;
        } else {
            return "Share is lower than minimum share";
        }
    }

    public int getMoney() {
        return money;
    }

    public int getProfit() {
        return profit;
    }

    public String[] getInfo(){
        String[] res = new String[6];
        res[0] = "Current money " + money;
        res[1] = "Profit " + profit;
        res[2] = "Reserves " + reserves;
        res[2+1] = "Total spending " + sumShares;
        res[4] = "Rate " + rate;
        res[5] = "Tax rate " + taxRate;
        return res;
    }

    private double lastTR = 0.2;
    public void changeTaxes(double val){
        taxRate = val;
    }

    public String[] getShares(){
        String[] res = new String[BS.numberEconomySpheres];
        for (int i = 0; i < BS.numberEconomySpheres; i++){
            res[i] = BS.economySpheres.get(i) + " " + shares[i] + ";    Min share " + minimumShares[i];
        }
        return res;
    }

    public int getAcShare(int i){
        return (int) (shares[i] *100);
    }

    private void updateShares(){
        for (int i = 0; i < minimumShares.length; i++){
            if (shares[i] < minimumShares[i]){
                shares[i] = minimumShares[i];
            }
        }
        sumShares = 0;
        for (int i = 0; i < BS.numberEconomySpheres; i++){
            sumShares += shares[i];
        }
    }

    public void updateMinShares(){
        for (int i = 0; i < BS.numberEconomySpheres; i++){
            minimumShares[i] = BS.baseEconomySpheres.get(i) + gov.getLaws().increaseCosts()[i];
        }
        updateShares();
    }

    public String[] getMinimumShares(){
        String[] res = new String[BS.numberEconomySpheres];
        for (int i = 0; i < BS.numberEconomySpheres; i++){
            res[i] = BS.economySpheres.get(i) + " " + minimumShares[i];
        }
        return res;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void addMoney(int i){
        money += i;
    }


}
