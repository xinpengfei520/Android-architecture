package com.xpf.mvp.presenter;

import android.os.Handler;

import com.xpf.mvp.bean.User;
import com.xpf.mvp.model.IUserBusiness;
import com.xpf.mvp.model.OnLoginListener;
import com.xpf.mvp.model.UserBusinessImpl;
import com.xpf.mvp.view.IUserLoginView;

/**
 * Created by xpf on 2018/4/5 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class UserLoginPresenter {

    private IUserBusiness userBusiness;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    // Presenter必须要能拿到View和Model的实现类
    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBusiness = new UserBusinessImpl();
    }

    public void login() {
        userLoginView.showLoading();
        userBusiness.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}
