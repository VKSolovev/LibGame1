package com.some.game1.Entities.Ministers;

import com.badlogic.gdx.graphics.g3d.particles.influencers.ModelInfluencer;
import com.some.game1.Entities.MainComponents.BS;

import java.util.Random;

public class Minister {
    private String name;
    private double age;
    private String spec;
    private int level;
    private int bonus;
    private int extraBonus = -1;
    private int id;
    private boolean hired = false;

    public Minister(String name, int age, String spec, int level, int bonus, int exB, int id) {
        this.name = name;
        this.age = age;
        this.spec = spec;
        this.level = level;
        this.bonus = bonus;
        extraBonus = exB;
        this.id = id;
    }

    public Minister(String spec, int id) {
        this.spec = spec;
        this.id = id;
        Random random = new Random();
        int index = random.nextInt(BS.names.length);
        name = BS.names[index];
        age = random.nextInt(40) + 20;
        level = random.nextInt(100);
        if (level > 90) {
            level = 3;
        } else if (level > 50){
            level = 2;
        } else {
            level = 1;
        }
        bonus = getRandomBonus(spec);
    }

    public boolean turn(){
        tryAddExtraBonus(false);
        age += 0.25;
        return experience();
    }

    private boolean experience(){
        int score = (new Random()).nextInt(100);
        if (hired) {
            score -= 10;
        }
        if (score < 10 - level){
            level += 1;
            return true;
        } else {
            return false;
        }
    }

    private int getRandomBonus(String spec){
        //в чем суть. Тут надо давать бонус в соответствии с министерством, которое наняло.
        return 0;
    }

    public boolean tryAddExtraBonus(Boolean sure){
        if ((extraBonus > -1 || (new Random()).nextInt(15) < level) && !sure){
            return false;
        } else {
            extraBonus = getRandomBonus(spec);
            return true;
        }
    }

    public String getSpec() {
        return spec;
    }

    public void setHired(boolean hired) {
        this.hired = hired;
    }

    public boolean isHired() {
        return hired;
    }
}
