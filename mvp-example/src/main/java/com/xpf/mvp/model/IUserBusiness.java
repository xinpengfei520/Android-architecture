package com.xpf.mvp.model;

/**
 * Created by xpf on 2018/4/4 :)
 * GitHub:xinpengfei520
 * Function:业务登录的接口（输入）
 */
public interface IUserBusiness {
    void login(String userName, String password, OnLoginListener listener);
}
