package com.wintec.degreemap.ui.student.student_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.wintec.degreemap.R;

public class StudentProfileFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_profile, container, false);

        view.findViewById(R.id.about_us).setOnClickListener(this);
        view.findViewById(R.id.developer_group).setOnClickListener(this);
        view.findViewById(R.id.student_contact_details).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        NavController navController = Navigation.findNavController(view);
        switch (view.getId()) {
            case R.id.about_us:
                navController.navigate(R.id.action_studentProfileFragment_to_aboutUsFragment);
                break;
            case R.id.developer_group:
                navController.navigate(R.id.action_studentProfileFragment_to_developersFragment);
                break;
            case R.id.student_contact_details:
                navController.navigate(R.id.action_studentProfileFragment_to_contactDetailFragment);
                break;
        }
    }
}
