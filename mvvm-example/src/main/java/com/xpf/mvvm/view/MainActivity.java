package com.xpf.mvvm.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;
import com.viewpagerindicator.CirclePageIndicator;
import com.xpf.mvvm.BR;
import com.xpf.mvvm.R;
import com.xpf.mvvm.fragment.NewsListFragment;
import com.xpf.mvvm.messenger.Messenger;
import com.xpf.mvvm.utils.AlphaForegroundColorSpan;
import com.xpf.mvvm.utils.ViewUtils;
import com.xpf.mvvm.viewmodel.MainViewModel;

public class MainActivity extends RxAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AlphaForegroundColorSpan alphaForegroundColorSpan;
    private SpannableString actionBarTitleSpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVariable(BR.viewModel, new MainViewModel(this));

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        CirclePageIndicator circlePageIndicator = findViewById(R.id.indicator);
        ViewPager viewPager = findViewById(R.id.viewpager);
        AppBarLayout barLayout = findViewById(R.id.appBarLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);
        barLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            int height = appBarLayout.getHeight() - getSupportActionBar().getHeight() - ViewUtils.getStatusBarHeight(MainActivity.this);
            int alpha = 255 * (-verticalOffset) / height;
            collapsingToolbarLayout.setExpandedTitleColor(Color.argb(0, 255, 255, 255));
            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.argb(alpha, 255, 255, 255));
        });

        // Indicator must setViewPager after setAdapter,but data for ViewPager is load in other ViewModel
        Messenger.getDefault().register(this, MainViewModel.TOKEN_UPDATE_INDICATOR, () ->
                circlePageIndicator.setViewPager(viewPager));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setFragment();
    }

    private void setFragment() {
        NewsListFragment fragment = new NewsListFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "设置", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            setFragment();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Messenger.getDefault().unregister(this);
    }
}
