package com.some.game1.Entities.Regions;
/*
1 - farms
2 - mines
3 - factories
4 - cities

 */


public class KeyPlace {
    private String name;
    private boolean revolt;
    private int type;
    private int num;

    public KeyPlace(int type, int num) {
        this.num = num;
        this.type = type;
        if (type == 1){
            name = "Farms";
        }
        if (type == 2){
            name = "Mines";
        }
        if (type == 3){
            name = "Factories";
        }
        if (type == 4){
            name = "Cities";
        }
        revolt = false;
    }

    public String getName() {
        return name;
    }

    public boolean isRevolt() {
        return revolt;
    }

    public int getNum() {
        return num;
    }

    public void setRevolt(boolean revolt) {
        this.revolt = revolt;
    }

    public int getType() {
        return type;
    }
}
