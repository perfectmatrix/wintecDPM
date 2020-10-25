package com.wintec.degreemap.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wintec.degreemap.R;
import com.wintec.degreemap.ui.ManagerManageCourses;
import com.wintec.degreemap.ui.ManagerManageStudents;
import com.wintec.degreemap.ui.login.LoginActivity;

public class Manager_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);
    }

    // Method for manager to select their action: [Manage Students] or [Manage Courses]
    public void jumpTo(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.manage_courses:
                i = new Intent(this, ManagerManageCourses.class);
                startActivity(i);
                break;
            case R.id.manage_students:
                i = new Intent(this, ManagerManageStudents.class);
                startActivity(i);
                break;
        }
    }
}