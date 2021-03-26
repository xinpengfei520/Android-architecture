package com.xpf.http;

import android.content.Context;

import com.xpf.http.core.OkHttpDns;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by xpf on 2017/9/22 :)
 * Function: network request config initialize
 */

final public class ApiClient {

    private ApiClient() {
    }

    public static void init(Context context) {
        init(context, null);
    }

    /**
     * initialize okhttp client config
     *
     * @param context
     */
    public static void init(Context context, OkHttpClient okHttpClient) {
        if (context == null) {
            return;
        }

        OkHttpClient httpClient;
        if (okHttpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .dns(OkHttpDns.getInstance(context))
//                .hostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return hostname.contains("x-sir.com");// 替换为自己api的域名
//                    }
//                })
//                .sslSocketFactory(SslUtils.getSSLSocketFactory())
                    .build();
        } else {
            httpClient = okHttpClient;
        }

        OkHttpUtils.initClient(httpClient);
    }

}
