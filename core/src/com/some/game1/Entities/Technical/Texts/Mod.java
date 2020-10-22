package com.some.game1.Entities.Technical.Texts;

public abstract class Mod {
    protected int id;
    protected boolean endless;
    protected int duration = 0;
    protected int maxDuration;
    protected boolean active = false;
    protected String name;
    protected String effect = "";


    protected double[] modifiers;
    protected int[] numMod;
    protected double[] valMod;

    public void activate(){
        duration = maxDuration;
        active = true;
    }
    public void turn(){
        if (active && !endless){
            duration -= 1;
            if (duration == 0){
                deactivate();
            }
        }
    }
    public void deactivate(){
        duration = 0;
        active = false;
    }

    public int getId() {
        return id;
    }

    public boolean isEndless() {
        return endless;
    }

    public int getDuration() {
        return duration;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public abstract int getLocalID();

    public abstract double getChangeLocalInfluence();

    public abstract double getChangeTotalInfluence();

    public abstract int getChangeLoyalty();

    public abstract Mod copy();

    public abstract void setLocalId(int localID);

    public abstract double[] getModifiers();
}
