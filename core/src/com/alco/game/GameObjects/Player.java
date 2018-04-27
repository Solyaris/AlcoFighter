 package com.alco.game.GameObjects;

import com.alco.game.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by kirya on 30.11.2017.
 */

public class Player {
    static final float RADIUS = 25;
    static float MAX_TIME = 2;
    private Vector2 position, velocity, tempVelocity, direction, dashDirection;
    private int health;
    private float dashPower, runTime, runTime0, dashDuration;
    private int number;
    public float delay;
    private double angle;
    private boolean isPressed = false;
    float delta;

    public static float getMaxTime() {
        return MAX_TIME;
    }

    public float getDashTime(){
        if(isPressed)
            return 2 - Math.abs((runTime - runTime0) % 4 - 2);
        return 0;
    }

    public Player(int number){
        this.number = number;
        Random r = new Random();
        position = new Vector2(r.nextInt((int) (640 - RADIUS * 2)) + (640 * number + RADIUS),
                r.nextInt(720 - 50) + 25); //игрок респится в своей части экрана, учтены радиусы игроков, чтобы не респиться за край карты
        direction = new Vector2();
        tempVelocity = new Vector2();
        velocity = new Vector2();
        dashDirection = new Vector2();
        health = 100;
    }

    public void update(float delta, float runtime){
        this.delta = delta;
        this.runTime = runtime;
        if(velocity.x > 0 && tempVelocity.x > 0 || velocity.x < 0 && tempVelocity.x < 0)
            dash(delta);
//        else if(delay > 0){
//            delay -= delta;
////        }
//         if(dashDuration > 0){
//          dashDuration -= delta;  }
        else {
            velocity.set(0, 0);
            angle = (angle + (delta * 90)) % 360;
            setDirection((float)angle);
        }
    }

    public void onTouchDown(){
        if(!(velocity.x > 0 && tempVelocity.x > 0 || velocity.x < 0 && tempVelocity.x < 0)) {
            runTime0 = runTime;
            isPressed = true;
        }
    }

    public void dash(float delta){
        position.add(tempVelocity.cpy().scl(delta));
        velocity.add(-tempVelocity.x * delta, -tempVelocity.y * delta); // С ДЭШПАВЕРОМ РАЗОБРАТЬСЯ!!! ПРИ НЕРВОЗНОЙ СИТУАЦИИ, УБРАТЬ ЕГО К ХУЯМ
        if(!(velocity.x > 0 && tempVelocity.x > 0 || velocity.x < 0 && tempVelocity.x < 0))
            tempVelocity.set(0, 0);
    }

    public void onTouchUp() {
        if(!(velocity.x > 0 && tempVelocity.x > 0 || velocity.x < 0 && tempVelocity.x < 0) && isPressed == true) {
            isPressed = false;
            dashDirection.set(direction.x, direction.y);
            dashPower = MAX_TIME - Math.abs((runTime - runTime0) % (MAX_TIME * 2) - MAX_TIME);// Устанавливаем dashPower, не более 2 секунд
            dashDuration = dashPower;
            setVelocity(dashPower * RADIUS * 4, dashPower * RADIUS * 4, (float) angle);
        }
    }
    public float sin(float runTime){
        return (float) Math.sin(Math.toRadians((runTime % 4) * 90));
    }// получаем на вход время. Вычисляем синус. За 4 секунды поворачиваемся на 360

    public float cos(float runTime){
        return (float) Math.cos(Math.toRadians((runTime % 4) * 90));
    }// получаем на вход время. Вычисляем косинус. За 4 секунды поворачиваемся на 360

    public void setDirection(float angle) {
        direction.set(25 * (float)Math.cos(Math.toRadians(angle)), 25 * (float)Math.sin(Math.toRadians(angle)));
    }
    public static float getRadius() {
        return RADIUS;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public int getHealth() {
        return health;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public int getNumber() {
        return number;
    }

    public void setVelocity(float powerX, float powerY, double angle) {
        velocity.set(powerX, powerY);
        velocity.setAngle((float)angle);
        tempVelocity.set(velocity);
    }
    public void changeVelocity(boolean isX){
        if(isX){
            velocity.set(-velocity.x, velocity.y);
            tempVelocity.set(-tempVelocity.x, tempVelocity.y);
        }
        else {
            velocity.set(velocity.x, -velocity.y);
            tempVelocity.set(tempVelocity.x, -tempVelocity.y);
        }
    }
    public void setVelocityAngle(double angle){
        velocity.setAngle((float) angle);
        tempVelocity.setAngle((float) angle);
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void setVelocity(float x, float y) {
        velocity.set(x, y);
        tempVelocity.set(velocity);
    }

    public Vector2 getTempVelocity() {
        return tempVelocity;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
