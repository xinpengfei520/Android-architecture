package com.xpf.mvc.model;

/**
 * Created by xpf on 2018/4/4 :)
 * Function:定义获取天气的接口(输入)
 */
public interface WeatherModel {
    void getWeather(String cityNumber, OnWeatherListener listener);
}
