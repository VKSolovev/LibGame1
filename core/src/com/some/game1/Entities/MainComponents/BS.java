package com.some.game1.Entities.MainComponents;

import com.some.game1.Entities.Technical.Texts.Event;

import java.io.*;
import java.util.ArrayList;

public class BS {
    public static int baseCoolDownLawChange = 5;
    public static int baseMovement;
    public static int numRegions = 4;
    public static double baseTactic = 1;
    public static int tankInc = 100;
    public static int gunInc = 500;
    public static int menInc = 100;
    public static int costOfUpgrade = 50;
    public static int costOfMen = 50;
    public static int costOfCreation = 100;
    public static int tankBase = 100;
    public static int gunBase = 1000;
    public static int menBase = 1000;
    public static double gunEff = 10.0;
    public static double tankEff = 100.0;
    public static double baseExperience = 2;
    public static int baseRiseUnrest = -4;
    public static int baseRiseLoy = -4;
    public static double baseTax = 0.2;
    public static int baseLoyalty = 50;
    public static int turn = 0;
    public static int numLows = 4;
    public static int numPolitics = 2;
    public static ArrayList<String> namesOfPolitic = new ArrayList<>();
    static {
        try {
            File file = new File("res\\Texts\\PoliticsId");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                namesOfPolitic.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int numberEconomySpheres = 5;
    public static ArrayList<String> economySpheres = new ArrayList<>();
    public static ArrayList<Double> baseEconomySpheres = new ArrayList<>();
    static {
        try {
            File file = new File("res\\Texts\\EconomySpheres");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                economySpheres.add(line);
                line = reader.readLine();
                baseEconomySpheres.add(Double.parseDouble(line));
                line = reader.readLine();
            }
            numberEconomySpheres = economySpheres.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Event> events = new ArrayList<>();
    static {
        try {
            File file = new File("res\\Texts\\Events");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            int id = 0;
            while (line != null) {
                String name = line;
                String text = reader.readLine();
                int numAns = Integer.parseInt(reader.readLine());
                String[] answers = new String[numAns];
                int[] modClass = new int[numAns];
                int[] modId = new int[numAns];
                for (int i = 0; i < numAns; i++){
                    answers[i] = reader.readLine();
                    modClass[i] = Integer.parseInt(reader.readLine());
                    modId[i] = Integer.parseInt(reader.readLine());
                }
                events.add(new Event(name, id, answers, text, modClass, modId));
                System.out.println(name);
                id+=1;
                line = reader.readLine();
                line = reader.readLine();
            }
            numberEconomySpheres = economySpheres.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String[] names = {"Vladimir Solovev", "Ildar Zagretdinov"};
}
