package com.some.game1.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.Regions.Region;
import com.some.game1.Entities.War.Army;
import com.some.game1.Entities.War.Military;
import com.some.game1.Strategy;

import java.util.ArrayList;

public class ArmyScreen extends ScreenBase {
    private Military military;

    public ArmyScreen(Gov gov, Strategy strategy) {
        this.strategy = strategy;
        this.gov = gov;
        military = gov.getMilitary();
    }

    @Override
    public void showh() {
        stage.clear();
        addBackButton();
        Table container = new Table();
        container.debugAll();
        Table armyList = new Table();
        armyList.debugAll();
        final ScrollPane scroll = new ScrollPane(armyList, skin);
        armyList.defaults().expand();
        container.setFillParent(true);
        container.top().left().pad(0, 0, 0, 300);
        container.add(scroll);
        for (Region region: gov.getRegions().getRegions()){
            Label info = new Label("Total strength in region " + region.getId() + " is "
                    + region.getUnrest().getTotStr(), skin);
            info.setFontScale((float) 1.5);
            armyList.add(info);
            armyList.row();
            armyList.add(getArmT(region));
            armyList.row();
        }
        stage.addActor(container);

        stage.addActor(addArmy());
        addMods(military.getWarMods().getInfo());
    }

    private void addMods(ArrayList<String> warMods){
        Table modT = new Table();
        Label title = (new Label("Mods", skin));
        title.setFontScale(3);
        modT.add(title);
        modT.row();
        for (String string: warMods){
            Label mod = new Label(string, skin);
            mod.setFontScale(2);
            modT.add(mod);
            modT.row();
        }
        stage.addActor(modT);
        modT.setFillParent(true);
        modT.top();

    }

    private Table addArmy(){
        TextButton addArmyB = new TextButton("Add Army", skin);
        addArmyB.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                military.createArmy(gov.getRegions().getRegions()[0]);
                showh();
            }
        });

        Table addArmyT = new Table();
        addArmyT.setFillParent(true);
        addArmyT.defaults().size(120, 80);
        addArmyT.right().bottom();
        addArmyT.add(addArmyB);
        return addArmyT;
    }

    private Table getArmT(Region region){
        ArrayList<Army> armies = military.getArmiesInRegion(region);
        final Table armT = new Table();
        for (final Army army: armies){
            Label label = new Label("" + army.getStrength(), skin);
            armT.add(new Label("Strength" , skin));
            armT.add(new Label("" + army.getGunAmount(), skin));
            armT.add(new Label("" + army.getSize(), skin));
            armT.add(new Label("" + army.getTankAmount(), skin));
            armT.row();
            armT.add(label);
            armT.add(armyButtons(army));
            armT.row();
            armT.add(new Label("Morale" , skin));
            armT.add(new Label("" + army.getMorale() , skin));
            armT.add(new Label("Experience" , skin));
            armT.add(new Label("" + String.format("%.2f", army.getExperience()) , skin));
            armT.row();
            final Button move = new TextButton("Move", skin);
            armT.add(move);
            final Table mov = new Table();
            move.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    mov.setVisible(true);
                    mov.clear();
                    for (int i = 0; i < 4; i++){
                        final int num = i;
                        Button reg = new TextButton("Region " + i, skin);
                        reg.addListener(new ClickListener() {
                            public void clicked(InputEvent event, float x, float y) {
                                army.move(gov.getRegions().getRegions()[num]);
                            }
                        });
                        mov.add(reg);
                        mov.row();

                    }
                    mov.setX(move.getX()+ armT.getX());
                    mov.setY(move.getY()+armT.getY());
                    stage.addActor(mov);
                    mov.addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            mov.setVisible(false);
                            showh();
                        }
                    });
                }

            });
            Label dislocation = new Label("Dislocation "+army.getLocation().getId(), skin);
            armT.add(dislocation);
            Label onAWay = new Label(army.way(), skin);
            armT.add(onAWay);
            Label tactic = new Label("Tactic " + military.getTactic(), skin);
            armT.add(tactic);
            armT.row();
        }
        return armT;
    }

    private Button[] armyButtons(Army army){
        TextButton addTank = new TextButton("Add Tanks " + army.costUpgrade(), skin);
        final Army arm = army;
        addTank.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                arm.addTank();
                showh();
            }
        });
        TextButton addGun = new TextButton("Add Guns " + army.costUpgrade(), skin);
        addGun.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                arm.addGun();
                showh();
            }
        });
        TextButton addMen = new TextButton("Add Men " +army.costAddMen(), skin);
        addMen.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                arm.addMen();
                showh();
            }
        });
        if (arm.whatBetter() == 1){
            addTank.setColor( (float) 0.5, 0, 0, (float) 0.5);
        } else {
            addGun.setColor( (float) 0.5, 0, 0, (float) 0.5);
        }
        Button[] res = new Button[3];
        res[0] = addGun;
        res[1] = addMen;
        res[2] = addTank;
        return res;
    }
    @Override
    public void renderh(){

    }


}
