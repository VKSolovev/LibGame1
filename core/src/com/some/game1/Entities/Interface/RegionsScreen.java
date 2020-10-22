package com.some.game1.Entities.Interface;



import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.MainComponents.BS;

import java.util.Scanner;

public class RegionsScreen extends Screen {
    public RegionsScreen(Gov gov) {
        this.gov = gov;
        regionScreens = new RegionScreen[4];
        for (int i = 0; i < 4; i ++){
            regionScreens[i] = new RegionScreen(gov, gov.getRegions().getRegions()[i]);
        }
    }
    private RegionScreen[] regionScreens;
    @Override
    protected void start() {
        System.out.println("REGIONS SCREEN");
    }
    @Override
    protected boolean show(String command){
        if (command.equals("data")|| command.equals("d")){
            data();
            play();
        } else if (command.equals("region")|| command.equals("r")){
            showRegion();
            play();
        } else {
            return true;
        }
        return false;
    }
    private void showRegion(){
        System.out.println("Enter id of region (from 1 to 4)");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt() - 1;
        regionScreens[id].play();
    }
    @Override
    protected void infoPlay(){
        System.out.println("data - get data about influence of all estates in some region");
    }
    private void data(){
        System.out.println("Enter id of region (from 1 to 4)");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt() - 1;
        for (String string: gov.getEstates().getInfoRegion(id)){
            System.out.println(string);
        }
    }
}
