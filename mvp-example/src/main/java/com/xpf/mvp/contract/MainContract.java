package com.xpf.mvp.contract;

import com.xpf.mvp.base.IPresenter;
import com.xpf.mvp.base.IView;
import com.xpf.mvp.listener.OnProfileListener;

/**
 * Created by xpf on 2018/4/14 :)
 * GitHub:xinpengfei520
 * Function:
 */
public interface MainContract {

    interface IMainPresenter extends IPresenter {

        void getProfile();
    }

    interface IMainView extends IView {

        void showProfile(String profile);
    }

    interface IMainModel {
        void getProfile(final OnProfileListener listener);
    }

}
