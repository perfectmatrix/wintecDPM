package com.wintec.degreemap.data.firebaselivedata;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wintec.degreemap.data.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseListLiveData extends FirebaseBaseLiveData<List<Course>> {
    private final DatabaseReference reference;
    private final CourseValueEventListener listener = new CourseValueEventListener();
    private List<Course> mCourseList;

    public CourseListLiveData(DatabaseReference ref) {
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

    private class CourseValueEventListener implements ValueEventListener {
        private static final String TAG = "CourseValueEventListener";

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            mCourseList = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Course course = snapshot.getValue(Course.class);
                mCourseList.add(course);
            }
            setValue(mCourseList);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to reference" + reference, databaseError.toException());
        }
    }
}
