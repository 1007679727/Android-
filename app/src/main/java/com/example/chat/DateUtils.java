package com.example.chat;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static long MILLIS_IN_DAY = 24*60*60*1000L;

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String formatTime(Long time) {
        Date date = new Date(time);
        String formatTime = simpleDateFormat.format(date);
        return formatTime;
    }

    /**
     * 时间字符串转成Long
     *
     * @param time
     * @return
     */
    public static Long formatTime(String time) {
        if (!TextUtils.isEmpty(time)) {
            try {
                Date date = simpleDateFormat.parse(time);
                return date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param time
     * @return 1: 今天   2：昨天   3：以前  其他：解析错误
     */
    public static int offsetToday(String time) {
        if (TextUtils.isEmpty(time)) {
            return 0;
        }
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowFormat = dateFormat.format(now);
        try {
            Date timeDate = dateFormat.parse(time);
            Date nowDate = dateFormat.parse(nowFormat);
            int intervalTime = (int) ((nowDate.getTime() - timeDate.getTime()) / (24 * 60 * 60 * 1000));
            if (intervalTime < 1) {
                return 1;
            } else if (intervalTime == 1) {
                return 2;
            } else if (intervalTime >= 2) {
                return 3;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 通过毫秒值判断是否为同一天
     * @param ms1
     * @param ms2
     * @return true同一天
     */
    public static boolean isSameDayOfMillis(final long ms1, final long ms2) {
        final long interval = ms1 - ms2;
        return interval < MILLIS_IN_DAY
                && interval > -1L * MILLIS_IN_DAY
                && toDay(ms1) == toDay(ms2);
    }

    private static long toDay(long millis) {
        return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
    }
}
