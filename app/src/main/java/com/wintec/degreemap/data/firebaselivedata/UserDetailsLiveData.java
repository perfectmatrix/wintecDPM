package com.wintec.degreemap.data.firebaselivedata;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wintec.degreemap.data.model.User;

public class UserDetailsLiveData extends FirebaseBaseLiveData<User> {
    private final DatabaseReference reference;
    private final UserValueEventListener listener = new UserValueEventListener();

    public UserDetailsLiveData(DatabaseReference ref) {
        this.reference = ref;
    }

    @Override
    void removeListener() {
        reference.removeEventListener(listener);
    }

    @Override
    void attachListener() {
        reference.addValueEventListener(listener);
    }

    private class UserValueEventListener implements ValueEventListener {
        private static final String TAG = "UserValueEventListener";

        @Override
        public void onDataChange(DataSnapshot snapshot) {
            User user = snapshot.getValue(User.class);

            if (user != null) {
                user.setKey(snapshot.getKey());
            }

            setValue(user);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to reference" + reference, databaseError.toException());
        }
    }
}
