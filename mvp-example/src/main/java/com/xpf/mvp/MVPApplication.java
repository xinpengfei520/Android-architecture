package com.xpf.mvp;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

/**
 * Created by xpf on 2018/4/13 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class MVPApplication extends Application {

    private static Context mContext;
    public static Handler mHandler;
    public static Thread mainThread; // 获取主线程
    public static int mainThreadId;  // 获取主线程的id
    private static final String TAG = MVPApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler();
        mainThread = Thread.currentThread(); // 当前用于初始化Application的线程，即为主线程
        mainThreadId = android.os.Process.myTid(); // 获取当前主线程的id
        Log.i(TAG, "MVPApplication onCreate()");
    }

    public static Context getAppContext() {
        return mContext;
    }
}
