package com.wintec.degreemap.data.firebaselivedata;

import android.os.Handler;

import androidx.lifecycle.LiveData;

public abstract class FirebaseBaseLiveData<T> extends LiveData<T> {
    private final Handler handler = new Handler();
    private boolean hasPendingListenerRemoval;

    private final Runnable removeListener = new Runnable() {
        @Override
        public void run() {
            removeListener();
            hasPendingListenerRemoval = false;
        }
    };

    @Override
    protected void onActive() {
        if (hasPendingListenerRemoval) {
            handler.removeCallbacks(removeListener);
        } else {
            attachListener();
        }
        hasPendingListenerRemoval = false;
    }

    @Override
    protected void onInactive() {
        handler.postDelayed(removeListener, 2000);
        hasPendingListenerRemoval = true;
    }

    /* Should override these abstract methods on derived class */

    abstract void removeListener();

    abstract void attachListener();
}
