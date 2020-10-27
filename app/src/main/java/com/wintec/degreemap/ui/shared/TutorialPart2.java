package com.wintec.degreemap.ui.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wintec.degreemap.R;

public class TutorialPart2 extends AppCompatActivity {

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
                break;
        }
    }
}