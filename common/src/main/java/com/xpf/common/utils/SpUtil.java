package com.xpf.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xpf on 2017/03/25 :)
 * Function: sp存储的工具类
 */
public class SpUtil {

    private static final String MY_SP = "my_sp";
    private static final String TAG = SpUtil.class.getSimpleName();

    private SpUtil() {
    }

    private static SpUtil instance = new SpUtil();
    private static SharedPreferences mSp = null;

    public static SpUtil getInstance(Context context) {
        if (mSp == null) {
            if (context != null) {
                Context appContext = context.getApplicationContext();
                mSp = appContext.getSharedPreferences(MY_SP, Context.MODE_PRIVATE);
            }
        }
        return instance;
    }

    /**
     * 保存数据
     *
     * @param key   键
     * @param value 值
     */
    public void save(String key, Object value) {
        if (value == null) return;
        if (value instanceof String) {
            mSp.edit().putString(key, (String) value).commit();
        } else if (value instanceof Boolean) {
            mSp.edit().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Integer) {
            mSp.edit().putInt(key, (Integer) value).commit();
        }
    }

    /**
     * 读取String类型数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    /**
     * 读取boolean类型数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }

    /**
     * 读取boolean类型数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public int getInt(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }

    /**
     * 清空sp存储的数据(xxx.xml仍然存在，但是内部没有数据)
     */
    public void clearAll() {
        mSp.edit().clear().commit();
    }

}
