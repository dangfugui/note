package com.dang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Date Create in 2017/12/26
 */
public class DateUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String toString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date toDate(String date) throws ParseException {
        return toDate(date, DEFAULT_PATTERN);
    }

    public static Date toDate(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    public static void main(String[] args) {
        System.out.println(toString(new Date(), DateUtils.DEFAULT_PATTERN));
    }
}
