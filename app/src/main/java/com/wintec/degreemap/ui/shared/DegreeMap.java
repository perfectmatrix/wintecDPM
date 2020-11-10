package com.wintec.degreemap.ui.shared;

import com.google.firebase.database.FirebaseDatabase;

public class DegreeMap extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
