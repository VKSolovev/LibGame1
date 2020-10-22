package com.some.game1.Entities.Estates;


/*
короче эта штука будет принадлежать каждому эстейту, тогда все рассчитывается дл одного определенного эстейта
 */

import com.some.game1.Entities.Technical.Texts.Mod;
import com.some.game1.Entities.Technical.Texts.Mods;

import java.util.ArrayList;

public class EstatesMods extends Mods {


    public EstatesMods() {
        mods = new EstMod[4];
        mods[0] = new EstMod(1, 0, 1, 0, "Support politic",
                12, false, 0);
        mods[1] = new EstMod(-2, 0, 1, 0, "Suppress politic",
                12, false, 1);
        mods[2] = new EstMod(10, 0.5, 0, 0, "Support party",
                12, false, 2);
        mods[3] = new EstMod(-20, -0.5, 0, 0, "Suppress party",
                12, false, 3);
    }

    public double getInfluenceMod(int idRegion){
        double res = 1.0;
        for (Mod mod: activatedMods){
            if (mod.isActive()){
                if (idRegion == mod.getLocalID()){
                    res *= (1+mod.getChangeLocalInfluence());
                }
                res *= 1+mod.getChangeTotalInfluence();
            }
        }
        return res;
    }

    public int getLoyaltyMod(){
        int res = 0;
        for (Mod mod: activatedMods){
            if (mod.isActive()){
                res += mod.getChangeLoyalty();
            }
        }
        return res;
    }


    public boolean isActive(int i){
        for (Mod mod: activatedMods){
            if (mod.getId() == i){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getAcMods(){
        ArrayList<String> res = new ArrayList<>();
        for (Mod mod: activatedMods){
            if (mod.isActive()){
                String str = mod.getName() + " " + mod.getEffect() + " " + mod.getDuration();
                res.add(str);
            }
        }
        return res;
    }

    public void setLocalId(int id){
        activatedMods.get(activatedMods.size() - 1).setLocalId(id);
    }
}
