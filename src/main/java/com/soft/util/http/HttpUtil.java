package com.soft.util.http;

/**
 * @author 野性的呼唤
 * @date 2021/7/27 18:01
 * @description
 */
public class HttpUtil {

    public static HttpBuilder builder() {
        return new HttpBuilderImpl();
    }
}
