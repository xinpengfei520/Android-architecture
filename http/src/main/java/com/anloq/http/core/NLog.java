package com.anloq.http.core;

import android.util.Log;

/**
 * Created by xpf on 2017/9/22 :)
 * Function:NLog util.
 */

public class NLog {

    private static final String TAG = NLog.class.getSimpleName();
    private static boolean mDebug = true; // default true.

    public NLog() {
    }

    public static void isDebug(boolean isDebug) {
        mDebug = isDebug;
    }

    public static void e(String message) {
        if (mDebug) {
            Log.e(TAG, message);
        }
    }


    public static void i(String message) {
        if (mDebug) {
            Log.i(TAG, message);
        }
    }

    public static void d(String message) {
        if (mDebug) {
            Log.d(TAG, message);
        }
    }

    public static void v(String message) {
        if (mDebug) {
            Log.v(TAG, message);
        }
    }

    public static void w(String message) {
        if (mDebug) {
            Log.w(TAG, message);
        }
    }

    public static void wtf(String message) {
        if (mDebug) {
            Log.wtf(TAG, message);
        }
    }
}
