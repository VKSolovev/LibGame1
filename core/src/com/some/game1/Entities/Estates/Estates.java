package com.some.game1.Entities.Estates;

import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.Regions.Region;


import java.io.Serializable;
import java.util.ArrayList;

public class Estates implements Serializable {
    private Estate[] estates;
    private Gov gov;

    public Estates(Gov gov) {
        this.gov = gov;
        estates = new Estate[3];
        estates[0] = new Estate(0, "Communism");
        estates[1] = new Estate(1, "Liberalism");
        estates[2] = new Estate(2, "Socialism");
    }

    public void turn(){
        recalPow();
        calcLoyalty();
        for (Estate estate: estates){
            estate.getEstatesMods().turn();
            estate.forgetTaxes();
        }
    }

    public void changeTax(double prev, double cur){
        for (Estate est: estates){
            est.changeTaxes(prev, cur);
        }
    }

    public int getId(String name){
        for (Estate est: estates){
            if (est.getName().equals(name)){
                return est.getId();
            }
        }
        return -1;
    }

    public boolean support(String name){
        if (getId(name) > -1&& gov.getInfluence()>=50) {
            estates[getId(name)].support();
            gov.addInf(-50);
            return true;
        } else {
            return false;
        }
    }

    public void desupport(String name){
        if (getId(name) > -1) {
            estates[getId(name)].desupport();
        }
    }

    public boolean suppress(String name){
        if (getId(name) > -1 && gov.getInfluence()>=50) {
            estates[getId(name)].suppress();
            gov.addInf(-50);
            return true;
        } else {
            return false;
        }
    }

    private void recalPow(){
        for (Region reg: gov.getRegions().getRegions()){
            double[] basePow = new double[estates.length];
            double sum_power = 0;
            for (int i = 0; i < estates.length; i++){
                //TODO add support and suppress
                basePow[i] = reg.getBP(estates[i].getPolitics(), estates[i].getId(), this)
                        *estates[i].getEstatesMods().getInfluenceMod(reg.getId())
                        - estates[i].getInfluence()[reg.getId()];
                sum_power += basePow[i]/10+estates[i].getInfluence()[reg.getId()];
            }
            for (int i = 0; i < estates.length; i++){
                estates[i].setInfluence(reg.getId(), (basePow[i]/10+estates[i].getInfluence()[reg.getId()])/sum_power*100);
            }
        }
    }

    public String[] getInfo(){
        String[] res = new String[estates.length];
        for (Estate estate: estates){
            res[estate.getId()] = estate.getName() + ". Loyalty " + (int) estate.getLoyalty() + "; Average influence " +
                    (int) estate.getTotInfluence();
        }
        return res;
    }

    public String[] getInfoRegion(int id){
        String[] res = new String[estates.length];
        for (Estate estate: estates){
            res[estate.getId()] = estate.getName() + ". Influence " +
                    String.format("%.2f", estate.getInfluence()[id]);
        }
        return res;
    }

    public void calcLoyalty(){
        for (Estate estate: estates){
            estate.calcLoyalty(gov.getLaws().getLaws());
        }
    }

    public String supportPolitic(int idPol, boolean against, int regionId){
        String res = "OK";
        if (idPol >= BS.numPolitics || idPol < 0){
            return "idPol out of range";
        }
        for (Estate estate: estates){
            if (!against) {
                if (estate.getPolitics()[idPol]) {
                    estate.getEstatesMods().activateID(0);
                    estate.getEstatesMods().setLocalId(regionId);
                }
                else {
                    estate.getEstatesMods().activateID(1);
                    estate.getEstatesMods().setLocalId(regionId);
                }
            } else {
                if (!estate.getPolitics()[idPol]) {
                    estate.getEstatesMods().activateID(0);
                    estate.getEstatesMods().setLocalId(regionId);
                }
                else {
                    estate.getEstatesMods().activateID(1);
                    estate.getEstatesMods().setLocalId(regionId);
                }
            }
        }
        return res;
    }

    public ArrayList getMods(){
        ArrayList<String> res = new ArrayList<>();
        res.add("Common change because of taxes " + (BS.baseTax - gov.getEconomy().getTaxRate())*100 +"%");
        for (Estate estate: estates){
            res.add(estate.getName());
            res.add("Change because of last tax changes - " + (estate.getTaxMod()-1)*100 + "%");
            res.addAll(estate.getEstatesMods().getInfo());
            res.add("");
        }
        return res;
    }

    public Estate[] getEstates() {
        return estates;
    }

    public int numEstPol(int id){
        int res = 0;
        for (Estate estate: estates){
            if (estate.getPolitics()[id]){
                res += 1;
            }
        }
        return res;
    }
}
