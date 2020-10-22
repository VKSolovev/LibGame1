package com.some.game1.Entities.Interface;


import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.Regions.Region;


import java.util.Scanner;

public class RegionScreen extends Screen{
    public RegionScreen(Gov gov, Region region) {
        this.region = region;
        this.gov = gov;
    }

    private Region region;
    @Override
    protected void start() {
        System.out.println((region.getId() + 1) + " REGION SCREEN");
    }
    @Override
    protected boolean show(String command){
        if (command.equals("data")|| command.equals("d")){
            for (String string: region.getData()){
                System.out.println(string);
            }
            play();
        } else if (command.equals("politics")|| command.equals("p")) {
            for (String string : region.getPolitics()) {
                System.out.println(string);
            }
            play();
        } else if (command.equals("support")|| command.equals("sp")) {
            support(false);
            play();
        }else if (command.equals("suppress")|| command.equals("sr")) {
            support(true);
            play();
        }
        else {
            return true;
        }
        return false;
    }

    private void support(boolean against){
        if (gov.getInfluence() >= 50) {
            System.out.println("Enter name of politic");
            Scanner in = new Scanner(System.in);
            String name = in.nextLine();
            System.out.println(region.getPopulation().getPolitics().supportOld(name, against));
            System.out.println(gov.getEstates().supportPolitic(BS.namesOfPolitic.indexOf(name), against, region.getId()));
            //gov.getEstates().calcLoyalty();
        } else {
            System.out.println("There no influence");
        }
    }
    @Override
    protected void infoPlay(){
        System.out.println("configuration - configuration of laws");
    }

}
