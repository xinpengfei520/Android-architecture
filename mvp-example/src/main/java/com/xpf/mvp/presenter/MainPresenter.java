package com.xpf.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.xpf.mvp.base.BasePresenter;
import com.xpf.mvp.contract.MainContract;
import com.xpf.mvp.listener.OnProfileListener;
import com.xpf.mvp.model.MainBusinessModel;
import com.xpf.mvp.utils.UIUtils;

/**
 * Created by xpf on 2018/4/14 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class MainPresenter extends BasePresenter implements MainContract.IMainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private MainContract.IMainView mView;
    private MainBusinessModel model;

    /**
     * constructor
     *
     * @param context
     * @param view
     */
    public MainPresenter(Context context, MainContract.IMainView view) {
        super(context, view);
        this.mView = view;
        model = new MainBusinessModel();
    }

    @Override
    public void start() {
        Log.i(TAG, "start()");
    }

    @Override
    public void destroy() {
        Log.i(TAG, "destroy()");
    }

    @Override
    public void getProfile() {
        Log.i(TAG, "getProfile()");
        model.getProfile(new OnProfileListener() {
            @Override
            public void onSuccess(final String profile) {
                UIUtils.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showProfile(profile);
                    }
                });
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
