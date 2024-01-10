package com.example.androidgame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("MainActivity.java", "onCreate()");

        super.onCreate(savedInstanceState);

        // Set window to fullscreen

        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        //Initialize Game
        game = new Game (this);

        // Set content view to game, so that objects in the Game class can be rendered to the screen
        setContentView(game);

    }

    @Override
    protected void onStart() {

        Log.d("MainActivity.java", "onStart()");

        super.onStart();
    }

    @Override
    protected void onResume() {

        Log.d("MainActivity.java", "onResume()");

        super.onResume();
    }

    @Override
    protected void onPause() {

        Log.d("MainActivity.java", "onPause()");

        game.pause();

        super.onPause();
    }

    @Override
    protected void onStop() {

        Log.d("MainActivity.java", "onStop()");

        super.onStop();
    }

    @Override
    protected void onDestroy() {

        Log.d("MainActivity.java", "onDestroy()");

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        Log.d("MainActivity.java", "onBackPressed()");

        // Comment out super to prevent any back press action
        // super.onBackPressed();
    }

}