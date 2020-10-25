 package com.wintec.degreemap.ui.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wintec.degreemap.ui.login.LoginActivity;
import com.wintec.degreemap.R;
import com.wintec.degreemap.Student_Home;

 public class RoleSelectionActivity extends AppCompatActivity {

     Dialog disclaimer_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);
    }

    // Method for users to select role: [Student] or [Manager]
     public void jumpTo(View view) {
         Intent i = null;
         switch (view.getId()){
             case R.id.btn_student:
                 i = new Intent(this, Student_Home.class);
                 startActivity(i);
                 break;
             case R.id.btn_manager:
                 i = new Intent(this, LoginActivity.class);
                 startActivity(i);
                 break;

         }
     }

     // Method for disclaimer
     // But every time we go to role selection activity, this dialog will show
     // feel free to change it
     @Override
     protected void onStart() {
         super.onStart();
         disclaimer_dialog = new Dialog(this);
         disclaimer_dialog.setContentView(R.layout.dialog_disclaimer);
         disclaimer_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         Button button = disclaimer_dialog.findViewById(R.id.btn_get_started);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 disclaimer_dialog.dismiss();
             }
         });
         disclaimer_dialog.show();
     }

 }