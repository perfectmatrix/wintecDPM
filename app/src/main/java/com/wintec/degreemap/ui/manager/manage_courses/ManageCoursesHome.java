package com.wintec.degreemap.ui.manager.manage_courses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wintec.degreemap.R;

public class ManageCoursesHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_manage_courses);
    }

    // Method for managers to select a pathway when they manage courses:
    public void jumpTo(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.card_network:
                i = new Intent(this, ManageCourse_Network.class);
                startActivity(i);
                break;
            case R.id.card_database:
                i = new Intent(this, ManageCourse_Database.class);
                startActivity(i);
                break;
            case R.id.card_web:
                i = new Intent(this, ManageCourse_Web.class);
                startActivity(i);
                break;
            case R.id.card_software:
                i = new Intent(this, ManageCourse_Software.class);
                startActivity(i);
                break;
        }
    }
}