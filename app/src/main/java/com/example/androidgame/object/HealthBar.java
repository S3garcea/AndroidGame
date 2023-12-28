package com.example.androidgame.object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.androidgame.R;

/**
 * HealthBar displays the player's health to the screen
 */
public class HealthBar {

    private Player player;
    private int width, height, margin;
    private Paint backgroundPaint, healthPaint1, healthPaint2, healthPaint3, healthPaint4;

    public HealthBar (Context context, Player player) {

        this.player = player;
        this.width = 180;
        this.height = 30;
        this.margin = 2;

        this.backgroundPaint = new Paint();
        int backgroundColor = ContextCompat.getColor(context, R.color.healthBarBackground);
        backgroundPaint.setColor(backgroundColor);

        this.healthPaint1 = new Paint();
        int healthColor1 = ContextCompat.getColor(context, R.color.healthBar1);
        healthPaint1.setColor(healthColor1);

        this.healthPaint2 = new Paint();
        int healthColor2 = ContextCompat.getColor(context, R.color.healthBar2);
        healthPaint2.setColor(healthColor2);

        this.healthPaint3 = new Paint();
        int healthColor3 = ContextCompat.getColor(context, R.color.healthBar3);
        healthPaint3.setColor(healthColor3);

        this.healthPaint4 = new Paint();
        int healthColor4 = ContextCompat.getColor(context, R.color.healthBar4);
        healthPaint4.setColor(healthColor4);

    }

    public void draw (Canvas canvas) {

        float x = (float) player.getPositionX();
        float y = (float) player.getPositionY();
        float distanceToPlayer = 55;
        float healthPointsPerecentage = (float) player.getHealthPoints()/player.MAX_HEALTH_POINTS;

        // Draw background
        float backgroundLeft, backgroundTop, backgroundRight, backgroundBottom;

        backgroundLeft = x - width/2;
        backgroundRight = x + width/2;
        backgroundBottom = y - distanceToPlayer;
        backgroundTop = backgroundBottom - height;

        canvas.drawRect(backgroundLeft, backgroundTop, backgroundRight, backgroundBottom, backgroundPaint);

        // Draw health
        float healthLeft, healthTop, healthRight, healthBottom, healthWidth, healthHeight;

        healthWidth = width - 2 * margin;
        healthHeight = height - 2 * margin;
        healthLeft = backgroundLeft + margin;
        healthRight = backgroundLeft + healthWidth * healthPointsPerecentage;
        healthBottom = backgroundBottom - margin;
        healthTop = healthBottom - healthHeight;

        // Figuring how much health color

        if (healthPointsPerecentage > 0.80) {

            canvas.drawRect(healthLeft, healthTop, healthRight, healthBottom, healthPaint1);

        } else if (healthPointsPerecentage <= 0.80 && healthPointsPerecentage > 0.55) {

            canvas.drawRect(healthLeft, healthTop, healthRight, healthBottom, healthPaint2);

        } else if (healthPointsPerecentage <= 0.55 && healthPointsPerecentage > 0.30) {

            canvas.drawRect(healthLeft, healthTop, healthRight, healthBottom, healthPaint3);

        } else if (healthPointsPerecentage <= 0.30 && healthPointsPerecentage > 0.0) {

            canvas.drawRect(healthLeft, healthTop, healthRight, healthBottom, healthPaint4);

        }



    }
}
