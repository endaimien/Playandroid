package com.ktc.playandroid.util;

import java.text.SimpleDateFormat;

public class TimeUtil {
    public final static String TIMEHOUR = "YYYY-MM-DD";
    public static String getTimehour(long time){
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIMEHOUR);
        return dateFormat.format(time);
    }
}
