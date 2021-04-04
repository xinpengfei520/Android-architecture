package com.xpf.mvvm.service;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public interface NewsDetailCssService {
    @GET
    Observable<String> getNewsDetailCss(@Url String url);
}
