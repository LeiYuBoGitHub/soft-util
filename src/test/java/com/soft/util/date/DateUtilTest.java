package com.soft.util.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * @author 野性的呼唤
 * @date 2021/6/29 19:00
 * @description
 */
public class DateUtilTest {

    @Test
    void yesterday() {
        System.out.println(DateUtil.yesterday());
    }

    @Test
    void today() {
        System.out.println(DateUtil.today());
    }

    @Test
    void tomorrow() {
        System.out.println(DateUtil.tomorrow());
    }

    @Test
    void localDateToDate() {
        String time = DateUtil.dateToString(DateUtil.localDateTimeToDate(LocalDateTime.now()), DateUtil.DATE_DAY_TIME);
        System.out.println(time);
    }

    @Test
    void localToString() {
        String time = DateUtil.localToString(LocalDateTime.now(), DateUtil.DATE_DAY_TIME);
        System.out.println(time);
    }

    @Test
    void monthLastDay() {
    }

    @Test
    void nowMonthFirstDay() {
    }

    @Test
    void nowMonthDay() {
    }

    @Test
    void dateToString() {
    }
}
