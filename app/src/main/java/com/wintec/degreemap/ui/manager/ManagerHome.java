package com.wintec.degreemap.ui.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wintec.degreemap.R;
import com.wintec.degreemap.ui.manager.manage_courses.ManagerCourseHomeFragment;
import com.wintec.degreemap.ui.manager.manage_students.ManageStudentsHome;
import com.wintec.degreemap.ui.shared.RoleSelectionActivity;

public class ManagerHome extends AppCompatActivity {

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
                i = new Intent(this, ManagerCourseHomeFragment.class);
                startActivity(i);
                break;
            case R.id.manage_students:
                i = new Intent(this, ManageStudentsHome.class);
                startActivity(i);
                break;
            case R.id.back_arrow:
                i = new Intent(this, RoleSelectionActivity.class);
                startActivity(i);
                break;
        }
    }


}