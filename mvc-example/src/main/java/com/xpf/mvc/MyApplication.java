package com.xpf.mvc;

import android.app.Application;

import com.anloq.http.ApiClient;

/**
 * Created by xpf on 2018/4/4 :)
 * Function:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiClient.getInstance().init(this);
    }
}
