package com.some.game1.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.Politics.Politic;
import com.some.game1.Entities.Regions.Region;
import com.some.game1.Strategy;

import java.util.ArrayList;

public class RegionScreen extends ScreenBase {
    private Region region;
    private ArrayList<TextButton> supsup = new ArrayList<>();

    public RegionScreen(Strategy strategy, Region region) {
        this.strategy = strategy;
        this.gov = strategy.getGov();
        this.region = region;
    }

    private void turnOf(){
        for (TextButton button: supsup){
            button.setVisible(false);
        }
    }

    public void showh() {
        addBackButton();
        Table pTable = new Table();
        pTable.debug();
        pTable.defaults().left();
        pTable.add(new Label("Politic support", skin));
        pTable.row();
        int i = 0;
        for (String string : region.getPolitics()) {
            pTable.add(new Label(string, skin));
            TextButton support = new TextButton("Support", skin);
            final int num = i;
            final Politic pol = region.getPopulation().getPolitics().getPolitics()[num];
            support.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    if (!pol.isSupport() || pol.isAgainst()) {
                        String name = pol.getName();
                        region.getPopulation().getPolitics().support(name, false);
                        gov.getEstates().supportPolitic(BS.namesOfPolitic.indexOf(name), false, region.getId());
                    } else {
                        pol.desupport();
                    }
                    stage.clear();
                    showh();
                }
            });
            if (pol.isSupport() && !pol.isAgainst()){
                support.setColor((float) 20.0 / 255, (float) 190.0 / 255, (float) 1, (float) 1);
            }
            TextButton suppress = new TextButton("Support", skin);
            suppress.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    if (!pol.isSupport() || !pol.isAgainst()) {
                        String name = region.getPopulation().getPolitics().getPolitics()[num].getName();
                        region.getPopulation().getPolitics().support(name, true);
                        //todo create different mods for supporting/suppressing politics
                        //todo influence flies in the infinity because of numerous modifiers
                        //We should add modifiers in the end of turn
                        gov.getEstates().supportPolitic(BS.namesOfPolitic.indexOf(name), true, region.getId());
                    } else {
                        pol.desupport();
                    }
                    stage.clear();
                    showh();
                }
            });
            if (region.getPopulation().getPolitics().getPolitics()[num].isSupport()
                    && region.getPopulation().getPolitics().getPolitics()[num].isAgainst()){
                suppress.setColor((float) 20.0 / 255, (float) 190.0 / 255, (float) 1, (float) 1);
            }
            pTable.add(support);
            pTable.add(suppress);
            supsup.add(support);
            supsup.add(suppress);
            pTable.row();
            i += 1;
        }
        pTable.setFillParent(true);
        pTable.left().top();
        pTable.pad(50, 50, 0, 0);
        stage.addActor(pTable);

        //common data
        Table dTable = new Table();
    }

    @Override
    protected void addBackButton(){
        Table backB = new Table();
        backB.setFillParent(true);
        stage.addActor(backB);
        backB.defaults().size(120, 80);
        backB.right().top();
        TextButton backButton = new TextButton("Back", skin);
        backB.add(backButton);
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(new RegionsScreen(strategy));
            }
        });
        stage.addActor(backB);
        backB.setFillParent(true);
        backB.right().top();
    }

    @Override
    public void renderh() {
    }
}
