package com.example.androidgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.content.ContextCompat;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.androidgame.object.Circle;
import com.example.androidgame.object.Enemy;
import com.example.androidgame.object.Player;
import com.example.androidgame.object.Spell;

/*
Game manages all objects in the game and is responsible for updating all states and render all objects to the screen
*/

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final Joystick joystick;
    private final Player player;
    private GameLoop gameLoop;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellList = new ArrayList<Spell>();
    private int joystickPointerId = 0;
    private int numberOfSpellsToCast = 0;
    private GameOver gameOver;
    private SpriteEnemy spriteEnemy;

    public Game(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        // Inititalize Game Panels
        gameOver = new GameOver(getContext());

        // Initialize game objects
        joystick = new Joystick(275, 750, 70, 40);

        // Initialize player
        SpriteSheet spriteSheet = new SpriteSheet(context);
        player = new Player(getContext(), joystick, 1000,500, 35, spriteSheet.getPlayerSprite());

        // Prepare pop-corn
        SpriteSheetEnemy spriteSheetEnemy = new SpriteSheetEnemy(context);
        spriteEnemy = spriteSheetEnemy.getEnemySprite();

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joystick.getIsPressed()) {

                    // Joystick was already pressed -> cast spell
                    numberOfSpellsToCast++;
                }
                else if (joystick.isPressed((double) event.getX(), (double) event.getY())) {

                    // Joystick is pressed in this event -> setIsPressed(true)
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else {

                    // Joystick was not pressed in previous, present ->  cast spell
                    numberOfSpellsToCast++;

                }

                /*
                if (joystick.isPressed((double) event.getX(), (double) event.getY()))
                {
                    joystick.setIsPressed(true);
                }

                return true;

                 */

            case MotionEvent.ACTION_MOVE:

                // Joystick was pressed previously and is now moved
                if(joystick.getIsPressed())
                {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }

                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:

                if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    // Joystick was let go of -> setIsPressed(false) and resetActuator
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }

                return true;

        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        joystick.draw(canvas);
        player.draw(canvas);

        for (Enemy enemy : enemyList) {
            enemy.draw(canvas);
        }

        for (Spell spell : spellList) {
            spell.draw(canvas);
        }

        // Draw Game Over if the player is dead
        if (player.getHealthPoints() <= 0) {
            gameOver.draw(canvas);
        }
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 50, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 95, paint);
    }

    public void update() {

        // Stop updating when Game Over
        if (player.getHealthPoints() == 0) {

            return;

        }

        // Update game state
        player.update();
        joystick.update();

        if (Enemy.readyToSpawn()) {
            // enemyList.add(new Enemy());
            enemyList.add(new Enemy(
                    getContext(),
                    player,
                    Math.random() * 1000,
                    Math.random() * 1000,
                    30,
                    spriteEnemy
            ));
        }

        while (numberOfSpellsToCast > 0) {
            spellList.add(new Spell(getContext(), player));
            numberOfSpellsToCast--;
        }
        // Update the state of each enemy
        for (Enemy enemy : enemyList) {
            enemy.update();
        }

        // Update the state of each spell
        for (Spell spell : spellList) {
            spell.update();
        }

        // Iterate through enemyList and check for collisions
        Iterator<Enemy> iteratorEnemy = enemyList.iterator();

        while (iteratorEnemy.hasNext()) {

            Circle enemy = iteratorEnemy.next();

            if (Circle.isColliding(enemy, player)) {
                
                // Remove Enemy if collides with Player
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints() - 30);
                continue;
            }


            Iterator<Spell> iteratorSpell = spellList.iterator();

            while (iteratorSpell.hasNext()) {
                Circle spell = iteratorSpell.next();

                // Remove spell if it collides with enemy
                if (Circle.isColliding(spell, enemy)) {
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    break;
                }
            }

        }

    }

}
