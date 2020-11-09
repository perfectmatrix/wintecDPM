package com.wintec.degreemap.data.repository;

import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.wintec.degreemap.data.firebaselivedata.UserDetailsLiveData;
import com.wintec.degreemap.data.firebaselivedata.UserListLiveData;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.util.FirebaseUtils;

public class UserRepository {
    private static UserRepository userRepository;
    private UserListLiveData userList;

    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public UserListLiveData getUserList() {
        if (userList == null) {
            userList = new UserListLiveData(FirebaseUtils.getUserRef());
            return userList;
        }
        return userList;
    }

    public UserDetailsLiveData getUserDetails(String userKey) {
        return new UserDetailsLiveData(FirebaseUtils.getUserRef().child(userKey));
    }

    public void insertUser(String userKey, User user) {
        DatabaseReference userRef = FirebaseUtils.getUserRef();
        userRef.child(userKey).setValue(user);
    }

    public void insertUserWithProfile(final String userKey, Uri profileImage, String profileImageExtension, final User user) {
        StorageReference profileImageRef = FirebaseUtils.getProfileImageRef();
        StorageReference fileRef = profileImageRef.child(userKey + '.' + profileImageExtension);

        fileRef.putFile(profileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> profileImageUrlTask = taskSnapshot.getStorage().getDownloadUrl();

                while (!profileImageUrlTask.isSuccessful()) ;

                String profileImageUrl = profileImageUrlTask.getResult().toString();
                user.setProfileUrl(profileImageUrl);

                insertUser(userKey, user);
            }
        });
    }

    public void deleteUser(String userKey) {
        DatabaseReference userRef = FirebaseUtils.getUserRef();
        userRef.child(userKey).removeValue();
    }
}