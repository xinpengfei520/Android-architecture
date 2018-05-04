package com.xpf.mvp.model;

import com.xpf.mvp.bean.User;
import com.xpf.mvp.contract.LoginContract;
import com.xpf.mvp.listener.OnLoginListener;

/**
 * Created by xpf on 2018/4/5 :)
 * GitHub:xinpengfei520
 * Function:用户登录业务的具体实现类
 */
public class UserBusinessImpl implements LoginContract.IUserBusiness {

    @Override
    public void login(final String userName, final String password, final OnLoginListener listener) {
        // 模拟子线程的登录操作
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if ("xpf".equals(userName) && "123".equals(password)) {
                    User user = new User();
                    user.setUserName(userName);
                    user.setPassword(password);

                    if (listener != null) {
                        listener.loginSuccess(user);
                    }
                } else {
                    if (listener != null) {
                        listener.loginFailed();
                    }
                }
            }
        }.start();
    }
}
