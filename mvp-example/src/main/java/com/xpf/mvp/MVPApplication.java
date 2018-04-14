package com.xpf.mvp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by xpf on 2018/4/13 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class MVPApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Log.i("TAG", "MVPApplication onCreate()");
    }

    public static Context getAppContext() {
        return mContext;
    }
}
