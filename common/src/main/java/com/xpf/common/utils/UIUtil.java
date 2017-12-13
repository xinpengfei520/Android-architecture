package com.xpf.common.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;


/**
 * Created by xpf on 2017/05/16 :)
 * Function:获取ui操作的相关工具类
 */

public class UIUtil {

    /**
     * 返回指定id对应的颜色
     *
     * @param context
     * @param colorId
     * @return
     */
    public static int getColor(Context context, int colorId) {
        if (context == null) return -1;
        Context appContext = context.getApplicationContext();
        return appContext.getResources().getColor(colorId);
    }

    /**
     * 返回指定布局id,所对应的视图对象
     *
     * @param context
     * @param layoutId
     * @return
     */
    public static View getView(Context context, int layoutId) {
        if (context == null) return null;
        Context appContext = context.getApplicationContext();
        return View.inflate(appContext, layoutId, null);
    }

    /**
     * 返回指定id的字符串数组
     *
     * @param strArrayId
     * @return
     */
    public static String[] getStrArray(Context context, int strArrayId) {
        if (context == null) return null;
        Context appContext = context.getApplicationContext();
        return appContext.getResources().getStringArray(strArrayId);
    }

    /**
     * 保证如下的操作在主线程中执行的
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    /**
     * 判断当前的线程是否是主线程
     *
     * @return
     */
    private static int getCurrentThreadId() {
        return android.os.Process.myTid();
    }
}
