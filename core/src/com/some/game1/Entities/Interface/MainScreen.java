package com.some.game1.Entities.Interface;

import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.MainComponents.BS;

public class MainScreen extends Screen {
    public MainScreen() {
        gov = new Gov();
        regionsScreen = new RegionsScreen(gov);
        estateScreen = new EstateScreen(gov);
        lawsScreen = new LawsScreen(gov);
        economyScreen = new EconomyScreen(gov);
    }

    private RegionsScreen regionsScreen;
    private EstateScreen estateScreen;
    private LawsScreen lawsScreen;
    private EconomyScreen economyScreen;

    public void startGame(){
        System.out.println("Hello, game is created you can check something. Print info to get more information and commands");
        gov.turn();
        play();
    }

    @Override
    protected void start() {
        System.out.println("MAIN SCREEN");
    }

    @Override
    protected boolean show(String command){
        if (command.equals("cur info")|| command.equals("ci")) {
            infoPlay();
        } else if (command.equals("regions") || command.equals("r")){
            regionsScreen.play();
            play();
        } else if (command.equals("gov info")|| command.equals("gi")){
            govInfo();
        } else if (command.equals("parties")|| command.equals("p")){
            estateScreen.play();
            play();
        }  else if (command.equals("economy")|| command.equals("e")){
            economyScreen.play();
            play();
        } else if (command.equals("laws")|| command.equals("l")){
        lawsScreen.play();
        play();
        }
        else {
            return true;
        }
        return false;
    }

    @Override
    protected boolean endGame(String command){
        if (command.equals("end")){
            System.out.println("It was good game");
            System.exit(0);
            return false;
        } else {
            return true;
        }
    }


    @Override
    protected void infoPlay(){
        System.out.println("regions - open region screen");
        System.out.println("gov info - information about government");
        System.out.println("parties/p - screen with parties");
        System.out.println("economy/e - screen with economy");
        System.out.println("laws/l - screen with laws");
    }
    private void govInfo(){
        System.out.println("Year " + (BS.turn / 12) + " month " +  (BS.turn % 12));
        System.out.println("Money " + gov.getEconomy().getMoney());
        System.out.println("Taxes " + gov.getEconomy().getProfit());
        System.out.println("Influence " + gov.getInfluence());
        play();
    }
}
