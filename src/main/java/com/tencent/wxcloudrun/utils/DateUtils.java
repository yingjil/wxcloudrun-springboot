package com.tencent.wxcloudrun.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    /**
     * 字符串转日期
     *
     * @param dateStr
     * @return
     */
    public static LocalDate toDate(String dateStr) {
        return toDate(dateStr, "yyyy-MM-dd");
    }

    /**
     * 根据formatPattern转换日期
     *
     * @param dateStr
     * @param formatPattern
     * @return
     */
    public static LocalDate toDate(String dateStr, String formatPattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(formatPattern));
    }


    /**
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public static String format(LocalDate date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * 根据formatPattern将日期转字符串
     *
     * @param date
     * @param formatPattern
     * @return
     */
    public static String format(LocalDate date, String formatPattern) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date);
    }

}
