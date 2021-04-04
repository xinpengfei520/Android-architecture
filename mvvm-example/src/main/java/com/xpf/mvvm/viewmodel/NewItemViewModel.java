package com.xpf.mvvm.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.xpf.mvvm.base.ViewModel;
import com.xpf.mvvm.bean.NewsBean;
import com.xpf.mvvm.command.ReplyCommand;
import com.xpf.mvvm.helper.NewsListHelper;
import com.xpf.mvvm.view.NewsDetailActivity;


/**
 * Created by x-sir on 3/27/21 :)
 * Function:
 */
public class NewItemViewModel implements ViewModel {

    private Context context;
    //model
    public NewsBean.StoriesBean storiesBean;
    //field to presenter
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<String> date = new ObservableField<>();
    public ViewStyle viewStyle = new ViewStyle();

    //Use class viewStyle to wrap field which is binding to style of view
    public static class ViewStyle {
        public final ObservableInt titleTextColor = new ObservableInt();
    }

    public ReplyCommand itemClickCommand = new ReplyCommand(() -> {
        this.viewStyle.titleTextColor.set(context.getResources().getColor(android.R.color.darker_gray));
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.EXTRA_KEY_NEWS_ID, storiesBean.getId());
        context.startActivity(intent);
    });

    public NewItemViewModel(Context context, NewsBean.StoriesBean storiesBean) {
        this.context = context;
        this.storiesBean = storiesBean;
        this.viewStyle.titleTextColor.set(context.getResources().getColor(android.R.color.black));
        if (storiesBean.getExtraField() != null) {
            date.set(NewsListHelper.changeDateFormat(storiesBean.getExtraField().getDate(), NewsListHelper.DAY_FORMAT, NewsListHelper.DAY_UI_FORMAT));
        } else {
            title.set(storiesBean.getTitle());
            imageUrl.set(storiesBean.getImages().get(0));
        }
    }

}
