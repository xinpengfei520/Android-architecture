package com.xpf.mvvm.net.interceptor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.xpf.mvvm.utils.JsonUtils;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by x-sir on 2018/8/3 :)
 * Function:LoggerInterceptor
 */
public class LoggerInterceptor implements HttpLoggingInterceptor.Logger {

    private static final String TAG = "LoggerInterceptor";

    @Override
    public void log(@NonNull String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            Log.d(TAG, "---------- START ----------\n");
        }
        // 以 {} 或者 [] 形式的说明是响应结果的 json 数据，需要进行格式化
        boolean flag1 = message.startsWith("{") && message.endsWith("}");
        boolean flag2 = message.startsWith("[") && message.endsWith("]");
        if (flag1 || flag2) {
            message = JsonUtils.formatJson(message);
        }
        // 请求或者响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            Log.d(TAG, message);
            Log.d(TAG, "\n----------- END -----------");
        }
    }
}
