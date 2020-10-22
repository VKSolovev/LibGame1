package com.some.game1.Entities.War;

import com.some.game1.Entities.Technical.Texts.Mod;

public class WarMod extends Mod {

    public WarMod(int[] numMod, double[] valMod, String name, int maxDuration, boolean endless, int id) {
        modifiers = new double[18];
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
    }

    @Override
    public double[] getModifiers() {
        return modifiers;
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
        return new WarMod(numMod, valMod, name, maxDuration, endless, id);
    }

    @Override
    public void setLocalId(int localID) {

    }


}
