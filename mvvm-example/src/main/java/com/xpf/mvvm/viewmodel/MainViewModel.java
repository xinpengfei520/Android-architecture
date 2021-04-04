package com.xpf.mvvm.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.xpf.mvvm.App;
import com.xpf.mvvm.BR;
import com.xpf.mvvm.R;
import com.xpf.mvvm.base.ViewModel;
import com.xpf.mvvm.bean.TopNewsBean;
import com.xpf.mvvm.messenger.Messenger;
import com.xpf.mvvm.utils.AppUtils;

import io.reactivex.rxjava3.core.Observable;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public class MainViewModel implements ViewModel {

    // Token to Messenger append package name to be unique
    public static final String TOKEN_UPDATE_INDICATOR = "token_update_indicator" + AppUtils.getPackageName(App.getAppContext());
    private Context context;
    // viewModel for recycler header viewPager
//    public final ItemView topItemView = ItemView.of(BR.viewModel, R.layout.viewpager_item_top_news);
    public final ItemBinding<TopItemViewModel> topItemView = ItemBinding.of(BR.viewModel, R.layout.viewpager_item_top_news);
    public final ObservableList<TopItemViewModel> topItemViewModel = new ObservableArrayList<>();

    public MainViewModel(Activity activity) {
        context = activity;
        Messenger.getDefault().register(activity, NewsViewModel.TOKEN_TOP_NEWS_FINISH, TopNewsBean.class, (news) -> {
            Observable.just(news)
                    .doOnNext(m -> topItemViewModel.clear())
                    .flatMap(n -> Observable.fromIterable(n.getTop_stories()))
                    .doOnNext(m -> topItemViewModel.add(new TopItemViewModel(context, m)))
                    .toList()
                    .subscribe((l) -> Messenger.getDefault().sendNoMsgToTargetWithToken(TOKEN_UPDATE_INDICATOR, activity));
        });
    }
}
