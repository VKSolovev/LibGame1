package com.some.game1.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.some.game1.Strategy;

public class MainMenu extends ScreenBase {

    public MainMenu(Strategy strategy) {
        this.strategy = strategy;
        gov = strategy.getGov();
    }

    Button start;
    Button exit;
    Table table;
    Texture startButtonTexture;
    Texture exitButtonTexture;

    @Override
    public void showh() {
        startButtonTexture = new Texture(Gdx.files.internal("res\\start_button.png"));
        exitButtonTexture = new Texture(Gdx.files.internal("res\\exit_button.png"));
        start = new ImageButton(new TextureRegionDrawable(new TextureRegion(startButtonTexture)));
        exit = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitButtonTexture)));
        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        table = new Table();
        //stage.addActor(table);
        table.center();
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        start.setSize(col_width*4,row_height);
        start.setPosition(col_width,Gdx.graphics.getHeight()-row_height*3);
        start.setTransform(true);
        start.scaleBy(0.5f);
        exit.setSize(col_width*4,row_height);
        exit.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*3);
        exit.setTransform(true);
        exit.scaleBy(0.5f);
        stage.addActor(start);
        stage.addActor(exit);
        //table.add(exit).size(150, 200).center();
        //table.add(start).size(200, 400).center(); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui
        start.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                stage.clear();
                strategy.setScreen(new MainScreen(strategy));
                return true;

            }
        });
        exit.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("scroll", "Exit"); //** Usually used to start Game, etc. **//
                stage.clear();
                System.exit(0);
                return true;

            }
        });
    }
}
