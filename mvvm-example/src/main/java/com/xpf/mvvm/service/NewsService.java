package com.xpf.mvvm.service;

import com.xpf.mvvm.bean.NewsBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public interface NewsService {
    @GET("/api/4/news/before/{date}")
    Observable<NewsBean> getNewsList(@Path("date") String date);
}
