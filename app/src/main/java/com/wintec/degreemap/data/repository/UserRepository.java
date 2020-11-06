package com.wintec.degreemap.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.wintec.degreemap.data.firebaselivedata.CourseDetailsLiveData;
import com.wintec.degreemap.data.firebaselivedata.UserDetailsLiveData;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.util.FirebaseUtils;

public class UserRepository {
    private static UserRepository userRepository;

    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public UserDetailsLiveData getUserDetails(String userKey) {
        return new UserDetailsLiveData(FirebaseUtils.getUserRef().child(userKey));
    }

    public void insertUser(String userKey, User user) {
        DatabaseReference userRef = FirebaseUtils.getUserRef();
        userRef.child(userKey).setValue(user);
    }

    public void deleteUser(String userKey) {
        DatabaseReference userRef = FirebaseUtils.getUserRef();
        userRef.child(userKey).removeValue();
    }
}