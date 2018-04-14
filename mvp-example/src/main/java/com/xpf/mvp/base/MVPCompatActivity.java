package com.xpf.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by xpf on 2018/4/13 :)
 * GitHub:xinpengfei520
 * Function:MVP Activity的基类
 */
public abstract class MVPCompatActivity<T extends BasePresenter> extends Activity {

    protected T mPresenter;

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        mPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.clearPresenter();
        mPresenter = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mPresenter.clearPresenter();
        mPresenter = null;
    }

    /**
     * 创建一个Presenter
     *
     * @return
     */
    protected abstract T createPresenter();
}
