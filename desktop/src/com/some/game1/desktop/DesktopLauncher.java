package com.some.game1.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.some.game1.Strategy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1600;
		config.height = 900;
		config.x = 0;
		config.y = 0;


		// полноэкранный режим
		config.fullscreen = true;
		// вертикальная синхронизация
		config.vSyncEnabled = true;
		new LwjglApplication(new Strategy(), config);
	}
}
