package com.example.androidgame.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.androidgame.GameLoop;
import com.example.androidgame.R;

public class Spell extends Circle {
    private final Player spellcaster;
    public static final double SPEED_PIXELS_PER_SECONDS = 700.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECONDS / GameLoop.MAX_UPS;
    public Spell(Context context, Player spellcaster) {
        super(
                context,
                ContextCompat.getColor(context, R.color.spell),
                spellcaster.getPositionX(),
                spellcaster.getPositionY(),
                10
        );

        this.spellcaster = spellcaster;

        velocityX = spellcaster.getDirectionX() * MAX_SPEED;
        velocityY = spellcaster.getDirectionY() * MAX_SPEED;
    }

    @Override
    public void update() {

        positionX += velocityX;
        positionY += velocityY;

    }
}
