package com.some.game1.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Entities.MainComponents.BS;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Entities.Technical.Texts.Event;
import com.some.game1.Strategy;

import javax.swing.plaf.basic.BasicOptionPaneUI;

public class EventScreen implements Screen {
    private final Event event;
    protected Gov gov;
    protected Stage stage;
    protected Skin skin;
    protected Strategy strategy;

    public EventScreen(Event event, Gov gov, Strategy strategy) {
        this.gov = gov;
        this.event = event;
        this.strategy = strategy;
    }

    @Override
    public void show() {
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        Table container = new Table();
        Label name = new Label(event.getName(), skin);
        //name.setFillParent(true);
        container.add(name);
        Label text = new Label(event.getText(), skin);
        //text.setFillParent(true);
        container.row();
        container.add(text);
        container.row();
        for (int i = 0; i < event.getAnswers().length; i++){
            TextButton button = new TextButton(event.getAnswers()[i], skin);
            //button.setFillParent(true);
            final int ide = event.getId();
            final int choice = i;
            button.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    gov.getEventListener().listen(ide, choice);
                    strategy.setScreen(new MainScreen(strategy));
                }
            });
            container.add(button);
            container.row();
        }
        container.setFillParent(true);
        container.debug();
        stage.addActor(container);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
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

    }
}
