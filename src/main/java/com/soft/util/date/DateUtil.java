package com.soft.util.date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author 野性的呼唤
 * @date 2021/5/27 19:16
 * @description
 */
public class DateUtil {

    public final static String DATE_DAY = "yyyy-MM-dd";

    public final static String DATE_DAY_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 昨天
     * @return  结果
     */
    public static Date yesterday() {
        LocalDate localDate = LocalDate.now().minusDays(1);
        return localDateToDate(localDate);
    }

    /**
     * 今天
     * @return 结果
     */
    public static Date today() {
        LocalDate localDate = LocalDate.now();
        return localDateToDate(localDate);
    }

    /**
     * 明天
     * @return  结果
     */
    public static Date tomorrow() {
        LocalDate localDate = LocalDate.now().plusDays(1);
        return localDateToDate(localDate);
    }

    /**
     * LocalDate 转 Date
     * @param localDate 参数
     * @return 结果
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDateTime 转 Date
     * @param localDateTime 参数
     * @return 结果
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone( ZoneId.systemDefault()).toInstant());
    }

    /**
     * 时间 转 文本
     * @param o 参数
     * @param format 类型
     * @return 结果
     */
    public static String localToString(Object o, String format) {
        if (o instanceof LocalDate) {
            LocalDate localDate = (LocalDate) o;
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern(format);
            return localDate.format(formatters);
        }
        if (o instanceof LocalDateTime) {
            LocalDateTime localDateTime = (LocalDateTime) o;
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(formatters);
        }
        return null;
    }

    /**
     * 月份的最后一天
     * @param month 月份 当前月份减去
     * @return  结果
     */
    public static Date monthLastDay(int month) {
        LocalDate localDate = LocalDate.now().minusMonths(month);
        // 本月的最后一天
        localDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return localDateToDate(localDate);
    }

    /**
     * 本月第一天
     * @return 结果
     */
    public static Date nowMonthFirstDay() {
        return nowMonthDay(1);
    }

    /**
     * 本月第几天
     * @param day 天数
     * @return 结果
     */
    public static Date nowMonthDay(int day) {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = LocalDate.of(today.getYear(),today.getMonth(),1);
        return localDateToDate(firstDay);
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        Date date = today();
        String s = DateUtil.dateToString(date, DateUtil.DATE_DAY);
        System.out.println(s);
    }

}
