package com.some.game1.Entities.Ministers;
/*
Кабинет министров состоит из нескольких министров. Вполне разумно сделать это министерствами.
В каждом министерстве у партии есть влияние. Соответственно если вы контролируете министертво, то
Вы можете выдвигать законы. Однако для принятия законов требуется влияние в министерстве.
Влияние в министерстве изменяется назначениями (обижается предыдущий министр), указами (сильные дебаффы)
и эвентами (основное). При этом лучше, чтобы влияние в среднем снижалось.
Министр финансов, развития, военный министр, министр юстиции, министр по работе с населением,

 */

import com.some.game1.Entities.MainComponents.Gov;

import java.util.ArrayList;

public class Cabinet {
    private Minister[] ministers;//0 - economy
    private ArrayList<Minister> ministerPool = new ArrayList<>();
    private Gov gov;
    private int idCounter = 0;

    public Cabinet(Minister[] ministers, Gov gov) {
        this.ministers = ministers;
        this.gov = gov;
    }

    public void turn(){
        for (Minister minister: ministerPool){
            if (minister.turn()){
                //возможно стоит возвращать строку
                System.out.println("Level Up");
            }
        }
    }

    public void hire(String part){
        ministerPool.add(new Minister(part, idCounter));
        idCounter += 1;
    }

    public void givePosition(Minister minister){
        if (minister.getSpec().equals("Economy")){
            ministers[0] = minister;
            ministers[0].setHired(true);
        }
    }

    public void callPosition(Minister minister){
        if (minister.getSpec().equals("Economy")){
            ministers[0].setHired(false);
            ministers[0] = null;
        }
    }

    public void callPosition(int numberOfSphere){
        ministers[numberOfSphere].setHired(false);
        ministers[numberOfSphere] = null;
    }

}
