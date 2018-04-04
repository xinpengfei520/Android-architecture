package com.xpf.mvp.view;

import com.xpf.mvp.bean.User;

/**
 * Created by xpf on 2018/4/5 :)
 * GitHub:xinpengfei520
 * Function:登录界面相关视图操作的接口
 */
public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
