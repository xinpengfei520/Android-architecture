package com.xpf.mvp.listviewpager.contract;

import com.xpf.mvp.listviewpager.bean.GirlBean;
import com.xpf.mvp.listviewpager.listener.OnDataSuccessListener;

import java.util.List;

/**
 * Created by xpf on 2018/5/4 :)
 * GitHub:xinpengfei520
 * Function:
 */
public interface ListViewContract {

    interface Model {
        void initData(OnDataSuccessListener listener);
    }

    interface View {
        void setAdapter(List<GirlBean> list);
    }

    interface Presenter {
        void initData();
    }
}
