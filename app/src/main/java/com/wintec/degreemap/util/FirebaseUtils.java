package com.wintec.degreemap.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class FirebaseUtils {
    private static FirebaseDatabase _databaseReference;

    public static FirebaseDatabase getDatabaseInstance() {
        if (_databaseReference == null) {
            _databaseReference = FirebaseDatabase.getInstance();
            return _databaseReference;
        }
        return _databaseReference;
    }

    public static DatabaseReference getCourseRef() {
        return getDatabaseInstance().getReference("course");
    }

    public static DatabaseReference getUserRef() {
        return getDatabaseInstance().getReference("user");
    }
}
