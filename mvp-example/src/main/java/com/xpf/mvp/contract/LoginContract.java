package com.xpf.mvp.contract;

import com.xpf.mvp.base.IPresenter;
import com.xpf.mvp.base.IView;
import com.xpf.mvp.bean.User;

/**
 * Created by xpf on 2018/4/13 :)
 * GitHub:xinpengfei520
 * Function:Contract契约类(接口功能仓库)
 */
public interface LoginContract {

    /**
     * Presenter的抽象接口(继承接口规范)
     */
    interface IUserLoginPresenter extends IPresenter {

        void login();

        void clear();
    }

    /**
     * 登录界面相关视图操作的接口(继承接口规范)
     */
    interface IUserLoginView extends IView {

        String getUserName();

        String getPassword();

        void clearUserName();

        void clearPassword();

        void showLoading();

        void hideLoading();

        void toMainActivity(User user);

        void showFailedError();
    }
}
