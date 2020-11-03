 package com.wintec.degreemap.ui.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;
import com.wintec.degreemap.ui.manager.ManagerLogin;
import com.wintec.degreemap.R;
import com.wintec.degreemap.ui.student.StudentHome;

 public class RoleSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    // Method for users to select role: [Student] or [Manager]
     public void jumpTo(View view) {
         Intent i = null;
         switch (view.getId()){
             case R.id.btn_student:
                 i = new Intent(this, StudentHome.class);
                 startActivity(i);
                 break;
             case R.id.btn_manager:
                 i = new Intent(this, ManagerLogin.class);
                 startActivity(i);
                 break;
         }
     }
 }