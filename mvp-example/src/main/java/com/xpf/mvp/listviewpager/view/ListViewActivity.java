package com.xpf.mvp.listviewpager.view;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.xpf.mvp.R;
import com.xpf.mvp.base.MvpBaseActivity;
import com.xpf.mvp.listviewpager.adapter.ListViewAdapter;
import com.xpf.mvp.listviewpager.bean.GirlBean;
import com.xpf.mvp.listviewpager.contract.ListViewContract;
import com.xpf.mvp.listviewpager.presenter.ListViewPresenter;

import java.util.List;

/**
 * Created by xpf on 2018/5/4 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class ListViewActivity extends MvpBaseActivity<ListViewContract.View,
        ListViewPresenter<ListViewContract.View>> implements ListViewContract.View {
    /*
     * ListViewPresenter 是父泛型 MvpBasePresenter 的具体实现类
     * ListViewContract.View 是父泛型 V 的具体实现
     */
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = findViewById(R.id.listView);
        mPresenter.initData();
    }

    @Override
    protected ListViewPresenter<ListViewContract.View> createPresenter() {
        return new ListViewPresenter<>();
    }


    @Override
    public void setAdapter(List<GirlBean> list) {
        listView.setAdapter(new ListViewAdapter(this, list));
    }
}
