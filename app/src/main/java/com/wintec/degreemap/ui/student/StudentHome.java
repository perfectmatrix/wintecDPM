package com.wintec.degreemap.ui.student;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wintec.degreemap.R;

import static androidx.navigation.Navigation.findNavController;

public class StudentHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // setup bottom nav to use student_nav_graph
        NavigationUI.setupWithNavController(bottomNav, findNavController(this, R.id.nav_host_fragment));
    }

    // Method to select actions in profile page: [Contact Details], [About us] or [Developers]
    public void navigateTo(View view) {
        switch (view.getId()) {
            case R.id.btn_details_cancel:
                Navigation.findNavController(view).navigate(R.id.action_contactDetailFragment_to_studentProfileFragment);
                break;
            case R.id.back_arrow:
                Navigation.findNavController(view).navigate(R.id.action_studentDashboardFragment_to_roleSelectionActivity);
                break;
        }
    }
}