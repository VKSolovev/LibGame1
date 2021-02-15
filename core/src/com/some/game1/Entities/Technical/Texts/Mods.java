package com.some.game1.Entities.Technical.Texts;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Mods implements Serializable {
    protected Mod[] mods;
    protected ArrayList<Mod> activatedMods = new ArrayList<>();

    public void turn(){
        ArrayList<Mod> delete = new ArrayList<>();
        for (Mod mod: activatedMods){
            mod.turn();
            if (!mod.isActive()){
                delete.add(mod);
            }
        }
        for (Mod mod: delete){
            activatedMods.remove(mod);
        }
    }

    public void deactivateOld(String name){
        ArrayList<Mod> delete = new ArrayList<>();
        for (Mod mod: activatedMods){
            if (mod.getName().equals(name)){
                delete.add(mod);
            }
        }
        for (Mod mod: delete){
            activatedMods.remove(mod);
        }
    }

    public void deactivate(int id){
        ArrayList<Mod> delete = new ArrayList<>();
        for (Mod mod: activatedMods){
            if (mod.getId() == id){
                delete.add(mod);
            }
        }
        for (Mod mod: delete){
            activatedMods.remove(mod);
        }
    }

    public void activateID(int id){
        activatedMods.add(mods[id].copy());
    }

    public ArrayList<String> getInfo(){
        ArrayList<String> res = new ArrayList<>();
        for (Mod mod: activatedMods){
            if (mod.isActive()){
                res.add(mod.getName() + "; duration - "+ mod.getDuration() +  ". " + mod.getEffect());
            }
        }
        System.out.println("getInfo mods" + res.size());
        return res;
    }

    public double[] getMods(){
        if (mods.length > 0) {
            double[] res = new double[mods[0].getModifiers().length];
            for (Mod mod : activatedMods) {
                for (int i = 0; i < mod.getModifiers().length; i++) {
                    res[i] += mod.getModifiers()[i];
                }
            }
            return res;
        } else {
            System.out.println("There no mods");
            double[] res = new double[mods[0].getModifiers().length];
            System.exit(1);
            return res;
        }
    }
}
