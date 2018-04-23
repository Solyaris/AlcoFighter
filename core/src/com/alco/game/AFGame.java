package com.alco.game;

		import com.alco.game.afhelpers.AssetLoader;
		import com.alco.game.screens.GameScreen;
		import com.badlogic.gdx.Game;


public class 	AFGame extends Game {

	@Override
	public void create () {
		setScreen(new GameScreen());
		AssetLoader.load();
	}

	@Override
	public void dispose () {
		super.dispose();
		AssetLoader.dispose();
	}
}
