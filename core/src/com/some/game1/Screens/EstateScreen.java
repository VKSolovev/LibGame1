package com.some.game1.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Entities.Estates.Estate;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Strategy;

import java.util.ArrayList;

public class EstateScreen extends ScreenBase {
    private Gov gov;
    private Table modTable;
    private ArrayList<Button> buttonSupport = new ArrayList<>();
    private ArrayList<Button> buttonSuppress = new ArrayList<>();
    private ArrayList<Button> buttonMod = new ArrayList<>();

    public EstateScreen(Strategy strategy) {
        this.gov = strategy.getGov();
        this.strategy = strategy;
    }

    private void updatesMods(Estate estate){
        ArrayList<String> mods = estate.getEstatesMods().getAcMods();
        modTable.reset();
        modTable.debug();
        modTable.defaults().left();
        modTable.add(new Label("Mods", skin));
        modTable.row();
        modTable.add(new Label("Loyalty change because of taxes " + estate.getTaxMod(), skin));
        for (String m: mods){
            modTable.row();
            modTable.add(new Label(m, skin));
        }
        modTable.left().top();
        modTable.pad(100,400,0,0);
        modTable.defaults().left();
    }

    @Override
    public void showh() {
        //adding table with mods
        modTable = new Table();
        modTable.debug();
        modTable.defaults().left();
        modTable.setFillParent(true);
        modTable.add(new Label("Mods", skin));
        modTable.left().top();
        modTable.pad(100,400,0,0);
        stage.addActor(modTable);
        //table with all info about parties
        ArrayList<Table> eTable = new ArrayList<>();
        for (final Estate estate: gov.getEstates().getEstates()){
            Table estTab = new Table();
            Label name = new Label(estate.getName(), skin);
            Label influence = new Label("" + estate.getTotInfluence(), skin);
            Label loyalty = new Label("" + estate.getLoyalty(), skin);

            //support button
            final TextButton support = new TextButton("Support", skin);
            buttonSupport.add(support);
            if (estate.isSupport()){
                support.setColor((float) 20.0 / 255, (float) 190.0 / 255, (float) 1, (float) 1);
            }
            support.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    if (!estate.isSupport()) {
                        if (gov.getEstates().support(estate.getName())) {
                            support.setColor((float) 20.0 / 255, (float) 190.0 / 255, (float) 1, (float) 1);
                        }
                    } else {
                        gov.getEstates().desupport(estate.getName());
                        support.setColor((float) 1, (float) 1, (float) 1, (float) 1);
                    }
                }
            });

            //suppress button
            final TextButton suppress = new TextButton("Suppress", skin);
            buttonSuppress.add(suppress);
            if (estate.isSuppress()){
                suppress.setColor((float) 20.0 / 255, (float) 190.0 / 255, (float) 1, (float) 1);
            }
            suppress.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    if (gov.getEstates().suppress(estate.getName())) {
                        suppress.setColor((float) 20.0 / 255, (float) 190.0 / 255, (float) 1, (float) 1);
                    }
                }
            });

            //mod button
            final TextButton modButton = new TextButton("Mods", skin);
            buttonMod.add(modButton);
            modButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    for (Button button: buttonMod){
                        button.setColor(1,1,1,1);
                    }
                    modButton.setColor((float) 20.0 / 255, (float) 190.0 / 255, (float) 1, (float) 1);
                    updatesMods(estate);
                }
            });
            estTab.add(new Label("Name", skin));
            estTab.add(new Label("Influence", skin));
            estTab.add(new Label("Loyalty", skin));
            estTab.row();
            estTab.add(name);
            estTab.add(influence);
            estTab.add(loyalty);
            estTab.row();
            estTab.add(support);
            eTable.add(estTab);
            estTab.setFillParent(true);
            estTab.add(modButton);
            estTab.add(suppress);
        }
        stage.addActor(eTable.get(0));
        int i = 0;
        for (Table tab: eTable){
            tab.left().top();
            tab.pad(30+ i*100, 30 , 0, 0);
            stage.addActor(tab);
            tab.debug();
            i+=1;
        }
        //add back button
        /*
        Table backB = new Table();
        backB.setFillParent(true);
        stage.addActor(backB);
        backB.defaults().size(120, 80);
        backB.right().top();
        backB.add(backButton());

         */
        addBackButton();
    }

    @Override
    public void renderh() {

    }

}
