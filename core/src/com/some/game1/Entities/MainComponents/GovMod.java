package com.some.game1.Entities.MainComponents;

import com.some.game1.Entities.Technical.Texts.Mod;

public class GovMod extends Mod {
    private int modInfluence;

    public GovMod(int modInfluence, String name, int maxDuration, boolean endless, int id) {
        this.modInfluence = modInfluence;
        this.name = name;
        this.maxDuration = maxDuration;
        this.endless = endless;
        this.id = id;
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
        return null;
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
        return modInfluence;
    }
}
