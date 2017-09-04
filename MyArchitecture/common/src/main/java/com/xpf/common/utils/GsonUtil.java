package com.xpf.common.utils;

import com.google.gson.Gson;

/**
 * Created by xpf on 2017/9/4 :)
 * Function:Gson单例操作
 */
public class GsonUtil {

    private static Gson gson;

    public static Gson gson() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }
}
