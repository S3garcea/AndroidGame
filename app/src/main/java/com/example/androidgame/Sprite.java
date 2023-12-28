package com.example.androidgame;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    private final SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {

        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    public void draw(Canvas canvas, int x, int y) {

        canvas.drawBitmap(
                spriteSheet.getBitmap(),
                rect,
                new Rect(x, y, x + 140, y + 140),
                null
        );
    }
}
