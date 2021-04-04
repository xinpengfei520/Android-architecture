package com.xpf.mvvm.net.exception;

import java.io.IOException;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public class ApiException extends IOException {

    public final int code;
    public final String msg;

    public ApiException(int code) {
        this.code = code;
        this.msg = null;
    }

    public ApiException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
