package com.wintec.degreemap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wintec.degreemap.ui.AboutUs;
import com.wintec.degreemap.ui.Developers;
import com.wintec.degreemap.ui.ManagerManageCourses;
import com.wintec.degreemap.ui.ManagerManageStudents;

import static androidx.navigation.Navigation.findNavController;

public class Student_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // setup bottom nav to use nav_graph
        NavController navController = findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNav, navController);

    }

    // Method to select actions in profile page: [Contact Details], [About us] or [Developers]
    public void jumpTo(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.about_us:
                i = new Intent(this, AboutUs.class);
                startActivity(i);
                break;
            case R.id.developer_group:
                i = new Intent(this, Developers.class);
                startActivity(i);
                break;
        }
    }
}