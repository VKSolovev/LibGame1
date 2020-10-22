package com.some.game1.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Entities.Regions.Region;
import com.some.game1.Strategy;

import java.util.ArrayList;

public class RegionsScreen extends ScreenBase {

    public RegionsScreen(Strategy strategy) {
        this.gov = strategy.getGov();
        this.strategy = strategy;
    }

    @Override
    public void showh() {
        addBackButton();
        ArrayList<Table> rTables = new ArrayList<>();
        Table frTable = new Table();
        for (Region region :gov.getRegions().getRegions()){
            Table rTable = new Table();
            rTable.setFillParent(true);
            rTable.left().bottom();
            rTable.debug();
            rTable.add(new Label("Id " + region.getId(), skin));
            for (String string: region.getData()){
                rTable.row();
                rTable.add(new Label(string, skin));
            }
            TextButton unrestInfo = new TextButton("Unrest info", skin);
            final Region reg = region;
            unrestInfo.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    strategy.setScreen(new RegUnrestScreen(reg.getUnrest().info(), strategy));
                }
            });
            rTable.add(unrestInfo);
            /*
            for (String string: region.getUnrest().info()){
                rTable.row();
                rTable.add(new Label(string, skin));
            }

             */
            rTable.row();
            for (String str: gov.getEstates().getInfoRegion(region.getId())){
                rTable.add(new Label(str, skin));
                rTable.row();
            }
            rTable.add(regBut(region));
            frTable.add(rTable);
            if (region.getId() % 2 == 1){
                frTable.row();
            }
        }
        frTable.setFillParent(true);
        frTable.left().top();
        frTable.debug();
        stage.addActor(frTable);
        frTable.pad(0,50,0,0);
    }

    @Override
    public void renderh() {
    }

    public Button regBut(final Region region){
        TextButton butScreen = new TextButton("Open view", skin);
        butScreen.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(new RegionScreen(strategy, region));
            }
        });
        return butScreen;
    }


}
