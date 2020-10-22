package com.some.game1.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Strategy;

public abstract class ScreenBase implements Screen {
    protected Gov gov;
    protected Stage stage;
    protected Skin skin;
    protected Strategy strategy;

    @Override
    public void show() {
        stage = new Stage();
        stage.addListener(new InputListener(){
            public boolean keyDown (InputEvent event, int keycode) {
                System.out.println(keycode);
                if (keycode == 131) {
                    menu.setVisible(true);
                }
                return false;
            }
        });
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Gdx.input.setInputProcessor(stage);
        showh();
        addMenu();
    }

    protected void showh(){

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
        renderh();
    }

    public void renderh(){

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

    private Button backButton(){
        TextButton button = new TextButton("Back", skin);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(new MainScreen(strategy));
            }
        });
        return button;
    }

    protected void addBackButton(){
        //add back button
        Table backB = new Table();
        backB.setFillParent(true);
        stage.addActor(backB);
        backB.defaults().size(120, 80);
        backB.right().top();
        backB.add(backButton());
    }
    private final Table menu = new Table();

    private void addMenu(){
        stage.addActor(menu);
        menu.setVisible(false);
        menu.setX(stage.getWidth()/2);
        menu.setY(stage.getHeight()/2);
        TextButton contin = new TextButton("Continue", skin);
        TextButton save = new TextButton("Save", skin);
        TextButton load = new TextButton("Load", skin);
        TextButton exit = new TextButton("Exit", skin);
        TextButton exitToMenu = new TextButton("Exit to main menu", skin);
        menu.defaults().size(stage.getWidth()/8, stage.getHeight()/10);
        menu.defaults().pad(10);
        menu.add(contin);
        menu.row();
        menu.add(save);
        menu.row();
        menu.add(load);
        menu.row();
        menu.add(exit);
        menu.row();
        menu.add(exitToMenu);
        contin.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                menu.setVisible(false);
            }
        });
        exit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });

        exitToMenu.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(new MainMenu(strategy));
            }
        });
    }
}
