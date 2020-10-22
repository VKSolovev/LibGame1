package com.some.game1.Entities.Economy;

import com.some.game1.Entities.Technical.Texts.Mods;
import com.some.game1.Entities.War.WarMod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EcMods extends Mods {


    private static int numMods;

    public EcMods() {
        mods = new EcMod[numMods];
        for (int i = 0; i < numMods; i++){
            mods[i] = readMods.get(i).copy();
        }
    }


    private static ArrayList<EcMod> readMods = new ArrayList<>();

    static {
        try {
            File file = new File("res\\Texts\\Modifiers\\War.modifiers");

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            line = null;
            int id = 0;
            while (line != null) {
                String[] main = line.split(", ");
                int numModif = Integer.parseInt(reader.readLine());
                int[] numMod = new int[numModif];
                double[] valMod = new double[numModif];
                for (int i = 0; i < numModif; i++){
                    String[] modLine = reader.readLine().split(", ");
                    System.out.println(modLine[0]+" "+ modLine[1]);
                    numMod[i] = Integer.parseInt(modLine[0]);
                    valMod[i] = Double.parseDouble(modLine[1]);
                }
                int numSpheres = Integer.parseInt(reader.readLine());
                int[] numSp = new int[numModif];
                double[] valSp = new double[numModif];
                for (int i = 0; i < numSpheres; i++){
                    String[] modLine = reader.readLine().split(", ");
                    System.out.println(modLine[0]+" "+ modLine[1]);
                    numSp[i] = Integer.parseInt(modLine[0]);
                    valSp[i] = Double.parseDouble(modLine[1]);
                }
                reader.readLine();
                line = reader.readLine();
                readMods.add(new EcMod(numMod, valMod, main[0],
                        Integer.parseInt(main[1].split(" ")[1]),
                        Integer.parseInt(main[2].split(" ")[1]) == 1,
                        id, numSp, valSp));
                id += 1;
            }
            numMods = readMods.size();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
