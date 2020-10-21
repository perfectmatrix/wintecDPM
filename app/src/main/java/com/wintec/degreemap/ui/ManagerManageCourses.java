package com.wintec.degreemap.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wintec.degreemap.ManagerManageCourseDatabase;
import com.wintec.degreemap.ManagerManageCourseNetwork;
import com.wintec.degreemap.ManagerManageCourseSoftware;
import com.wintec.degreemap.ManagerManageCourseWeb;
import com.wintec.degreemap.R;

public class ManagerManageCourses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_manage_courses);
    }

    public void jumpTo(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.card_network:
                i = new Intent(this, ManagerManageCourseNetwork.class);
                startActivity(i);
                break;
            case R.id.card_database:
                i = new Intent(this, ManagerManageCourseDatabase.class);
                startActivity(i);
                break;
            case R.id.card_web:
                i = new Intent(this, ManagerManageCourseWeb.class);
                startActivity(i);
                break;
            case R.id.card_software:
                i = new Intent(this, ManagerManageCourseSoftware.class);
                startActivity(i);
                break;
        }

    }
}