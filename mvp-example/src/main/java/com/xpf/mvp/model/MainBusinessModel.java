package com.xpf.mvp.model;

import com.xpf.mvp.contract.MainContract;
import com.xpf.mvp.listener.OnProfileListener;

/**
 * Created by xpf on 2018/4/14 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class MainBusinessModel implements MainContract.IMainModel {

    @Override
    public void getProfile(final OnProfileListener listener) {
        // 模拟联网请求
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (listener != null) {
                    listener.onSuccess("My name is Vancy.");
                }
            }
        }.start();
    }

}
