package com.soft.util;

import org.junit.jupiter.api.Test;

/**
 * @author 野性的呼唤
 * @date 2021/7/14 17:39
 * @description
 */
public class StringUtilTest {

    @Test
    void yesterday() {

    }

    @Test
    void getFirstUrl() {
        String s = StringUtil.getFirstUrl("http://www.baidu.com/img/1234.jpg");
        System.out.println(s);
    }

    @Test
    void getLastUrl() {
        String s = StringUtil.getLastUrl("http://www.baidu.com/img/1234.jpg");
        System.out.println(s);
    }
}
