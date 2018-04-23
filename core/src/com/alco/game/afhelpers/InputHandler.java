package com.alco.game.afhelpers;

import com.alco.game.GameObjects.Player;
import com.badlogic.gdx.InputProcessor;
import com.alco.game.gameworld.GameWorld;

import java.util.ArrayList;

/**
 * Created by kirya on 30.11.2017.
 */

public class InputHandler implements InputProcessor {
    ArrayList<Player> players;

    public InputHandler(ArrayList<Player> players) {
        this.players = players;
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenX > 640){
            players.get(1).onTouchDown();
        }
        else players.get(0).onTouchDown();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenX > 640){
            players.get(1).onTouchUp();
        }
        else players.get(0).onTouchUp();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
