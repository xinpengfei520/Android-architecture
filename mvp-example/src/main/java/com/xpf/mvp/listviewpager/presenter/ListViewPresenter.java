package com.xpf.mvp.listviewpager.presenter;

import com.xpf.mvp.base.MvpBasePresenter;
import com.xpf.mvp.listviewpager.bean.GirlBean;
import com.xpf.mvp.listviewpager.contract.ListViewContract;
import com.xpf.mvp.listviewpager.listener.OnDataSuccessListener;
import com.xpf.mvp.listviewpager.model.ListViewModel;

import java.util.List;

/**
 * Created by xpf on 2018/5/4 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class ListViewPresenter<T extends ListViewContract.View> extends MvpBasePresenter<T> implements ListViewContract.Presenter {

    private ListViewContract.Model mListViewModel = new ListViewModel();

    public ListViewPresenter() {
    }

    @Override
    public void initData() {
        mListViewModel.initData(new OnDataSuccessListener() {
            @Override
            public void success(List<GirlBean> list) {
                if (isNonNull()) {
                    mViewRef.get().setAdapter(list);
                }
            }
        });
    }
}
