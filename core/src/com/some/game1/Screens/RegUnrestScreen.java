package com.some.game1.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Strategy;

import java.util.ArrayList;

public class RegUnrestScreen extends ScreenBase {
    private ArrayList<String> info;


    public RegUnrestScreen(ArrayList<String> info, Strategy strategy) {
        this.info = info;
        this.strategy = strategy;
    }

    @Override
    public void showh(){
        addBackButton();
        Table container = new Table();
        for (String string: info){
            container.add(new Label(string, skin));
            container.row();
        }
        container.setFillParent(true);
        stage.addActor(container);

    }

    @Override
    protected void addBackButton(){
        TextButton button = new TextButton("Back", skin);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(new RegionsScreen(strategy));
            }
        });
        Table backB = new Table();
        backB.setFillParent(true);
        stage.addActor(backB);
        backB.defaults().size(120, 80);
        backB.right().top();
        backB.add(button);
    }
}
