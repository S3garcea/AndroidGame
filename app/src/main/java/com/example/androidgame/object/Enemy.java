package com.example.androidgame.object;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.androidgame.GameLoop;
import com.example.androidgame.R;
import com.example.androidgame.SpriteEnemy;

/**
 * Enemy is a character, extension of Circle
 * Enemy always moves in the direction of the Player
 */
public class Enemy extends Circle{

    private static final double SPEED_PIXELS_PER_SECONDS = Player.SPEED_PIXELS_PER_SECONDS * 0.7;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECONDS / GameLoop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 10;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    // private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;
    private final SpriteEnemy spriteEnemy;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius, SpriteEnemy spriteEnemy) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);
        this.player = player;
        this.spriteEnemy = spriteEnemy;
    }

    /*
    public Enemy(Context context, Player player, SpriteEnemy spriteEnemy) {
        super(
                context,
                ContextCompat.getColor(context, R.color.enemy),
                Math.random() * 1000,
                Math.random() * 1000,
                30
        );

        this.player = player;
        this.spriteEnemy = spriteEnemy;
    }

     */



    /** readyToSpawn checks if a new enemy should spawn according to the decided number of
     * spawns per minute (see SPAWNS_PER_MINUTE at top)
     */
    public static boolean readyToSpawn() {

        if (updatesUntilNextSpawn <= 0) {

            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            //
            UPDATES_PER_SPAWN *= 0.97;
            //

            return true;
        } else {
            updatesUntilNextSpawn --;
            return false;
        }

    }

    @Override
    public void update() {

        /**
         * Update the velocity of the Enemy
         */

        // Calculate vector from enemy to player
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        // Calculate (absolute) distance between enemy (this) and
        // player

        double distanceToPlayer = getDistanceBetweenObjects(this, player);

        // Calculate direction from enemy to player
        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        // Set velocity in the direction of player
        if (distanceToPlayer > 0) {

            // Avoid division by 0
            velocityX = directionX * MAX_SPEED;
            velocityY = directionY * MAX_SPEED;

        }

        else {

            velocityX = 0;
            velocityY = 0;

        }

        // Update the position of the enemy

        positionX += velocityX;
        positionY += velocityY;

    }

    public void draw(Canvas canvas) {

        spriteEnemy.draw(canvas, (int) getPositionX() - 50, (int) getPositionY() - 50);
    }

}
