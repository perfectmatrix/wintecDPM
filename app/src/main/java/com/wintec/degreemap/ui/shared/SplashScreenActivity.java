package com.wintec.degreemap.ui.shared;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.LinearInterpolator;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.wintec.degreemap.R;

import static com.wintec.degreemap.util.Constants.KEY_TUTORIAL_DONE;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;


public class SplashScreenActivity extends AppCompatActivity {

    LazyLoader lazyLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

<<<<<<< Updated upstream
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        final boolean isTutorialDone = prefs.getBoolean(KEY_TUTORIAL_DONE, false);
=======
        // Method for DotLoader

        lazyLoader = (LazyLoader)findViewById(R.id.myLoaderProgress);
        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());

        lazyLoader.addView(loader);
>>>>>>> Stashed changes

        // Method to show splash screen for 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, isTutorialDone ? RoleSelectionActivity.class : TutorialPart1.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }

}