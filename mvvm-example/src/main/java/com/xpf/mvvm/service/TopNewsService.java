package com.xpf.mvvm.service;

import com.xpf.mvvm.bean.TopNewsBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public interface TopNewsService {

    @GET("/api/4/news/latest")
    Observable<TopNewsBean> getTopNewsList();
}
