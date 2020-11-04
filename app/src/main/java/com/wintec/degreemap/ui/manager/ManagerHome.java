package com.wintec.degreemap.ui.manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wintec.degreemap.R;
import com.wintec.degreemap.ui.shared.RoleSelectionActivity;

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
                Intent i = new Intent(this, RoleSelectionActivity.class);
                startActivity(i);
                break;
        }
    }
}