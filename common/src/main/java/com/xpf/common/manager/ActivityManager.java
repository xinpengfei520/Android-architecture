package com.xpf.common.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by xpf on 2017/05/09 :)
 * Function:提供当前应用中所有创建的Activity的管理器(恶汉式单例)
 * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈的大小
 */

public class ActivityManager {

    // 提供操作activity的容器：Stack
    private Stack<Activity> activityStack = new Stack<>();

    public ActivityManager() {
    }

    private static ActivityManager instance = new ActivityManager();

    public static ActivityManager getInstance() {
        return instance;
    }

    /**
     * activity的添加
     */
    public void add(Activity activity) {
        if (activity != null) {
            activityStack.push(activity);
        }
    }

    /**
     * 删除指定的activity
     */
    public void remove(Activity activity) {
        for (int i = activityStack.size(); i >= 0; i--) {
            if (activity != null && activity.getClass().equals(activityStack.get(i).getClass())) {
                activity.finish();       // 销毁当前的activity
                activityStack.remove(i); // 将指定的activity对象从栈空间移除
            }
        }
    }

    /**
     * 删除当前的activity(栈顶的activity)
     */
    public void removeCurrent() {
        activityStack.lastElement().finish();
        activityStack.remove(activityStack.lastElement());
    }

    /**
     * 删除所有的activity
     */
    public void removeAll() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            activityStack.get(i).finish();
            activityStack.remove(i);
        }
    }

    /**
     * 返回栈大小
     */
    public int getSize() {
        return activityStack.size();
    }
}
