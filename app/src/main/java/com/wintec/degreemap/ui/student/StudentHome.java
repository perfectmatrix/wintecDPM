package com.wintec.degreemap.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wintec.degreemap.R;
import com.wintec.degreemap.ui.shared.RoleSelectionActivity;
import com.wintec.degreemap.ui.student.student_profile.StudentProfile_AboutUs;
import com.wintec.degreemap.ui.student.student_profile.StudentProfile_ContactDetail;
import com.wintec.degreemap.ui.student.student_profile.StudentProfile_Developers;

import static androidx.navigation.Navigation.findNavController;

public class StudentHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // setup bottom nav to use student_nav_graph
        NavController navController = findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNav, navController);

    }

    // Method to select actions in profile page: [Contact Details], [About us] or [Developers]
    public void jumpTo(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.about_us:
                i = new Intent(this, StudentProfile_AboutUs.class);
                startActivity(i);
                break;
            case R.id.developer_group:
                i = new Intent(this, StudentProfile_Developers.class);
                startActivity(i);
                break;
            case R.id.student_contact_details:
                i = new Intent(this, StudentProfile_ContactDetail.class);
                startActivity(i);
                break;
            case R.id.back_arrow:
                i = new Intent(this, RoleSelectionActivity.class);
                startActivity(i);
                break;
        }
    }
}