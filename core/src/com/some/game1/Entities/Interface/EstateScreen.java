package com.some.game1.Entities.Interface;



import com.some.game1.Entities.MainComponents.Gov;

import java.util.Scanner;

public class EstateScreen extends Screen {
    public EstateScreen(Gov gov) {
        this.gov = gov;
    }

    @Override
    protected void start() {
        System.out.println("ESTATE SCREEN");
    }
    @Override
    protected boolean show(String command){
        if (command.equals("data")|| command.equals("d")){
            for (String string: gov.getEstates().getInfo()){
                System.out.println(string);
            }
            play();
        } else if (command.equals("mods")|| command.equals("m")){
            for (Object str: gov.getEstates().getMods()) {
                System.out.println(str);
            }
            play();
        } else if (command.equals("support")|| command.equals("so")){
            support();
            play();
        } else if (command.equals("suppress")|| command.equals("sr")){
            suppress();
            play();
        } else{
            return true;
        }
        return false;
    }

    private void support(){
        if (gov.getInfluence() >= 50){
            System.out.println("Enter estate name");
            Scanner in = new Scanner(System.in);
            String name = in.nextLine();
            if (!gov.getEstates().support(name)){
                System.out.println("Wrong name");
            } else {
                System.out.println("Success");
            }
        } else {
            System.out.println("There no influence");
        }
    }

    private void suppress(){
        if (gov.getInfluence() >= 50){
            System.out.println("Enter estate name");
            Scanner in = new Scanner(System.in);
            String name = in.nextLine();
            if (!gov.getEstates().suppress(name)){
                System.out.println("Wrong name");
            } else {
                System.out.println("Success");
            }
        } else {
            System.out.println("There no influence");
        }
    }

    @Override
    protected void infoPlay(){
        System.out.println("data - get data about loyalty and influence of estates");
        System.out.println("mods/m - return all the mods");
        System.out.println("support/so - support some estate");
        System.out.println("suppress/sp - suppress some estate");
    }
}
