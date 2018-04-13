package com.xpf.mvp;

import android.app.Application;
import android.util.Log;

/**
 * Created by xpf on 2018/4/13 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class MVPApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TAG", "MVPApplication onCreate()");
    }
}
