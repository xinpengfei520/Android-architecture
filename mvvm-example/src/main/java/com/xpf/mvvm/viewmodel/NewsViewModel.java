package com.xpf.mvvm.viewmodel;

import android.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.util.Pair;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableList;

import com.trello.rxlifecycle4.LifecycleProvider;
import com.xpf.mvvm.App;
import com.xpf.mvvm.BR;
import com.xpf.mvvm.R;
import com.xpf.mvvm.base.ViewModel;
import com.xpf.mvvm.bean.NewsBean;
import com.xpf.mvvm.bean.TopNewsBean;
import com.xpf.mvvm.command.ReplyCommand;
import com.xpf.mvvm.helper.NewsListHelper;
import com.xpf.mvvm.messenger.Messenger;
import com.xpf.mvvm.net.RetrofitProvider;
import com.xpf.mvvm.net.service.ApiService;
import com.xpf.mvvm.utils.AppUtils;

import java.util.Calendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public class NewsViewModel implements ViewModel {

    private static final String TAG = "NewsViewModel";
    public static final String TOKEN_TOP_NEWS_FINISH = "token_top_news_finish" + AppUtils.getPackageName(App.getAppContext());
    private Fragment fragment;
    private NewsBean news;
    private TopNewsBean topNews;

    // viewModel for RecyclerView
    public final ObservableList<NewItemViewModel> itemViewModel = new ObservableArrayList<>();
    // view layout for RecyclerView
//    public final ItemViewSelector<NewItemViewModel> itemView = new BaseItemViewSelector<NewItemViewModel>() {
//        @Override
//        public void select(ItemView itemView, int position, NewItemViewModel itemViewModel) {
//            itemView.set(BR.viewModel, itemViewModel.storiesBean.getExtraField() != null ? R.layout.listitem_news_header : R.layout.listitem_news);
//        }
//
//        @Override
//        public int viewTypeCount() {
//            return 2;
//        }
//    };

    public final OnItemBind<NewItemViewModel> itemView = new OnItemBind<NewItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, NewItemViewModel item) {
            itemBinding.set(BR.viewModel, item.storiesBean.getExtraField() != null ? R.layout.listitem_news_header : R.layout.listitem_news);
        }
    };

    //collection of view style,wrap to a class to manage conveniently!
    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableBoolean isRefreshing = new ObservableBoolean(true);
        public final ObservableBoolean progressRefreshing = new ObservableBoolean(true);
    }

    /**
     * command
     */
    public final ReplyCommand onRefreshCommand = new ReplyCommand<>(() -> {
        Observable.just(Calendar.getInstance())
                .doOnNext(c -> c.add(Calendar.DAY_OF_MONTH, 1))
                .map(c -> NewsListHelper.DAY_FORMAT.format(c.getTime()))
                .subscribe(d -> loadTopNews(d));
    });

    /**
     * @param p count of listview items,is unused here!
     * @params,funciton when return true，the callback just can be invoked!
     */
    public final ReplyCommand<Integer> onLoadMoreCommand = new ReplyCommand<>((p) -> {
        loadNewsList(news.getDate());
    });

    public NewsViewModel(Fragment fragment) {
        this.fragment = fragment;

        BehaviorSubject<Notification<TopNewsBean>> subject = BehaviorSubject.create();
        subject.filter(Notification::isOnNext)
                .subscribe(n -> Toast.makeText(fragment.getActivity(), "load finish!", Toast.LENGTH_SHORT).show());

        Observable.just(Calendar.getInstance())
                .doOnNext(c -> c.add(Calendar.DAY_OF_MONTH, 1))
                .map(c -> NewsListHelper.DAY_FORMAT.format(c.getTime()))
                .subscribe(this::loadTopNews, throwable -> {
                    String message = throwable.getMessage();
                    Log.e(TAG, "NewsViewModel accept() -> message:" + message);
                });
    }

    private void loadNewsList(String date) {
        viewStyle.isRefreshing.set(true);

        Observable<Notification<NewsBean>> newsListOb =
                RetrofitProvider.getInstance().create(ApiService.class)
                        .getNewsList(date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(((LifecycleProvider) fragment).bindToLifecycle())
                        .materialize().share();

        newsListOb.filter(Notification::isOnNext)
                .map(n -> n.getValue())
                .filter(m -> !m.getStories().isEmpty())
                .doOnNext(m -> Observable.just(m.getDate()).map(d -> new NewsBean.StoriesBean.ExtraField(true, d))
                        .map(d -> new NewsBean.StoriesBean(d))
                        .subscribe(d -> itemViewModel.add(new NewItemViewModel(fragment.getActivity(), d))))
                .doOnNext(m -> news = m)
                .doAfterTerminate(() -> viewStyle.isRefreshing.set(false))
                .flatMap(m -> Observable.fromIterable(m.getStories()))
                .subscribe(i -> itemViewModel.add(new NewItemViewModel(fragment.getActivity(), i)));

        NewsListHelper.dealWithResponseError(newsListOb.filter(Notification::isOnError)
                .map(Notification::getError));
    }

    private void loadTopNews(String date) {
        viewStyle.isRefreshing.set(true);

        Observable<TopNewsBean> topNewsOb =
                RetrofitProvider.getInstance().create(ApiService.class)
                        .getTopNewsList()
                        .compose(((LifecycleProvider) fragment).bindToLifecycle());

        Observable<NewsBean> newsListOb =
                RetrofitProvider.getInstance().create(ApiService.class)
                        .getNewsList(date)
                        .compose(((LifecycleProvider) fragment).bindToLifecycle());

        Observable<Notification<Pair<TopNewsBean, NewsBean>>> combineRequestOb = Observable.combineLatest(topNewsOb, newsListOb, Pair::new)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .materialize().share();

        combineRequestOb.filter(Notification::isOnNext)
                .map(Notification::getValue)
                .map(p -> p.first)
                .filter(m -> !m.getTop_stories().isEmpty())
                .doOnNext(m -> Observable.just(NewsListHelper.isTomorrow(date)).filter(b -> b).subscribe(b -> itemViewModel.clear()))
                .subscribe(m -> Messenger.getDefault().send(m, TOKEN_TOP_NEWS_FINISH));

        combineRequestOb.filter(Notification::isOnNext)
                .map(Notification::getValue)
                .map(p -> p.second).filter(m -> !m.getStories().isEmpty())
                .doOnNext(m -> news = m)
                .flatMap(m -> Observable.fromIterable(m.getStories()))
                .subscribe(i -> itemViewModel.add(new NewItemViewModel(fragment.getActivity(), i)));

        combineRequestOb.subscribe((n) -> {
            viewStyle.isRefreshing.set(false);
            viewStyle.progressRefreshing.set(false);
        });

        NewsListHelper.dealWithResponseError(combineRequestOb.filter(Notification::isOnError)
                .map(Notification::getError));
    }

}
