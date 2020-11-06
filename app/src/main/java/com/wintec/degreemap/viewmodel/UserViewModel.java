package com.wintec.degreemap.viewmodel;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.google.firebase.storage.StorageReference;
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

    public void insertUser(String userKey, Uri profileImage, String profileImageExtension, User user) {
        if (profileImage == null) {
            userRepository.insertUser(userKey, user);
        } else {
            userRepository.insertUserWithProfile(userKey, profileImage, profileImageExtension, user);
        }
    }

    public void deleteUser(String userKey) {
        userRepository.deleteUser(userKey);
    }
}
