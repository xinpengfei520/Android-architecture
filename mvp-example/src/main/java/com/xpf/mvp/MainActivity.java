package com.xpf.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xpf.mvp.bean.User;
import com.xpf.mvp.presenter.UserLoginPresenter;
import com.xpf.mvp.view.IUserLoginView;

public class MainActivity extends AppCompatActivity implements IUserLoginView {

    private EditText etUserName, etPassword;
    private Button btnClear, btnLogin;
    private ProgressBar progressBar;
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.clear();
            }
        });
    }

    private void initView() {
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnClear = findViewById(R.id.btnClear);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        etUserName.setText("");
    }

    @Override
    public void clearPassword() {
        etPassword.setText("");
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUserName() +
                " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                "login failed!", Toast.LENGTH_SHORT).show();
    }
}
