package com.xpf.mvvm.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.ObservableField;

import com.xpf.mvvm.base.ViewModel;
import com.xpf.mvvm.bean.TopNewsBean;
import com.xpf.mvvm.command.ReplyCommand;
import com.xpf.mvvm.view.NewsDetailActivity;

/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public class TopItemViewModel implements ViewModel {

    private Context context;
    //model
    public TopNewsBean.TopStoriesBean topStoriesBean;
    //field to presenter
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> imageUrl = new ObservableField<>();

    public final ReplyCommand topItemClickCommand = new ReplyCommand(() -> {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.EXTRA_KEY_NEWS_ID, topStoriesBean.getId());
        context.startActivity(intent);
    });

    public TopItemViewModel(Context context, TopNewsBean.TopStoriesBean topStoriesBean) {
        this.context = context;
        this.topStoriesBean = topStoriesBean;
        title.set(topStoriesBean.getTitle());
        imageUrl.set(topStoriesBean.getImage());
    }
}
