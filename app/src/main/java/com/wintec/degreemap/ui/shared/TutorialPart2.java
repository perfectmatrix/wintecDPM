package com.wintec.degreemap.ui.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.wintec.degreemap.R;

public class TutorialPart2 extends AppCompatActivity {

    public static final String SHARED_PREFERENCES = "SharedPreferences";
    public static final String KEY_TUTORIAL_DONE = "KeyTutorialDone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_part2);
    }

    // Method to go back to previous tutorial or jump to Role Selection activity
    public void jumpTo(View view) {
        Intent i = null;
        switch (view.getId()){
            case R.id.back:
                i = new Intent(this, TutorialPart1.class);
                startActivity(i);
                break;
            case R.id.next2:
                i = new Intent(this, RoleSelectionActivity.class);
                startActivity(i);

                // Save tutorial status on SharedPreferences
                SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(KEY_TUTORIAL_DONE, true);
                editor.apply();

                break;
        }
    }
}