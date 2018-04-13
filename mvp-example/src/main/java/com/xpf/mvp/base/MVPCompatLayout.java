package com.xpf.mvp.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by xpf on 2018/4/13 :)
 * GitHub:xinpengfei520
 * Function:MVP Layout的基类
 */
public abstract class MVPCompatLayout<T extends BasePresenter> extends RootLayout {

    protected T mPresenter;

    public MVPCompatLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPresenter = createPresenter();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mPresenter != null) {
            mPresenter.clearPresenter();
            mPresenter = null;
        }
    }

    protected abstract T createPresenter();
}
