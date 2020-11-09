package com.wintec.degreemap.data.firebaselivedata;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wintec.degreemap.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserListLiveData extends FirebaseBaseLiveData<List<User>> {
    private final DatabaseReference databaseReference;
    private final UserListLiveData.UserValueEventListener listener = new UserListLiveData.UserValueEventListener();
    private List<User> userList;

    public UserListLiveData(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    void removeListener() {
        databaseReference.removeEventListener(listener);
    }

    @Override
    void attachListener() {
        databaseReference.addValueEventListener(listener);
    }

    private class UserValueEventListener implements ValueEventListener {
        private static final String TAG = "UserValueEventListener";

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            userList = new ArrayList<>();

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                User user = snapshot.getValue(User.class);
                user.setKey(snapshot.getKey());
                userList.add(user);
            }
            
            setValue(userList);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Log.e(TAG, "Can't listen to reference" + databaseReference, error.toException());
        }
    }
}
