package com.some.game1.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Strategy;

public class EconomyScreen extends ScreenBase {
    private Table data;
    private Label[] lData = new Label[5];
    private Label[] slabel;
    private Label tLable;

    public EconomyScreen(Gov gov, Strategy strategy) {
        this.gov = gov;
        this.strategy = strategy;
    }

    @Override
    public void showh() {
        //add data about economy
        data = new Table();
        data.debug();
        data.defaults().left();
        data.setFillParent(true);
        stage.addActor(data);
        data.left().top().pad(10, 10, 0, 0);
        int i = 0;
        for (String str: gov.getEconomy().getInfo()){
            lData[i] = new Label(str, skin);
            data.add(lData[i]);
            data.row();
        }
        //add back button
        addBackButton();
        //add information about spheres
        slabel = new Label[BS.numberEconomySpheres];
        Table sTable = new Table();
        sTable.debug();
        sTable.defaults().left();
        sTable.setFillParent(true);
        stage.addActor(sTable);
        sTable.left().top().pad(240, 10, 0, 0);
        int counter = 0;
        for (String str: gov.getEconomy().getShares()){
            sTable.defaults().size(400,50);
            slabel[counter] = new Label(str, skin);
            sTable.add(slabel[counter]);
            final Slider slider = new Slider(0, 100, 1, false, skin);
            slider.setValue(gov.getEconomy().getAcShare(counter));
            final int num = counter;
            slider.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        System.out.println(slider.getValue());
                        gov.getEconomy().changeShares(num, 1.0*slider.getValue()/100);
                        System.out.println(gov.getEconomy().getAcShare(num));
                    updateData();
                    return false;
                }
            });
            counter += 1;
            sTable.defaults().size(500,50);
            sTable.add(slider);
            sTable.row();

        }
        //taxes
        tLable = new Label(gov.getEconomy().getInfo()[5], skin);
        Table tTable = new Table();
        tTable.debug();
        tTable.defaults().left();
        tTable.setFillParent(true);
        stage.addActor(tTable);
        tTable.left().top().pad(180, 10, 0, 0);
        tTable.defaults().size(400, 50);
        tTable.add(tLable);
        final Slider taxSlider = new Slider(0, 100, 1, false, skin);
        tTable.add(taxSlider);
        taxSlider.setValue((int) (gov.getEconomy().getTaxRate()*100));
        taxSlider.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gov.getEconomy().changeTaxes(1.0*taxSlider.getValue()/100);
                updateData();
                return false;
            }
        });
    }



    private void updateData(){
        int i = 0;
        for (String str: gov.getEconomy().getInfo()){
            lData[i].setText(str);
        }
        i = 0;
        for (String str: gov.getEconomy().getShares()){
            slabel[i].setText(str);
            i += 1;
        }
        tLable.setText(gov.getEconomy().getInfo()[5]);
    }

    @Override
    public void renderh() {
        updateData();
    }

}
