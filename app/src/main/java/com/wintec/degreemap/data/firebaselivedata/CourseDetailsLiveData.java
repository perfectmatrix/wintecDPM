package com.wintec.degreemap.data.firebaselivedata;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wintec.degreemap.data.model.Course;

import java.util.ArrayList;

public class CourseDetailsLiveData extends FirebaseBaseLiveData<Course>  {
    private final DatabaseReference reference;
    private final CourseValueEventListener listener = new CourseValueEventListener();

    public CourseDetailsLiveData(DatabaseReference ref) {
        this.reference = ref;
    }

    @Override
    void removeListener() { reference.removeEventListener(listener); }

    @Override
    void attachListener() { reference.addValueEventListener(listener); }

    private class CourseValueEventListener implements ValueEventListener {
        private static final String TAG = "CourseValueEventListener";

        @Override
        public void onDataChange(DataSnapshot snapshot) {
            ArrayList<String> preRequisite = new ArrayList<>();
            if (snapshot.child("preRequisite").getValue() != null) {
                for (DataSnapshot preReq : snapshot.child("preRequisite").getChildren()) {
                    preRequisite.add(preReq.getKey());
                }
            }

            Course course = new Course(snapshot.getKey(),
                    snapshot.child("code").getValue(String.class),
                    snapshot.child("credit").getValue(Integer.class),
                    snapshot.child("description").getValue(String.class),
                    snapshot.child("longName").getValue(String.class),
                    snapshot.child("level").getValue(Integer.class),
                    preRequisite,
                    snapshot.child("semester").getValue(Integer.class),
                    snapshot.child("type").getValue(String.class),
                    snapshot.child("url").getValue(String.class),
                    snapshot.child("year").getValue(Integer.class));

            setValue(course);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to reference" + reference, databaseError.toException());
        }
    }
}
