package com.xpf.http.core;

/**
 * Created by xpf on 2017/9/22 :)
 * Function:
 */

public interface ApiRequestListener {
    void onSuccess(String result);

    void onError(String ex);
}
