package com.wintec.degreemap.viewmodel;

import androidx.lifecycle.ViewModel;

import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.data.repository.CourseRepository;
import com.wintec.degreemap.data.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private UserRepository mUserRepository;

    public UserViewModel() {
        this.mUserRepository = UserRepository.getInstance();
    }

    public void insertUser(String userKey, User user) {
        mUserRepository.insertUser(userKey, user);
    }
}
