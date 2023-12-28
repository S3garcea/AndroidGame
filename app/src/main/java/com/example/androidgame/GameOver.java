package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class GameOver {

    private Context context;

    public GameOver (Context context) {

        this.context = context;
    }
    public void draw(Canvas canvas) {

        String text = "Ruina";

        float x = 1000;
        float y = 300;

        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        float textSize = 150;
        paint.setTextSize(textSize);

        canvas.drawText(text, x, y, paint);

    }
}
