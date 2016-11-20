package com.example.ppp.developertodo.implementation.threading;

/**
 * Created by ppp on 2016-11-10.
 */

import android.os.Handler;
import android.os.Looper;
import com.example.ppp.developertodo.logic.executors.IMainThread;


 // This class makes sure that the runnable we provide will be run on the main UI thread.

public class MainThread implements IMainThread {

    private static IMainThread sMainThread;

    private Handler mHandler;

    private MainThread() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static IMainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThread();
        }

        return sMainThread;
    }
}
