package com.some.game1.Entities.Interface;

import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.MainComponents.BS;
import java.util.Scanner;

public class LawsScreen extends Screen {
    public LawsScreen(Gov gov) {
        this.gov = gov;
    }
    @Override
    protected void start() {
        System.out.println("LAWS SCREEN");
    }
    @Override
    protected boolean show(String command){
        if (command.equals("configuration") || command.equals("c")){
            for (String string: gov.getLaws().lawsConfig()){
                System.out.println(string);
            }
            play();
        } else if (command.equals("change law") || command.equals("cl")){
            changeLaw();
            //gov.getEstates().calcLoyalty();
            gov.getEconomy().updateMinShares();
            play();
        }
        else if (command.equals("change law (id)") || command.equals("cid")){
            changeLawId();
            //gov.getEstates().calcLoyalty();
            gov.getEconomy().updateMinShares();
            play();
        }
        else if (command.equals("get low info") || command.equals("gli")){
            getLowInfo();
            play();
        }
        else if (command.equals("get low info (id)") || command.equals("glid")){
            getLowInfoId();
            play();
        }
        else {
            return true;
        }
        return false;
    }

    public void getLowInfo(){
        System.out.println("enter law name");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        for (String st: gov.getLaws().getLawInfo(name)){
            System.out.println(st);
        }
    }

    public void getLowInfoId(){
        System.out.println("enter law id");
        Scanner in = new Scanner(System.in);
        int name = in.nextInt();
        for (String st: gov.getLaws().getLawInfoId(name)){
            System.out.println(st);
        }
    }

    public void changeLawId(){
        System.out.println("enter law id");
        Scanner in = new Scanner(System.in);
        int name = in.nextInt();
        if (gov.getLaws().changeLowId(name)){
            System.out.println("Success");
        } else {
            System.out.println("Unexpected error");
        }
    }

    public void changeLaw(){
        System.out.println("enter law name");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        if (gov.getLaws().changeLow(name)){
            System.out.println("Success");
        } else {
            System.out.println("Unexpected error");
        }
    }

    @Override
    protected void infoPlay(){
        System.out.println("configuration/c - configuration of laws");
        System.out.println("cl - change laws");
        System.out.println("cli - change law (id)");
        System.out.println("get low info (id)/glid");
        System.out.println("get low info/gli");
    }
}
