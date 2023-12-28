package com.example.androidgame;

import android.content.Context;

import androidx.core.content.ContextCompat;

/**
 *  Player is the main character of the game
 *
 *  which the user can control with a touch joystick
 *
 *  The player class is an extension of the Circle class, which in turn is an extension
 *  of the GameObject class
 */
class Player extends Circle {

    /*

    private double positionX;
    private double positionY;

    private double velocityX;
    private double velocityY;

     */

    private final Joystick joystick;
    public static final double SPEED_PIXELS_PER_SECONDS = 255.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECONDS / GameLoop.MAX_UPS;

    public Player (Context context, Joystick joystick, double positionX, double positionY, double radius) {

        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);

        /*
        this.positionX = positionX;
        this.positionY = positionY;
         */

        this.joystick = joystick;

    }



    public void update() {

        // Update velocity based on actuator opf Joystick

        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        // Update position
        positionX += velocityX;
        positionY += velocityY;

    }

}
