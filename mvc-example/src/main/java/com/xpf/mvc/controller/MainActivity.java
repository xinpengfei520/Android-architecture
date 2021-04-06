package com.xpf.mvc.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xpf.mvc.R;
import com.xpf.mvc.bean.WeatherBean;
import com.xpf.mvc.model.OnWeatherListener;
import com.xpf.mvc.model.WeatherModel;
import com.xpf.mvc.model.WeatherModelImpl;

public class MainActivity extends AppCompatActivity implements OnWeatherListener {

    private Button btnRequest;
    private TextView tvResult;
    private WeatherModel mWeatherModel;
    private static final String CITY_NUMBER = "101010100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        btnRequest = findViewById(R.id.btnRequest);
        tvResult = findViewById(R.id.tvResult);
        mWeatherModel = new WeatherModelImpl();
    }

    private void initListener() {
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWeatherModel.getWeather(CITY_NUMBER, MainActivity.this);
            }
        });
    }

    @Override
    public void onSuccess(WeatherBean weather) {
        if (weather != null) {
            String content = new Gson().toJson(weather);
            tvResult.setText(content);
        }
    }

    @Override
    public void onError() {
        Log.e("TAG", "获取天气数据失败！");
    }
}
