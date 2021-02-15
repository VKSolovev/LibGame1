package com.some.game1.Entities.Interface;




import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.MainComponents.Main;

import java.util.Scanner;

public abstract class Screen {
    protected Gov gov;

    protected void start(){

    }

    protected boolean show(String command){
        return true;
    }

    public void play(){
        start();
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        if (endGame(command)) {
            if (!basicCommands(command)) {
                if (show(command)){
                    System.out.println("Sorry I don't understand you");
                    play();
                }
            } else {
                play();
            }
        }
    }

    protected boolean endGame(String command){
        if (command.equals("back")){
            return false;
        } else if (command.equals("end")){
            System.out.println("It was good game");
            System.exit(0);
            return false;
        }
        else {
            return true;
        }
    }

    protected void infoPlay(){

    }

    protected boolean basicCommands(String command){
        if (command.equals("info")){
            System.out.println("end - end game in Main Screen");
            System.out.println("back - go to the back screen/page");
            System.out.println("info - get info in any moment about common commands");
            System.out.println("home - get main screen");
            System.out.println("cur info - information about current screen commands");
            return true;
        } else if (command.equals("home")|| command.equals("h")){
            Main.mainScreen.play();
            return true;
        } else if (command.equals("turn")|| command.equals("t")){
            gov.turn(true);
            return true;
        } else if (command.equals("12turn")|| command.equals("lt")){
            System.out.println("Enter number of years");
            Scanner in = new Scanner(System.in);
            int num = 12 * Integer.parseInt(in.nextLine());
            for (int i = 0; i < num; i++) {
                gov.turn(true);
            }
            return true;
        }else if (command.equals("cur info")|| command.equals("ci")){
            infoPlay();
            play();
            return true;
        }
        else {
            return false;
        }
    }

    public void printArray(String[] ar){
        for (String st: ar){
            System.out.println(st);
        }
    }
}
