package com.wintec.degreemap.data.firebaselivedata;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.wintec.degreemap.data.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseListLiveData extends FirebaseBaseLiveData<List<Course>> {
    private final Query query;
    private final CourseValueEventListener listener = new CourseValueEventListener();
    private List<Course> mCourseList;

    public CourseListLiveData(Query q) {
        this.query = q;
    }

    @Override
    void removeListener() {
        query.removeEventListener(listener);
    }

    @Override
    void attachListener() {
        query.addValueEventListener(listener);
    }

    private class CourseValueEventListener implements ValueEventListener {
        private static final String TAG = "CourseValueEventListener";

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            mCourseList = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                ArrayList<String> coRequisite = new ArrayList<>();
                if (snapshot.child("coRequisite").getValue() != null) {
                    for (DataSnapshot preReq : snapshot.child("coRequisite").getChildren()) {
                        coRequisite.add(preReq.getKey());
                    }
                }

                ArrayList<String> preRequisite = new ArrayList<>();
                if (snapshot.child("preRequisite").getValue() != null) {
                    for (DataSnapshot preReq : snapshot.child("preRequisite").getChildren()) {
                        preRequisite.add(preReq.getKey());
                    }
                }

                ArrayList<String> pathway = new ArrayList<>();
                if (snapshot.child("pathway").getValue() != null) {
                    for (DataSnapshot path : snapshot.child("pathway").getChildren()) {
                        pathway.add(path.getKey());
                    }
                }

                Course course = new Course(snapshot.getKey(),
                        coRequisite,
                        snapshot.child("credit").getValue(Integer.class),
                        snapshot.child("description").getValue(String.class),
                        snapshot.child("longName").getValue(String.class),
                        snapshot.child("level").getValue(Integer.class),
                        preRequisite,
                        snapshot.child("semester").getValue(Integer.class),
                        pathway,
                        snapshot.child("url").getValue(String.class),
                        snapshot.child("year").getValue(Integer.class));

                mCourseList.add(course);
            }
            setValue(mCourseList);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query" + query, databaseError.toException());
        }
    }
}
