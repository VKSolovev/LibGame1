package com.some.game1.Entities.Lows;

import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.MainComponents.BS;

import java.io.*;
import java.util.ArrayList;

public class Laws implements Serializable {
    public void constructLaws(){
        try {
            for (int i = 0; i < BS.numLows; i ++) {
                File file = new File("res\\Texts\\LawsDescription\\" + i + ".law");
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String res;
                laws[i] = new Law(reader.readLine(), res = reader.readLine(),
                        BS.namesOfPolitic.indexOf(res),
                        Boolean.parseBoolean(reader.readLine()),
                        i,
                        Integer.parseInt(reader.readLine()),
                        Integer.parseInt(reader.readLine())
                        );
                String line = reader.readLine();
                while (line != null && !line.equals("end")) {
                    laws[i].analise(line);
                    line = reader.readLine();
                }

                fr.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Laws(Gov gov) {
        laws = new Law[BS.numLows];
        constructLaws();
        this.gov = gov;
    }

    private Law[] laws;
    private Gov gov;

    public void turn(){
        for (Law law : laws){
            law.turn();
        }
    }

    public int getLawId(String name){
        for (Law law: laws){
            if (law.getName().equals(name)){
                return law.getId();
            }
        }
        return -1;
    }

    public ArrayList<String> getLawInfo(String st){
        if (getLawId(st) > -1){
            return laws[getLawId(st)].getInfo();
        } else {
            ArrayList<String> res =  new ArrayList();
            res.add("There no such law");
            return res;
        }
    }
    public ArrayList<String> getLawInfoId(int id){
        if (id > -1 && id < laws.length){
            return laws[id].getInfo();
        } else {
            ArrayList<String> res =  new ArrayList();
            res.add("id out of range");
            return res;
        }
    }

    public boolean changeLow(String name){
        for (Law law: laws){
            if (law.getName().equals(name)){
                return law.reform();
            }
        }
        return false;
    }

    public boolean acceptLow(Law law){
        return law.accept();
    }

    public boolean denyLow(Law law){
        return law.deny();
    }

    public boolean changeLowId(int name){
        if (name >= 0 && name < laws.length) {
            return laws[name].reform();
        } else return false;
    }

    public String[] lawsConfig(){
        String[] res = new String[BS.numLows];
        for (Law law : laws){
            gov.getEconomy().updateMinShares();
            res[law.getId()] = law.getName()  + " " + law.isActivated();
            if (law.getCooldown() > 0){
                res[law.getId()] += ". Cooldown is " + law.getCooldown();
            }
        }
        return res;
    }

    public double[] increaseCosts(){
        double[] res = new double[BS.numberEconomySpheres];
        for (Law law: laws){
            for (int i = 0; i < BS.numberEconomySpheres; i++){
                res[i] += law.getIncreaseCosts()[i] *(law.isActivated() ? 1 : 0);
            }
        }
        return res;
    }


    public Law[] getLaws() {
        return laws;
    }
}
