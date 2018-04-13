package com.xpf.mvp.base;

/**
 * Created by xpf on 2018/4/13 :)
 * GitHub:xinpengfei520
 * Function:MVP Fragment的基类
 */
public abstract class MVPCompatFragment<T extends BasePresenter> extends RootFragment {

    protected T mPresenter;

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        mPresenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.clearPresenter();
            mPresenter = null;
        }
    }

    protected abstract T createPresenter();
}
