package com.some.game1.Entities.Interface;



import com.some.game1.Entities.MainComponents.Gov;

import java.util.Scanner;

public class EconomyScreen extends Screen {
    public EconomyScreen(Gov gov) {
        this.gov = gov;
    }

    @Override
    protected void start() {
        System.out.println("ECONOMY SCREEN");
    }
    @Override
    protected boolean show(String command){
        if (command.equals("data") || command.equals("d")){
            printArray(gov.getEconomy().getInfo());
            play();
        }
        else
        if (command.equals("shares") || command.equals("s")){
            printArray(gov.getEconomy().getShares());
            play();
        }
        else
        if (command.equals("min shares") || command.equals("ms")){
            printArray(gov.getEconomy().getMinimumShares());
            play();
        }
        else
        if (command.equals("change shares") || command.equals("cs")){
            changeShare();
            play();
        }
        else
        if (command.equals("change taxes") || command.equals("ct")){
            changeTaxes();
            play();
        }
        else{
            return true;
        }
        return false;
    }

    private void changeTaxes(){
        System.out.println("Enter new tax rate");
        Scanner in = new Scanner(System.in);
        double num = Double.parseDouble(in.nextLine());
        gov.getEconomy().changeTaxes(num);
    }

    private void changeShare(){
        System.out.println("Enter number of share");
        Scanner in = new Scanner(System.in);
        int num = Integer.parseInt(in.nextLine());
        System.out.println("and new value");
        String line = in.nextLine();
        double value = Double.parseDouble(line);
        System.out.println(gov.getEconomy().changeShares(num, value));
    }


    @Override
    protected void infoPlay(){
        System.out.println("data/d - base information about economy");
        System.out.println("shares/s - information about current budget spending");
        System.out.println("min shares/ms - information about minimum budget spending");
    }

}
