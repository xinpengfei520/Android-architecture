package com.xpf.mvp.base;

import android.content.Context;

import com.xpf.mvp.utils.NetworkHelper;

/**
 * Created by xpf on 2018/4/13 :)
 * GitHub:xinpengfei520
 * Function:Presenter的基类
 */
public abstract class BasePresenter<AttachView extends IView> {

    private Context mContext;
    private AttachView mView;

    /**
     * constructor
     *
     * @param context
     * @param view
     */
    public BasePresenter(Context context, AttachView view) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }
        mContext = context.getApplicationContext();
        mView = view;
    }

    /**
     * 获取关联的View
     *
     * @return
     */
    public AttachView getAttachedView() {
        if (mView == null) {
            throw new NullPointerException("AttachView is null");
        }
        return mView;
    }

    /**
     * 获取关联的Context
     *
     * @return
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 清空Presenter
     */
    public void clearPresenter() {
        mContext = null;
        mView = null;
    }

    /**
     * View是否关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    /**
     * 网络是否连接
     *
     * @return
     */
    public boolean isNetworkConnected() {
        if (mContext == null) {
            throw new NullPointerException("mContext is null");
        }
        return NetworkHelper.isNetworkConnected(mContext);
    }

    public abstract void start();

    public abstract void destroy();
}
