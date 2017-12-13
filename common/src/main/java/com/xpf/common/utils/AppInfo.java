package com.xpf.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by xpf on 2017/5/20 :)
 * Function:获取当前APP信息
 */

public class AppInfo {

    /**
     * get version name
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        PackageInfo packInfo = null;
        try {
            if (context != null) {
                Context appContext = context.getApplicationContext();
                PackageManager packageManager = appContext.getPackageManager();
                // 是你当前类的包名，0代表是获取版本信息
                packInfo = packageManager.getPackageInfo(appContext.getPackageName(), 0);
                versionName = packInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * get version code
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = -1;
        PackageInfo packInfo = null;
        try {
            if (context != null) {
                Context appContext = context.getApplicationContext();
                PackageManager packageManager = appContext.getPackageManager();
                // 是你当前类的包名，0代表是获取版本信息
                packInfo = packageManager.getPackageInfo(appContext.getPackageName(), 0);
                versionCode = packInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

}
