package object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.androidgame.GameLoop;
import com.example.androidgame.R;

/**
 * Enemy is a character, extension of Circle
 * Enemy always moves in the direction of the Player
 */
public class Enemy extends Circle{

    private static final double SPEED_PIXELS_PER_SECONDS = Player.SPEED_PIXELS_PER_SECONDS * 0.7;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECONDS / GameLoop.MAX_UPS;
    private final Player player;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);
        this.player = player;
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

        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

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
}
