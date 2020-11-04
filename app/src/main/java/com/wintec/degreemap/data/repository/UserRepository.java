package com.wintec.degreemap.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.util.FirebaseUtils;

public class UserRepository {
    private static UserRepository sUserRepository;

    public static UserRepository getInstance() {
        if (sUserRepository == null) {
            sUserRepository = new UserRepository();
        }
        return sUserRepository;
    }

    public void insertUser(String userKey, User user) {
        DatabaseReference userRef = FirebaseUtils.getUserRef();
        userRef.child(userKey).setValue(user);
    }
}