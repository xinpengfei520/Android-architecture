package com.xpf.mvvm.viewmodel;

import android.app.Activity;
import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.trello.rxlifecycle4.LifecycleProvider;
import com.xpf.mvvm.base.ViewModel;
import com.xpf.mvvm.bean.NewsDetailBean;
import com.xpf.mvvm.command.ReplyCommand;
import com.xpf.mvvm.net.RetrofitProvider;
import com.xpf.mvvm.net.service.ApiService;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public class NewsDetailViewModel implements ViewModel {

    private static final String TAG = "NewsDetailViewModel";
    private Activity activity;

    /**
     * Model
     * data source for ViewModel
     */
    private NewsDetailBean newsDetail;

    /**
     * Collection of view style
     */
    public static class ViewStyle {
        public final ObservableBoolean isRefreshing = new ObservableBoolean(true);
        public final ObservableBoolean progressRefreshing = new ObservableBoolean(true);
    }

    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<String> html = new ObservableField<>();
    public final ObservableField<String> title = new ObservableField<>();
    public final ViewStyle viewStyle = new ViewStyle();

    public final ReplyCommand onRefreshCommand = new ReplyCommand<>(() -> {
        viewStyle.isRefreshing.set(true);
        viewStyle.progressRefreshing.set(false);
        loadData(newsDetail.getId());
    });

    public NewsDetailViewModel(Activity activity, long id) {
        this.activity = activity;
        loadData(id);
    }

    private void loadData(long id) {
        Observable<Notification<NewsDetailBean>> newsDetailObservable =
                RetrofitProvider.getInstance().create(ApiService.class)
                        .getNewsDetail(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(((LifecycleProvider) activity).bindToLifecycle())
                        .materialize().share();

        newsDetailObservable.filter(Notification::isOnNext)
                .map(Notification::getValue)
                .doOnNext(m -> newsDetail = m)
                .subscribe(m -> loadHtmlCss(m.getCss()), throwable -> {
                    String message = throwable.getMessage();
                    Log.e(TAG, "-> message:" + message);
                });
    }

    private void loadHtmlCss(List<String> urls) {
        Log.e(TAG, "loadHtmlCss() -> urls:" + urls);

        Observable.fromIterable(urls)
                .flatMap(s -> {
                    Observable<Notification<String>> observable =
                            RetrofitProvider.getInstance().create(ApiService.class)
                                    .getNewsDetailCss(s)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .compose(((LifecycleProvider) activity).bindToLifecycle())
                                    .materialize()
                                    .share();

                    return observable.filter(Notification::isOnNext)
                            .map(Notification::getValue);

                })
                .scan((s1, s2) -> s1 + s2)
                .takeLast(1)
                .doOnNext(s -> newsDetail.setCssStr(s))
                .doAfterTerminate(() -> viewStyle.progressRefreshing.set(false))
                .subscribe(s -> initViewModelField(), throwable -> {
                    String message = throwable.getMessage();
                    Log.e(TAG, "flatMap accept() -> message:" + message);
                });
    }

    private void initViewModelField() {
        viewStyle.isRefreshing.set(false);
        imageUrl.set(newsDetail.getImage());

        Observable.just(newsDetail.getBody())
                .map(s -> s + "<style type=\"text/css\">" + newsDetail.getCssStr())
                .map(s -> s + "</style>")
                .subscribe(html::set, throwable -> {
                    String message = throwable.getMessage();
                    Log.e(TAG, "NewsViewModel accept() -> message:" + message);
                });

        title.set(newsDetail.getTitle());
    }
}
