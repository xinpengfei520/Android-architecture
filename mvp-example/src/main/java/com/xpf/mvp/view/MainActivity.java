package com.xpf.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xpf.mvp.MVPApplication;
import com.xpf.mvp.R;
import com.xpf.mvp.base.BasePresenter;
import com.xpf.mvp.base.MVPCompatActivity;
import com.xpf.mvp.contract.MainContract;
import com.xpf.mvp.presenter.MainPresenter;

public class MainActivity extends MVPCompatActivity implements MainContract.IMainView {

    private Button btnProfile;
    private TextView tvProfile;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getProfile();
            }
        });
    }

    private void initView() {
        btnProfile = findViewById(R.id.btnProfile);
        tvProfile = findViewById(R.id.tvProfile);
    }

    @Override
    protected BasePresenter createPresenter() {
        mPresenter = new MainPresenter(MVPApplication.getAppContext(), this);
        return mPresenter;
    }

    @Override
    public void showProfile(String profile) {
        tvProfile.setText(profile);
    }
}
