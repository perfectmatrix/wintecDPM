package com.wintec.degreemap.ui.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wintec.degreemap.R;

import static com.wintec.degreemap.util.Constants.KEY_DISCLAIMER_ACCEPTED;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;

public class TutorialPart1 extends AppCompatActivity {
    Dialog disclaimer_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_part1);
    }

    // Method to skip tutorial or jump to Role Selection activity
    public void jumpTo(View view) {
        Intent i = null;
        switch (view.getId()){
            case R.id.skip:
                i = new Intent(this, RoleSelectionActivity.class);
                startActivity(i);
                break;
            case R.id.next1:
                i = new Intent(this, TutorialPart2.class);
                startActivity(i);
                break;
        }
    }

    // Disclaimer dialog will show only once and will be hidden after the user acknowledges
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        boolean isDisclaimerAccepted = prefs.getBoolean(KEY_DISCLAIMER_ACCEPTED, false);

        if(!isDisclaimerAccepted) {
            disclaimer_dialog = new Dialog(this);
            disclaimer_dialog.setContentView(R.layout.dialog_disclaimer);
            disclaimer_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Button button = disclaimer_dialog.findViewById(R.id.btn_get_started);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Save disclaimer status on SharedPreferences
                    SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean(KEY_DISCLAIMER_ACCEPTED, true);
                    editor.apply();

                    disclaimer_dialog.dismiss();
                }
            });

            disclaimer_dialog.show();
        }
    }
}