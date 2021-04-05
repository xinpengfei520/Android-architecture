package com.xpf.mvvm.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xpf.mvvm.net.converter.ToStringConverter;
import com.xpf.mvvm.net.factory.AnnotatedConverterFactory;
import com.xpf.mvvm.net.factory.ApiTypeAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public class RetrofitProvider {

    private static Retrofit mRetrofit;

    private RetrofitProvider() {
    }

    public static Retrofit getInstance() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://news-at.zhihu.com")
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(getConverters())
                    .build();
        }

        return mRetrofit;
    }

    private static AnnotatedConverterFactory getConverters() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ApiTypeAdapterFactory("data"))
                .create();

        return new AnnotatedConverterFactory.Builder()
                .add(AnnotatedConverterFactory.Str.class, ToStringConverter.getConverter())
                .add(AnnotatedConverterFactory.Json.class, GsonConverterFactory.create(gson))
                .build();
    }
}
