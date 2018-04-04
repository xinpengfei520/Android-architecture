package com.xpf.mvp.model;

import com.xpf.mvp.bean.User;

/**
 * Created by xpf on 2018/4/4 :)
 * GitHub:xinpengfei520
 * Function:登录结果的监听器(输出)
 */
public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
