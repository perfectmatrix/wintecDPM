package com.wintec.degreemap.viewmodel;

import androidx.lifecycle.ViewModel;

import com.wintec.degreemap.data.firebaselivedata.UserDetailsLiveData;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.data.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;

    public UserViewModel() {
        this.userRepository = UserRepository.getInstance();
    }

    public UserDetailsLiveData getUserDetails(String userKey) {
        return userRepository.getUserDetails(userKey);
    }

    public void insertUser(String userKey, User user) {
        userRepository.insertUser(userKey, user);
    }

    public void deleteUser(String userKey) {
        userRepository.deleteUser(userKey);
    }
}
