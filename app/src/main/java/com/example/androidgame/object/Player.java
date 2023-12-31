package com.example.androidgame.object;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.androidgame.GameLoop;
import com.example.androidgame.Joystick;
import com.example.androidgame.R;
import com.example.androidgame.Sprite;
import com.example.androidgame.Utils;

/**
 *  Player is the main character of the game
 *
 *  which the user can control with a touch joystick
 *
 *  The player class is an extension of the Circle class, which in turn is an extension
 *  of the GameObject class
 */
public class Player extends Circle {

    /*

    private double positionX;
    private double positionY;

    private double velocityX;
    private double velocityY;

     */

    private final Joystick joystick;
    public static final double SPEED_PIXELS_PER_SECONDS = 255.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECONDS / GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 100;
    private HealthBar healthBar;
    private int healthPoints;
    private Sprite sprite;

    public Player (Context context, Joystick joystick, double positionX, double positionY, double radius, Sprite sprite) {

        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);

        /*
        this.positionX = positionX;
        this.positionY = positionY;
         */

        this.joystick = joystick;
        this.healthBar = new HealthBar(context, this);
        this.healthPoints = MAX_HEALTH_POINTS;
        this.sprite = sprite;

    }

    public void update() {

        // Update velocity based on actuator of Joystick

        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        // Update position

        // I'll try adding the screen boundaries here

        if (positionX > 2020) {
            positionX -= 1;
        } else if (positionX < 140) {
            positionX += 1;
        } else {
                positionX += velocityX;
            }

        if (positionY > 950) {
            positionY -= 1;
        } else if (positionY < 190) {
            positionY += 1;
        } else {
            positionY += velocityY;
        }

        /*
        positionX += velocityX;
        positionY += velocityY;
         */

        // Update direction
        if (velocityX != 0 || velocityY != 0) {

            // Normalize velocaity to get direction (unit vector of velocity)

            double distance = Utils.getDistanceBetweenPoints(0,0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }

    }

    public void draw(Canvas canvas) {

        // super.draw(canvas);

        sprite.draw(canvas, (int) getPositionX() - 105, (int) getPositionY() - 105);
        healthBar.draw(canvas);

    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {

        // Only allow positive values
        if (healthPoints >= 0)
            this.healthPoints = healthPoints;
        else
            this.healthPoints = 0;
    }
}
