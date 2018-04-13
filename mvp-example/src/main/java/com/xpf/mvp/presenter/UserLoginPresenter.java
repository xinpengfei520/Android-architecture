package com.xpf.mvp.presenter;

import android.os.Handler;

import com.xpf.mvp.bean.User;
import com.xpf.mvp.contract.LoginContract;
import com.xpf.mvp.model.IUserBusiness;
import com.xpf.mvp.model.OnLoginListener;
import com.xpf.mvp.model.UserBusinessImpl;

/**
 * Created by xpf on 2018/4/5 :)
 * GitHub:xinpengfei520
 * Function:Presenter的业务逻辑的具体实现
 */
public class UserLoginPresenter implements LoginContract.IUserLoginPresenter {

    private IUserBusiness userBusiness;
    private LoginContract.IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    // Presenter必须要能拿到View和Model的实现类
    public UserLoginPresenter(LoginContract.IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBusiness = new UserBusinessImpl();
    }

    @Override
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

    @Override
    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}
