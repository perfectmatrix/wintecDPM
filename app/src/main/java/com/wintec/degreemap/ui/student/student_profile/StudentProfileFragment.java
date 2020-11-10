package com.wintec.degreemap.ui.student.student_profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.viewmodel.UserViewModel;

import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.KEY_USER_KEY;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;

public class StudentProfileFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_student_profile, container, false);

        view.findViewById(R.id.about_us).setOnClickListener(this);
        view.findViewById(R.id.developer_group).setOnClickListener(this);
        view.findViewById(R.id.student_contact_details).setOnClickListener(this);

        // load profile image from storage
        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        String userKey = prefs.getString(KEY_USER_KEY, "");

        if (!userKey.isEmpty()) {
            final UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            userViewModel.getUserDetails(userKey).observe(getViewLifecycleOwner(), new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (user != null && !user.getProfileUrl().isEmpty()) {
                        Glide.with(view)
                                .load(user.getProfileUrl())
                                .into((ImageView) view.findViewById(R.id.profileImageView));
                    }
                }
            });
        }

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
