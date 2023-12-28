package com.example.androidgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class SpriteSheet {

    private Bitmap bitmap;

    public SpriteSheet(Context context) {

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tataita, bitmapOptions);
    }

    public Sprite getPlayerSprite() {
        return new Sprite(this, new Rect(0, 0, 140, 140));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
