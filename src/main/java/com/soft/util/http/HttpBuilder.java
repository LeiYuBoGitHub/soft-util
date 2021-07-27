package com.soft.util.http;

import java.io.InputStream;
import java.util.Map;

/**
 * @author 野性的呼唤
 * @date 2021/7/27 17:20
 * @description
 */
public interface HttpBuilder {

    /**
     * 请求地址
     * @param url 地址
     * @return 结果
     */
    HttpBuilder url(String url);

    /**
     * 参数
     * @param json 参数
     * @return 结果
     */
    HttpBuilder param(String json);

    /**
     * 参数
     * @param map 参数
     * @return 结果
     */
    HttpBuilder param(Map<String, Object> map);

    /**
     * GET
     * @return 结果
     */
    String get();

    /**
     * POST
     * @return 结果
     */
    String post();

    /**
     * 请求流数据
     * @return 结果
     */
    InputStream getContent();

    /**
     * 请求流数据
     * @return 结果
     */
    InputStream postContent();

}
