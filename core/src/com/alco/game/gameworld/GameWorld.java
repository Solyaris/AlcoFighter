package com.alco.game.gameworld;

import com.alco.game.GameObjects.Player;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by kirya on 27.11.2017.
 */

public class GameWorld {
    float delay;
    public ArrayList<Player> players;
    Circle boundingCircle1, boundingCircle2;
    public GameWorld() {
        players = new ArrayList<Player>(2);
        for(int number  = 0; number < 2; number++){
            Player player = new Player(number);
            players.add(player);
        }
        delay = 0;
    }

    public void update(float delta, float runtime){
        for(Player player : players){
        player.update(delta, runtime);
        checkBounds(player);
        }
       if(delay <= 0)
        checkCollision(players.get(0), players.get(1));
        else delay -= delta;


    }

    public void checkBounds(Player player) {

            if (player.getPosition().x >= 1280 - player.getRadius() ||
                    player.getPosition().x <= 0 + player.getRadius()) {
                //player.setDirection(-player.getDashDirection().x, player.getDashDirection().y);
               // player.setVelocity(-player.getDashPower(), player.getDashPower());
                player.changeVelocity(true);
            }
            if (player.getPosition().y >= 720 - Player.getRadius() ||
                    player.getPosition().y <= 0 + Player.getRadius()) {
           //     player.setDirection(player.getDashDirection().x, -player.getDashDirection().y);
             //   player.setVelocity(player.getDashPower(), -player.getDashPower());
                player.changeVelocity(false);
            }
    }

    public void checkCollision(Player player1, Player player2){
        float weight =  Math.abs(player1.getPosition().x - player2.getPosition().x);
        float height = Math.abs(player1.getPosition().y - player2.getPosition().y);

        if(height * height + weight * weight <= Player.getRadius() * Player.getRadius() * 4 + 100){
            double angle = Math.abs(Math.toDegrees(Math.asin(-height / (Player.getRadius() * 2))));
            double alpha = Math.toDegrees(Math.acos(height / (Player.getRadius() * 2)));
            Vector2 vertical = new Vector2(0, 1);
            Vector2 norm = new Vector2(player1.getPosition().x - player2.getPosition().x,player1.getPosition().y - player2.getPosition().y );
            angle = norm.angle(vertical);
            System.out.println("Vertical : " + norm.angle(vertical));
            System.out.println("alpha: "  + alpha);
           // System.out.println(Math.toDegrees(alpha));
//            player1.setVelocityAngle(player1.getVelocity().angle() - alpha);
//            player2.setVelocityAngle(player2.getVelocity().angle() - alpha);

            player1.setVelocityAngle(player1.getVelocity().angle() + angle);
            player2.setVelocityAngle(player2.getVelocity().angle() + angle);


            float temp = player1.getVelocity().y;
            player1.setVelocity(player1.getVelocity().x, player2.getVelocity().y);
            player2.setVelocity(player2.getVelocity().x, temp);

//            player1.setVelocityAngle(player1.getVelocity().angle() + alpha);
//            player2.setVelocityAngle(player2.getVelocity().angle() + alpha);

            player1.setVelocityAngle(player1.getVelocity().angle() - angle);
            player2.setVelocityAngle(player2.getVelocity().angle() - angle);

            delay = 1;
//            System.out.println("NOW:");
//            System.out.println(Math.toDegrees(player1.getVelocity().angle())% 360);
//            System.out.println(Math.toDegrees(player2.getVelocity().angle())% 360);

        }
    }


}
