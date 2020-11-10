package com.wintec.degreemap.ui.manager.manage_students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.databinding.FragmentManageStudentDetailsBinding;
import com.wintec.degreemap.viewmodel.UserViewModel;

import static com.wintec.degreemap.util.Constants.BUNDLE_USER_KEY;

public class ManageStudentDetailsFragment extends Fragment implements View.OnClickListener {
    private FragmentManageStudentDetailsBinding binding;
    private ImageView profileImage;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manage_student_details, container, false);
        view = binding.getRoot();

        view.findViewById(R.id.btn_student_edit).setOnClickListener(this);
        view.findViewById(R.id.btn_student_delete).setOnClickListener(this);

        profileImage = view.findViewById(R.id.details_avatar);

        loadData();
        return view;
    }


    public void loadData() {
        String userKey = getArguments().getString(BUNDLE_USER_KEY);

        if (userKey.isEmpty()) {
            setEmptyUser();
        } else {
            final UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            userViewModel.getUserDetails(userKey).observe(getViewLifecycleOwner(), new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (user != null) {
                        if (!user.getProfileUrl().isEmpty()) {
                            Glide.with(view).load(user.getProfileUrl()).into(profileImage);
                        }

                        binding.setUser(user);
                    } else {
                        setEmptyUser();
                    }
                }
            });
        }
    }

    private void setEmptyUser() {
        User user = new User("", "", "", "", "", "", "", "", "");
        binding.setUser(user);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_student_edit:
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_USER_KEY, binding.getUser().getKey());

                NavHostFragment.findNavController(this).navigate(R.id.action_manageStudentDetailsFragment_to_manageStudentFormFragment, bundle);
                break;
            case R.id.btn_student_delete:
                deleteData(view);
                break;
        }
    }

    private void deleteData(View view) {
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.deleteUser(binding.getUser().getKey());

        NavHostFragment.findNavController(this).navigate(R.id.action_manageStudentDetailsFragment_to_manageStudentListFragment);
    }
}