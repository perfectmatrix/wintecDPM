package com.wintec.degreemap.ui.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.wintec.degreemap.R;

public class ManagerHome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);

    }

    public void navigateTo(View view) {
        NavController navController = Navigation.findNavController(view);
        switch (view.getId()) {
            case R.id.manage_courses:
                navController.navigate(R.id.action_managerHomeOptions_to_manageCourseHomeFragment);
                break;
            case R.id.manage_students:
                break;
            case R.id.back_arrow:
                navController.navigate(R.id.action_managerHomeOptions_to_roleSelectionActivity);
                break;
        }

    }
}