package com.xpf.mvp.listviewpager.model;

import com.xpf.mvp.R;
import com.xpf.mvp.listviewpager.bean.GirlBean;
import com.xpf.mvp.listviewpager.contract.ListViewContract;
import com.xpf.mvp.listviewpager.listener.OnDataSuccessListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xpf on 2018/5/4 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class ListViewModel implements ListViewContract.Model {

    @Override
    public void initData(OnDataSuccessListener listener) {
        List<GirlBean> list = new ArrayList<>();
        list.add(new GirlBean(R.drawable.image1, "image1"));
        list.add(new GirlBean(R.drawable.image2, "image2"));
        list.add(new GirlBean(R.drawable.image3, "image3"));
        list.add(new GirlBean(R.drawable.image4, "image4"));
        list.add(new GirlBean(R.drawable.image5, "image5"));
        list.add(new GirlBean(R.drawable.image6, "image6"));
        list.add(new GirlBean(R.drawable.image7, "image7"));
        list.add(new GirlBean(R.drawable.image8, "image8"));
        list.add(new GirlBean(R.drawable.image9, "image9"));
        list.add(new GirlBean(R.drawable.image10, "image10"));

        if (listener != null) {
            listener.success(list);
        }
    }
}
