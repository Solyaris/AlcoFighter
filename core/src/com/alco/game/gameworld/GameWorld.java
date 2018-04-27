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
        if (delay <= 0)
        for(int i = 0; i < players.size() - 1; i++) {
            for(int j = i + 1; j < players.size(); j++){
                checkCollision(players.get(i), players.get(j));
        }}
        else delay -= delta;
    }

    public void checkBounds(Player player) {

            if (player.getPosition().x >= 1280 - player.getRadius() ||
                    player.getPosition().x <= 0 + player.getRadius()) {
                player.changeVelocity(true);
            }
            if (player.getPosition().y >= 720 - Player.getRadius() ||
                    player.getPosition().y <= 0 + Player.getRadius()) {
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
            angle = norm.angle(vertical); // находим угол между полученной нормалью (вектор, соединяющий центры окружностей) и осью Y

            float accuracy1 = 180  - Math.abs(norm.angle(player1.getVelocity()));
            float accuracy2 = Math.abs(norm.angle(player2.getVelocity()));

            if(accuracy1 < 45){
                damageDeal(player2, (int) ((1 - accuracy1 / 45) * (player1.getTempVelocity().len2() / 80000 * 25))); // 8к тип максимально возможный квардрат длины вектора темскорости, который наносит 25 урона
            }
            if(accuracy2 < 45){
                damageDeal(player1, (int) ((1 - accuracy2 / 45) * (player2.getTempVelocity().len2() / 80000 * 25))); // 8к тип максимально возможный квардрат длины вектора темскорости, который наносит 25 урона
            }

            player1.setVelocityAngle(player1.getVelocity().angle() + angle); // рассматриваем вектора скоростей в новой система координат с нормалью в роли оси Yъ
            player2.setVelocityAngle(player2.getVelocity().angle() + angle);


            float temp = player1.getVelocity().y;
            player1.setVelocity(player1.getVelocity().x, player2.getVelocity().y); // скорости по оси X остаются неизменными, а по оси Y меняются
            player2.setVelocity(player2.getVelocity().x, temp);

            player1.setVelocityAngle(player1.getVelocity().angle() - angle); // возвращаем в дефолтную систему координат
            player2.setVelocityAngle(player2.getVelocity().angle() - angle);

            delay = 1;

        }
    }
    public void damageDeal(Player player1, int damage){
        player1.setHealth(player1.getHealth() - damage);
        if(player1.getHealth() <= 0)
        players.remove(player1.getNumber());
    }


}
