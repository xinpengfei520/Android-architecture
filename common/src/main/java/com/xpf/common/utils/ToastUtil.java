package com.xpf.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xpf.common.R;

/**
 * Created by xpf on 2017/9/4 :)
 * Function:Toast 显示工具类
 */
public class ToastUtil {

    /**
     * show short toast.
     *
     * @param context
     * @param msg
     */
    public static void showShortToast(Context context, String msg) {
        if (context == null) return;
        if (!TextUtils.isEmpty(msg)) {
            Context appContext = context.getApplicationContext();
            Toast.makeText(appContext, msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * show long toast.
     *
     * @param context
     * @param msg
     */
    public static void showLongToast(Context context, String msg) {
        if (context == null) return;
        if (!TextUtils.isEmpty(msg)) {
            Context appContext = context.getApplicationContext();
            Toast.makeText(appContext, msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * show toast on screen center.
     *
     * @param context
     * @param msg
     */
    public static void showCenterToast(Context context, CharSequence msg) {
        showToast(context, msg, Toast.LENGTH_SHORT, Gravity.CENTER);
    }

    /**
     * show toast by message id on screen center.
     *
     * @param context
     * @param msgId
     */
    public static void showCenterToast(Context context, int msgId) {
        showToast(context, msgId, Toast.LENGTH_SHORT, Gravity.CENTER);
    }

    /**
     * show toast on screen top.
     *
     * @param context
     * @param msg
     */
    public static void showTopToast(Context context, CharSequence msg) {
        showToast(context, msg, Toast.LENGTH_SHORT, Gravity.TOP);
    }

    /**
     * show toast by message id on screen top.
     *
     * @param context
     * @param msgId
     */
    public static void showTopToast(Context context, int msgId) {
        showToast(context, msgId, Toast.LENGTH_SHORT, Gravity.TOP);
    }

    /**
     * show toast on screen bottom.
     *
     * @param context
     * @param msg
     */
    public static void showBottomToast(Context context, CharSequence msg) {
        showToast(context, msg, Toast.LENGTH_SHORT, Gravity.BOTTOM);
    }

    /**
     * show toast by message id on screen bottom.
     *
     * @param context
     * @param msgId
     */
    public static void showBottomToast(Context context, int msgId) {
        showToast(context, msgId, Toast.LENGTH_SHORT, Gravity.BOTTOM);
    }

    public static void showToast(Context context, int msgId, int duration, int gravity) {
        if (context == null) return;
        Context appContext = context.getApplicationContext();
        CharSequence msg = context.getResources().getString(msgId);
        showToast(appContext, msg, duration, gravity);
    }

    public static void showToast(Context context, CharSequence msg, int duration, int gravity) {
        if (!TextUtils.isEmpty(msg)) {
            int yOffset = 0;
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            if ((gravity & Gravity.TOP) != 0) {
                yOffset = DensityUtil.dp2px(context, 15);
            }

            if ((gravity & Gravity.BOTTOM) != 0) {
                toast.setGravity(gravity, 0, yOffset);
            }
            toast.show();
        }
    }

    /**
     * show custom toast.
     *
     * @param context
     * @param msgId
     * @param duration
     */
    public static void showCustomToast(Context context, int msgId, int duration) {
        CharSequence msg = context.getResources().getString(msgId);
        showCustomToast(context, msg, duration);
    }

    @SuppressLint("InflateParams")
    public static void showCustomToast(Context context, CharSequence msg, int duration) {
        if (msg != null && msg.length() > 0) {
            if (duration != Toast.LENGTH_SHORT && duration != Toast.LENGTH_LONG) {
                duration = Toast.LENGTH_SHORT;
            }
            Toast toast = new Toast(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.toast_layout, null);
            TextView content = (TextView) view.findViewById(R.id.textView);
            content.setText(msg.toString());
            toast.setView(view);
            toast.setDuration(duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
