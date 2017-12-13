package com.xpf.myarchitecture;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xpf.common.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast();
                count++;
            }
        });
    }

    private void showToast() {
        switch (count) {
            case 0:
                ToastUtil.showTopToast(mContext, "Hello World!");
                break;
            case 1:
                ToastUtil.showBottomToast(mContext, "Hello World!");
                break;
            case 2:
                ToastUtil.showCenterToast(mContext, "Hello World!");
                break;
            case 3:
                ToastUtil.showCustomToast(mContext, "Hello World!", 1);
                break;
            case 4:
                ToastUtil.showShortToast(mContext, "Hello World!");
                break;
            case 5:
                ToastUtil.showLongToast(mContext, "Hello World!");
                break;
        }
    }
}
