//package com.xpf.mvvm.fragment;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.databinding.DataBindingUtil;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.trello.rxlifecycle4.components.RxFragment;
//import com.xpf.mvvm.R;
//import com.xpf.mvvm.databinding.FragmentNewListBinding;
//import com.xpf.mvvm.viewmodel.NewsViewModel;
//
///**
// * Created by x-sir on 3/27/21 :)
// * Function:
// */
//public class NewsListFragment extends RxFragment {
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        FragmentNewListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_list, container, false);
//        binding.setViewModel(new NewsViewModel(this));
//        initView(binding);
//        return binding.getRoot();
//    }
//
//    private void initView(FragmentNewListBinding binding) {
//        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity()));
//    }
//
//    public static class DividerItemDecoration extends RecyclerView.ItemDecoration {
//        private Drawable mDivider;
//
//        public DividerItemDecoration(Context context) {
//            mDivider = context.getResources().getDrawable(R.drawable.divider);
//        }
//
//        @Override
//        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//            int left = parent.getPaddingLeft();
//            int right = parent.getWidth() - parent.getPaddingRight();
//
//            int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View child = parent.getChildAt(i);
//
//                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//
//                int top = child.getBottom() + params.bottomMargin;
//                int bottom = top + mDivider.getIntrinsicHeight();
//
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//        }
//    }
//}
