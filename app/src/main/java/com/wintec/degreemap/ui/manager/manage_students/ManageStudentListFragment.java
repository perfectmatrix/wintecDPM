package com.wintec.degreemap.ui.manager.manage_students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.viewmodel.UserViewModel;

import java.util.List;

import static com.wintec.degreemap.util.Constants.BUNDLE_USER_KEY;

public class ManageStudentListFragment extends Fragment implements StudentAdapter.OnItemClickListener, View.OnClickListener {
    private List<User> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_student_list, container, false);

        view.findViewById(R.id.addStudentButton).setOnClickListener(this);
        final StudentAdapter studentAdapter = new StudentAdapter();
        studentAdapter.setOnItemClickListener(this);
        LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_all_students);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(studentAdapter);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUserList().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null) {
                    userList = users;
                    studentAdapter.setUsers(users);
                }
            }
        });

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_USER_KEY, userList.get(position).getKey());

        // navigate to course details fragment
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_manageStudentListFragment_to_manageStudentDetailsFragment, bundle);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addStudentButton:
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_USER_KEY, "");

                // navigate to course details fragment
                NavController navController = NavHostFragment.findNavController(this);
                navController.navigate(R.id.action_manageStudentListFragment_to_manageStudentFormFragment, bundle);

                break;
        }
    }
}