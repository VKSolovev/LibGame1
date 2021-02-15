package com.some.game1.Entities.Estates;


import com.some.game1.Entities.Technical.Texts.Mod;

public class EstMod extends Mod {
    public EstMod(int changeLoyalty, double changeTotalInfluence, double changeLocalInfluence, int localID,
                  String name, int maxDuration, boolean endless, int id) {
        this.changeLoyalty = changeLoyalty;
        this.changeTotalInfluence = changeTotalInfluence;
        this.changeLocalInfluence = changeLocalInfluence;
        this.localID = localID;
        this.name = name;
        this.maxDuration = maxDuration;
        this.endless = endless;
        this.id = id;
        //TODO сделать описание эффекта
        effect = "Change Loyalty " + changeLoyalty + "; Change Influence " + (int) (changeTotalInfluence*100) + "%";
        activate();
    }
    private int changeLoyalty;
    private double changeTotalInfluence;
    private double changeLocalInfluence;
    private int localID;

    public int getChangeLoyalty() {
        return changeLoyalty;
    }
    @Override
    public double getChangeTotalInfluence() {
        return changeTotalInfluence;
    }
    @Override
    public double getChangeLocalInfluence() {
        return changeLocalInfluence;
    }
    @Override
    public int getLocalID() {
        return localID;
    }
    @Override
    public Mod copy(){
        return new EstMod( changeLoyalty,  changeTotalInfluence,  changeLocalInfluence,  localID,
         name,  maxDuration,  endless,  id);
    }

    @Override
    public void setLocalId(int localID) {
        this.localID = localID;
    }

    @Override
    public double[] getModifiers() {
        return new double[0];
    }

    @Override
    public int getInfluence() {
        return 0;
    }
}
