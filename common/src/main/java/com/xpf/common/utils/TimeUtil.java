package com.xpf.common.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xpf on 2017/4/14 :)
 * Function:获取当前时间工具类
 */

public class TimeUtil {

    private static final java.text.DateFormat sdfout = new SimpleDateFormat("MM/dd HH:mm");
    private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    /**
     * 返回如下格式的当前时间
     *
     * @return 04/14 15:30
     */
    public static String getCurrentTime() {
        String mTimestamp = "";
        long sysTime = System.currentTimeMillis();
        CharSequence sysTimeStr = DateFormat.format("MM/dd", sysTime);
        mTimestamp += sysTimeStr;
        mTimestamp += " ";
        sysTimeStr = DateFormat.format("HH:mm", sysTime);
        mTimestamp += sysTimeStr;
        return mTimestamp;
    }

    /**
     * 判断当前时间是否在免打扰(22:00-8:00)时间段内
     */
    public static boolean isInSection() {
        Calendar cal = Calendar.getInstance();// 当前日期
        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
        if (hour >= 22 && hour < 8) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前时间是否大于传入时间2min
     */
    public static boolean isMoreTwoMinutes(String time) {
        long sysTime = System.currentTimeMillis();
        long longTime = 0;

        if (time != null) {
            try {
                Date date = sdf.parse(time);
                longTime = date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (sysTime - longTime >= 75000) { // 75S
                return true;
            }
        }
        return false;
    }

    /**
     * 返回如下格式的当前通话时间
     */
    public static String getCurrentCallTimeFormat() {
        String mTimestamp = "";
        long sysTime = System.currentTimeMillis();
        CharSequence sysTimeStr = DateFormat.format("MM月dd日HH:mm", sysTime);
        mTimestamp += sysTimeStr;
        return mTimestamp;
    }

    /**
     * 返回如下格式的当前时间(钥匙同步格式)
     *
     * @return 2017-01-01 18:00:00
     */
    public static String getDateToString() {
        Date d = new Date(System.currentTimeMillis());
        return sf.format(d);
    }

    /**
     * 获取授权时的开始、结束的时间格式
     *
     * @return
     */
    public static String getAuthTime(long time) {
        Date d = new Date(time);
        return sdf.format(d);
    }

    /**
     * 检查是否在有效期内
     *
     * @return true:过期 false:有效
     */
    public static boolean checkIsInvalidDate(String startTime, String endTime) {
        try {
            Date now = new Date(System.currentTimeMillis());
            Date end_date = sdf.parse(endTime);
            Date start_date = sdf.parse(startTime);
            return now.before(end_date) && now.after(start_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取时间(解析消息通知里的时间戳)
     */
    public static String getTime(String time) {
        Date date = new Date();
        if (time != null) {
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return sdfout.format(date);
    }

    /**
     * 解析SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")成毫秒数
     */
    public static long parseDate(String time) {
        long longTime = 0;
        if (time != null) {
            try {
                Date date = sdf.parse(time);
                longTime = date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return longTime;
    }

    /**
     * 解析判断小时数时间是否相等且小于开始时间
     */
    public static boolean parseIsSameHour(String auth_start_date, String auth_end_date) {
        long start = parseDate(auth_start_date);
        long end = parseDate(auth_end_date);
        return start == end || start > end;
    }
}
