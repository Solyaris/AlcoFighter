package com.alco.game.gameworld;

import com.alco.game.GameObjects.Player;
import com.alco.game.afhelpers.AssetLoader;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by kirya on 27.11.2017.
 */

public class GameRenderer{
    private Player player;
    private SpriteBatch batcher = new SpriteBatch();
    private ShapeRenderer renderer = new ShapeRenderer();
    private OrthographicCamera cam;
    private GameWorld myWrold;
    public GameRenderer(GameWorld World) {
        myWrold = World;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 1280, 720);
        renderer.setProjectionMatrix(cam.combined);
        //player = World.getPlayer();
    }
    public void render(float runTime){
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(Color.BROWN);
        renderer.rect(0, 0, 1280, 720);

        renderer.setColor(Color.GREEN);
        for(Player player : myWrold.players) {


            renderer.circle(player.getPosition().x, player.getPosition().y, player.getRadius());
            renderer.setColor(Color.CORAL);
            renderer.rectLine(player.getPosition().x, player.getPosition().y,//X0 Y0 Отображение направления движения
                    (player.getPosition().x + player.getDirection().x * 2), //X
                    (player.getPosition().y + player.getDirection().y * 2), //Y
                    2);//WEIGHT

            renderer.setColor(Color.RED);
            renderer.rectLine(player.getPosition().x, player.getPosition().y,//X0 Y0 Отображение направления движения
                    (player.getPosition().x + player.getVelocity().x), //X
                    (player.getPosition().y + player.getVelocity().y),//Y
                    5);
            renderer.rect(player.getPosition().x - 25, player.getPosition().y + 30, player.getHealth(), 5);
            renderer.rect(player.getPosition().x - 25, player.getPosition().y + 35, player.getDashTime() / (Player.getMaxTime()) * 100, 5, Color.BLACK, Color.BLUE, Color.FIREBRICK, Color.CYAN);
        }
        renderer.end();
    }
}
