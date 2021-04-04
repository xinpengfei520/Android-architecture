package com.xpf.mvvm.adapter;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.xpf.mvvm.command.ReplyCommand;
import com.xpf.mvvm.command.ResponseCommand;


/**
 * Created by x-sir on 3/26/21 :)
 * Function:
 */
public final class ViewBindingAdapter {

    @BindingAdapter({"clickCommand"})
    public static void clickCommand(View view, final ReplyCommand clickCommand) {
        view.setOnClickListener(v -> {
            if (clickCommand != null) {
                clickCommand.execute();
            }
        });
    }

    @BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(View view, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } else {
            view.clearFocus();
        }
    }

    @BindingAdapter({"onFocusChangeCommand"})
    public static void onFocusChangeCommand(View view, final ReplyCommand<Boolean> onFocusChangeCommand) {
        view.setOnFocusChangeListener((v, hasFocus) -> {
            if (onFocusChangeCommand != null) {
                onFocusChangeCommand.execute(hasFocus);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @BindingAdapter({"onTouchCommand"})
    public static void onTouchCommand(View view, final ResponseCommand<MotionEvent, Boolean> onTouchCommand) {
        view.setOnTouchListener((v, event) -> {
            if (onTouchCommand != null) {
                return onTouchCommand.execute(event);
            }
            return false;
        });
    }
}

