package com.alco.game.screens;

import com.alco.game.afhelpers.InputHandler;
import com.alco.game.gameworld.GameRenderer;
import com.alco.game.gameworld.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kirya on 27.11.2017.
 */

public class GameScreen implements Screen {
    private GameRenderer renderer;
    private GameWorld gameWorld;
    private float runTime;
    public GameScreen() {
        gameWorld = new GameWorld();
        renderer = new GameRenderer(gameWorld);
        Gdx.input.setInputProcessor(new InputHandler(gameWorld.players));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime+= delta;
        renderer.render(runTime);
        gameWorld.update(delta, runTime);
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
