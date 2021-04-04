package com.xpf.mvvm.viewmodel;

import android.app.Activity;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.trello.rxlifecycle4.LifecycleProvider;
import com.xpf.mvvm.base.ViewModel;
import com.xpf.mvvm.bean.NewsDetailBean;
import com.xpf.mvvm.command.ReplyCommand;
import com.xpf.mvvm.net.RetrofitProvider;
import com.xpf.mvvm.net.ToStringConverter;
import com.xpf.mvvm.service.NewsDetailService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by kelin on 16-4-28.
 */
public class NewsDetailViewModel implements ViewModel {

    private Activity activity;

    /**
     * Model
     * data source for ViewModel
     */
    private NewsDetailBean newsDetail;

    /**
     * ViewStyle
     * collection of view style
     */
    public class ViewStyle {
        public final ObservableBoolean isRefreshing = new ObservableBoolean(true);
        public final ObservableBoolean progressRefreshing = new ObservableBoolean(true);
    }

    //data
    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<String> html = new ObservableField<>();
    public final ObservableField<String> title = new ObservableField<>();
    public final ViewStyle viewStyle = new ViewStyle();


    //command
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
        Observable<Notification<NewsDetailBean>> newsDetailOb =
                RetrofitProvider.getInstance().create(NewsDetailService.class)
                        .getNewsDetail(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(((LifecycleProvider) activity).bindToLifecycle())
                        .materialize().share();

        newsDetailOb.filter(Notification::isOnNext)
                .map(n -> n.getValue())
                .doOnNext(m -> newsDetail = m)
                .subscribe(m -> loadHtmlCss(m.getCss()));
    }

    private void loadHtmlCss(List<String> urls) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new ToStringConverter();
                    }
                }).build();

//        Observable.fromIterable(urls)
//                .flatMap(s -> retrofit
//                        .create(NewsDetailCssService.class)
//                        .getNewsDetailCss(s)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .compose(((LifecycleProvider) activity).bindToLifecycle())
//                        .materialize()
//                        .share()
//                        .filter(o -> Notification.createOnNext(o).isOnNext())
//                        .map(n -> ((Notification)n).getValue()))
//                .scan((s1, s2) -> s1 + s2)
//                .last()
//                .doOnNext(s -> newsDetail.setCssStr(s))
//                .doAfterTerminate(() -> viewStyle.progressRefreshing.set(false))
//                .subscribe(s -> initViewModelField());
    }


    private void initViewModelField() {
        viewStyle.isRefreshing.set(false);
        imageUrl.set(newsDetail.getImage());
        Observable.just(newsDetail.getBody())
                .map(s -> s + "<style type=\"text/css\">" + newsDetail.getCssStr())
                .map(s -> s + "</style>")
                .subscribe(s -> html.set(s));
        title.set(newsDetail.getTitle());
    }
}
