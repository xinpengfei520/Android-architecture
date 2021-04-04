package com.xpf.mvvm.net.service;

import com.xpf.mvvm.bean.NewsBean;
import com.xpf.mvvm.bean.NewsDetailBean;
import com.xpf.mvvm.bean.TopNewsBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public interface ApiService {
    @GET("/api/4/news/{id}")
    Observable<NewsDetailBean> getNewsDetail(@Path("id") long id);

    @GET
    Observable<String> getNewsDetailCss(@Url String url);

    @GET("/api/4/news/before/{date}")
    Observable<NewsBean> getNewsList(@Path("date") String date);

    @GET("/api/4/news/latest")
    Observable<TopNewsBean> getTopNewsList();
}
