package com.xpf.mvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by xpf on 2018/4/13 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class NetworkHelper {

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            Context appContext = context.getApplicationContext();
            ConnectivityManager manager =
                    (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo networkinfo = manager.getActiveNetworkInfo();
                return networkinfo != null && networkinfo.isAvailable();
            }
        }
        return false;
    }
}
