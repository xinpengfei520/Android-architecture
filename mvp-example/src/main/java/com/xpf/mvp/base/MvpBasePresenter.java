package com.xpf.mvp.base;

import java.lang.ref.WeakReference;

/**
 * Created by xpf on 2018/5/4 :)
 * GitHub:xinpengfei520
 * Function:Presenter的基类
 */
public class MvpBasePresenter<T> {

    protected WeakReference<T> mViewRef;

    /**
     * view attach to mViewRef
     *
     * @param view Activity
     */
    public void attach(T view) {
        mViewRef = new WeakReference<>(view);
    }

    /**
     * view detach to mViewRef
     */
    public void detach() {
        mViewRef.clear();
    }

    /**
     * mViewRef is or not null.
     *
     * @return result
     */
    public boolean isNonNull() {
        return mViewRef != null;
    }

}
