package com.some.game1.Entities.MainComponents;

import com.some.game1.Entities.Estates.EstMod;
import com.some.game1.Entities.Technical.Texts.Mod;
import com.some.game1.Entities.Technical.Texts.Mods;

public class GovMods extends Mods {
    public GovMods() {
        mods = new GovMod[1];
        mods[0] = new GovMod(1, "Test", 1, false, 0);
    }

    public int totalInfl(){
        int res = 0;
        for (Mod mod: mods){
            res += mod.getInfluence();
        }
        return res;
    }
}
