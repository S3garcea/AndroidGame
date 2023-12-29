package com.example.androidgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class SpriteSheetEnemy {

    private Bitmap bitmap;

    public SpriteSheetEnemy(Context context) {

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.floricea, bitmapOptions);
    }

    public SpriteEnemy getEnemySprite() {
        return new SpriteEnemy(this, new Rect(0, 0, 100, 100));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}