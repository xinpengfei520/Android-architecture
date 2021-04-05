package com.xpf.mvvm.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;
import com.xpf.mvvm.BR;
import com.xpf.mvvm.R;
import com.xpf.mvvm.viewmodel.NewsDetailViewModel;

public class NewsDetailActivity extends RxAppCompatActivity {

    private static final String TAG = "NewsDetailActivity";
    public static final String EXTRA_KEY_NEWS_ID = "key_news_id";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long id = getIntent().getLongExtra(EXTRA_KEY_NEWS_ID, -1);
        Log.e(TAG, "NewsDetailActivity onCreate() -> id:" + id);

        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail);
        binding.setVariable(BR.viewModel, new NewsDetailViewModel(this, id));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedText);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedTitleText);
        WebView webView = findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}