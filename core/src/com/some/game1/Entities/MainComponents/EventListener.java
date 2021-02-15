package com.some.game1.Entities.MainComponents;

import com.some.game1.Screens.MainScreen;

import java.io.Serializable;

public class EventListener implements Serializable {
    private Gov gov;
    private double chanceOfEvent = 0.2;
    private int[] randomEvents = new int[]{1};

    public EventListener(Gov gov) {
        this.gov = gov;
    }

    public void turn(){
        if (Math.random() < chanceOfEvent){
            MainScreen.events.add(BS.events.get(randomEvents[(int) (Math.random()*randomEvents.length)]).clone());
        }
    }

    public void listen(int id, int choice){
        if (id == 1){
            if (choice == 0){
                gov.getMilitary().getWarMods().activateID(0);
            } else {
                gov.getMilitary().getWarMods().activateID(1);
            }
        }
    }
}
