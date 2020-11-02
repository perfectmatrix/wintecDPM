package com.wintec.degreemap.ui.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.wintec.degreemap.R;

import static com.wintec.degreemap.util.Constants.KEY_TUTORIAL_DONE;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        final boolean isTutorialDone = prefs.getBoolean(KEY_TUTORIAL_DONE, false);

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