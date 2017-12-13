package com.xpf.common.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by xpf on 2017/03/23.
 * Function :Activity的基类
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setContentView();
        initView();
        setOnClickListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 设置 ContentView布局
     */
    public abstract void setContentView();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据源
     */
    public abstract void bindData();

    /**
     * 设置监听事件
     */
    public void setOnClickListener() {
    }
}
