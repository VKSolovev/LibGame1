package com.some.game1.Entities.Economy;

import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.Technical.Texts.Mod;

public class EcMod extends Mod {
    private double[] increaseSpheres = new double[BS.numberEconomySpheres];
    int[] numsp;
    double[] incsp;

    public EcMod(int[] numMod, double[] valMod, String name, int maxDuration, boolean endless, int id,
                 int[] numsp, double[] incsp) {
        modifiers = new double[3];
        for (int i = 0; i < numMod.length; i++){
            modifiers[numMod[i]] = valMod[i];
        }
        this.name = name;
        this.maxDuration = maxDuration;
        this.endless = endless;
        this.id = id;
        this.numMod = numMod;
        this.valMod = valMod;
        activate();
        this.numsp = numsp;
        this.incsp = incsp;
        for (int i = 0; i < numsp.length; i++){
            increaseSpheres[numsp[i]] = incsp[i];
        }
    }

    @Override
    public int getLocalID() {
        return 0;
    }

    @Override
    public double getChangeLocalInfluence() {
        return 0;
    }

    @Override
    public double getChangeTotalInfluence() {
        return 0;
    }

    @Override
    public int getChangeLoyalty() {
        return 0;
    }

    @Override
    public Mod copy() {
        return new EcMod(numMod, valMod, name, maxDuration, endless, id,
        numsp, incsp);
    }

    @Override
    public void setLocalId(int localID) {

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
