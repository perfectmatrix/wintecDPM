 package com.wintec.degreemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     public void jumpTo(View view) {
         Intent i = null;
         switch (view.getId()){
             case R.id.btn_student:
                 i = new Intent(this, Student_Home.class);
                 startActivity(i);
                 break;
             case R.id.btn_manager:
                 i = new Intent(this, Manager_Password.class);
                 startActivity(i);
                 break;

         }
     }
}