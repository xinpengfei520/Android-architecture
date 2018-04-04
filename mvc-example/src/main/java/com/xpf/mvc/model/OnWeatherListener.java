package com.xpf.mvc.model;

import com.xpf.mvc.bean.WeatherBean;

/**
 * Created by xpf on 2018/4/4 :)
 * Function:获取天气结果的监听器(输出)
 */
public interface OnWeatherListener {

    void onSuccess(WeatherBean weather);

    void onError();
}
