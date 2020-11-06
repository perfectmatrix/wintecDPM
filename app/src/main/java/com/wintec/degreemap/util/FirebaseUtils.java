package com.wintec.degreemap.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public final class FirebaseUtils {
    private static FirebaseDatabase _databaseReference;
    private static FirebaseStorage _storageReference;

    public static FirebaseDatabase getDatabaseInstance() {
        if (_databaseReference == null) {
            _databaseReference = FirebaseDatabase.getInstance();
            return _databaseReference;
        }
        return _databaseReference;
    }

    public static FirebaseStorage getStorageInstance() {
        if (_storageReference == null) {
            _storageReference = FirebaseStorage.getInstance();
        }
        return _storageReference;
    }

    public static DatabaseReference getCourseRef() {
        return getDatabaseInstance().getReference("course");
    }

    public static DatabaseReference getUserRef() {
        return getDatabaseInstance().getReference("user");
    }

    public static StorageReference getProfileImageRef() {
        return getStorageInstance().getReference("profileImage");
    }
}
