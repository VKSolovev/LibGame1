package com.some.game1.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Entities.Lows.Law;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Strategy;


public class LawsScreen extends ScreenBase {

    public LawsScreen(Strategy strategy) {
        this.strategy = strategy;
        gov = strategy.getGov();
    }

    @Override
    public void showh() {
        addBackButton();
        Table lTable = new Table();
        final ScrollPane scroll = new ScrollPane(lTable, skin);
        Table container = new Table();
        container.add(scroll);
        scroll.setFillParent(true);
        container.setWidth(stage.getWidth()/2);
        container.setHeight(stage.getHeight());
        container.debugAll();
        //container.setFillParent(true);
        //container.setX(0);
        //container.setY(0);
        container.left().center();
        container.pad(0, 0, 0, stage.getWidth()/2);
        stage.addActor(container);
        for (final Law law: gov.getLaws().getLaws()){
            Table table = new Table();
            for (String info: law.getInfo()){
                table.add(new Label(info, skin));
                table.row();
            }
            TextButton aBut = new TextButton("Accept ", skin);
            TextButton dBut = new TextButton("Decline", skin);
            aBut.top();
            dBut.top();
            Table buttons = new Table();
            buttons.add(aBut);
            buttons.add(dBut);
            lTable.add(table);
            lTable.row();
            lTable.add(buttons);
            lTable.row();
            lTable.add(new Label("", skin));
            lTable.row();
            if (law.isActivated()){
                aBut.setColor((float) 20.0 / 255, (float) 190.0 / 255, (float) 1, (float) 1);
            } else {
                dBut.setColor((float) 20.0 / 255, (float) 190.0 / 255, (float) 1, (float) 1);
            }
            aBut.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    law.accept();
                    showh();
                }
            });
            dBut.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    law.deny();
                    showh();
                }
            });

        }
    }

    @Override
    public void renderh() {

    }
}
