package com.alco.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.alco.game.AFGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "AlcoFighter";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new AFGame(), config);
	}
}