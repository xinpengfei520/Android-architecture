package com.xpf.mvvm;

import android.app.Application;
import android.content.Context;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //Fresco.initialize(this);
    }

    public static Context getAppContext() {
        return context;
    }
}
