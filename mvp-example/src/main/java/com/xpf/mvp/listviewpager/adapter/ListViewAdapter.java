package com.xpf.mvp.listviewpager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xpf.mvp.R;
import com.xpf.mvp.listviewpager.bean.GirlBean;

import java.util.List;

/**
 * Created by xpf on 2018/5/4 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<GirlBean> mList;

    public ListViewAdapter(Context context, List<GirlBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_listview, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.textView = convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GirlBean girlBean = mList.get(position);
        holder.imageView.setImageResource(girlBean.getId());
        holder.textView.setText(girlBean.getDescribe());

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
