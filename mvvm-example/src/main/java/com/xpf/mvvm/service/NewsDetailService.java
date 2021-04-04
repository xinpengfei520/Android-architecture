package com.xpf.mvvm.service;

import com.xpf.mvvm.bean.NewsDetailBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public interface NewsDetailService {
    @GET("/api/4/news/{id}")
    public Observable<NewsDetailBean> getNewsDetail(@Path("id") long id);
}
