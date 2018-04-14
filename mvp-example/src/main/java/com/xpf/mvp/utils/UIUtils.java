package com.xpf.mvp.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.xpf.mvp.MVPApplication;

/**
 * Created by xpf on 2016/11/12 :)
 * Wechat:18091383534
 * Function:获取ui操作的相关工具类
 */
public class UIUtils {

    /**
     * 获取程序需要的上下文对象:返回的是MyApplication的对象
     *
     * @return
     */
    public static Context getContext() {
        return MVPApplication.getAppContext();
    }

    /**
     * 获取程序需要的消息处理器的对象
     *
     * @return
     */
    public static Handler getHandler() {
        return MVPApplication.mHandler;
    }

    /**
     * 返回指定id对应的颜色
     *
     * @param colorId
     * @return
     */
    public static int getColor(int colorId) {
        return getContext().getResources().getColor(colorId);
    }

    /**
     * 返回指定布局id,所对应的视图对象
     *
     * @param layoutId
     * @return
     */
    public static View getView(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    /**
     * dp--->px
     *
     * @param dp
     * @return
     */
    public static int dp2px(int dp) {
        // 获取手机的密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5); // 实现四舍五入操作
    }

    /**
     * px--->dp
     *
     * @param px
     * @return
     */
    public static int px2dp(int px) {
        // 获取手机的密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5); // 实现四舍五入操作
    }

    /**
     * 返回指定id的字符串数组
     *
     * @param strArrayId
     * @return
     */
    public static String[] getStrArray(int strArrayId) {
        String[] stringArray = getContext().getResources().getStringArray(strArrayId);
        return stringArray;
    }

    /**
     * 保证如下的操作在主线程中执行的
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            UIUtils.getHandler().post(runnable);
        }
    }

    /**
     * 判断当前的线程是否是主线程
     *
     * @return
     */
    private static boolean isMainThread() {
        int currentThreadId = android.os.Process.myTid();
        return MVPApplication.mainThreadId == currentThreadId;
    }
}
