package com.some.game1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.some.game1.Entities.MainComponents.Gov;
import com.some.game1.Screens.MainMenu;
import com.some.game1.Screens.MainScreen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Strategy extends Game {
	ImageButton start;
	ImageButton exit;
	Texture startButtonTexture;
	Texture exitButtonTexture;
	Stage stage;
	Table table;
	int Help_Guides = 12;
	int row_height;
	int col_width;
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public SpriteBatch batch;
	private Gov gov = new Gov();

	public Gov getGov() {
		return gov;
	}

	@Override
	public void resize(int width, int height) {
		row_height = width / 12;
		col_width = height / 12;
		stage.getViewport().update(width, height);
		//super.resize(width, height);
	}

	@Override
	public void create () {

		//gov.turn(true);

		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
		Gdx.input.setInputProcessor(stage); //Start taking input from the ui
		stage.clear();
		setScreen(new MainMenu(Strategy.this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor((float) 0.7, (float) 0.8, (float) 0.9, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		batch.begin();
		stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
		stage.draw(); //Draw the ui
		batch.end();
	}

	@Override
	public void dispose () {
		startButtonTexture.dispose();
		exitButtonTexture.dispose();
		stage.dispose();
		batch.dispose();
	}

	public void saveGame() throws IOException {
		String dir = System.getProperty("user.dir");
		FileOutputStream fileOutputStream = new FileOutputStream(dir + "\\savegame.svg");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

		objectOutputStream.writeObject(gov);

		objectOutputStream.close();

		System.out.println("Everything is Ok");

	}

	public void loadGame() throws IOException, ClassNotFoundException {
		String dir = System.getProperty("user.dir");
		FileInputStream fileInputStream = new FileInputStream(dir + "\\savegame.svg");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

		gov = (Gov) objectInputStream.readObject();

		System.out.println(gov.getEconomy().getInfo()[0]);
		System.out.println(dir);

		setScreen(new MainMenu(Strategy.this));
	}
}
