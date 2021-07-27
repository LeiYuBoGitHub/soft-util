package com.soft.util.http;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 野性的呼唤
 * @date 2021/7/27 17:56
 * @description
 */
class HttpUtilTest {

    @Test
    void get() {
        String response = HttpUtil.builder()
                .url("https://api.vc.bilibili.com/news/v1/notice/list?product=wh&category=all&page_no=1&page_size=3")
                .get();
        System.out.println(response);
    }

    @Test
    void post() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("name", "soft");
        String response = HttpUtil.builder()
                .param(map)
                .url("http://localhost:8492/people/info")
                .post();
        System.out.println(response);
    }

    @Test
    void getContent() {
    }

    @Test
    void postContent() {
    }
}