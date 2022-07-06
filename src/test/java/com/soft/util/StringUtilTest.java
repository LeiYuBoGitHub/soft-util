package com.soft.util;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;

/**
 * @author 野性的呼唤
 * @date 2021/7/14 17:39
 * @description
 */
public class StringUtilTest {

    @Test
    void yesterday() {
        String x = DigestUtil.md5Hex("s");
        System.out.println(x);
        String r = x.substring(0,5).toUpperCase();
        System.out.println(r);
        String d = x.substring(5,10);
        System.out.println(d);
        System.out.println(r + d + "&*");
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
