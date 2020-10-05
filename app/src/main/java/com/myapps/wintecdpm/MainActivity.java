 package com.myapps.wintecdpm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

 public class MainActivity extends AppCompatActivity {

    Button btnStudent, btnManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define buttons
        btnStudent = findViewById(R.id.btn_student);
        btnManager = findViewById(R.id.btn_manager);

        // add click listener to each button
        btnStudent.setOnClickListener((View.OnClickListener) this);
        btnManager.setOnClickListener((View.OnClickListener) this);
    }


}