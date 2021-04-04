package com.xpf.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;

import com.xpf.mvvm.BR;
import com.xpf.mvvm.base.ViewModel;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by x-sir on 3/26/21 :)
 * Function:
 */
public final class ViewGroupBindingAdapter {

//    @BindingAdapter({"itemView", "viewModels"})
//    public static void addViews(ViewGroup viewGroup, final ItemView itemView, final ObservableList<ViewModel> viewModelList) {
//        if (viewModelList != null && !viewModelList.isEmpty()) {
//            viewGroup.removeAllViews();
//            for (ViewModel viewModel : viewModelList) {
//                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
//                        itemView.layoutRes(), viewGroup, true);
//                binding.setVariable(itemView.bindingVariable(), viewModel);
//            }
//        }
//    }

    // this is new method

    @BindingAdapter({"itemView", "viewModels"})
    public static void addViews(ViewGroup viewGroup, final ItemBinding itemView, final ObservableList<ViewModel> viewModelList) {
        if (viewModelList != null && !viewModelList.isEmpty()) {
            viewGroup.removeAllViews();
            for (ViewModel viewModel : viewModelList) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        itemView.layoutRes(), viewGroup, true);
                binding.setVariable(BR.viewModel, viewModel);
            }
        }
    }
}

