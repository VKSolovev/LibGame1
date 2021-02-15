package com.some.game1.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Strategy;

public class someScreen implements Screen {
    Strategy strategy;
    int curPlayer;
    private Stage stage;
    private Table container;
    private Gov gov = new Gov();
    private Label label;

    @Override
    public void show() {
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        // Gdx.graphics.setVSync(false);

        container = new Table();
        stage.addActor(container);
        container.setFillParent(true);
        // table.debug();
        label = new Label("" + (BS.turn), skin);
        container.addActor(label);
        TextButton button = new TextButton("Turn", skin);
        container.add(button);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                gov.turn(true);
            }
        });
        TextButton button1 = new TextButton("Turn", skin);
        container.add(button1);
        button1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                gov.turn(true);
            }
        });

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
        label.setText("" + (BS.turn));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
