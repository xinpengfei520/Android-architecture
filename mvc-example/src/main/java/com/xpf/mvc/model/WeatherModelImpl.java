package com.xpf.mvc.model;

import android.text.TextUtils;
import android.util.Log;

import com.anloq.http.core.Api;
import com.anloq.http.core.ApiRequestListener;
import com.google.gson.Gson;
import com.xpf.mvc.bean.WeatherBean;

/**
 * Created by xpf on 2018/4/4 :)
 * Function:获取天气的接口的具体实现
 */
public class WeatherModelImpl implements WeatherModel {

    @Override
    public void getWeather(String cityNumber, final OnWeatherListener listener) {
        // 开始请求数据
        if (!TextUtils.isEmpty(cityNumber)) {
            String url = "http://www.weather.com.cn/data/sk/" + cityNumber + ".html";
            Api.request()
                    .get(url, new ApiRequestListener() {
                        @Override
                        public void onSuccess(String result) {
                            Log.i("TAG", "result===" + result);
                            if (!TextUtils.isEmpty(result)) {
                                WeatherBean weather = new Gson().fromJson(result, WeatherBean.class);
                                if (listener != null) {
                                    listener.onSuccess(weather);
                                }
                            }
                        }

                        @Override
                        public void onError(String ex) {
                            if (listener != null) {
                                listener.onError();
                            }
                        }
                    });

        } else {
            Log.e("TAG", "cityNumber is null!");
        }
    }
}
