package com.soft.util.http;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author 野性的呼唤
 * @date 2021/7/27 17:28
 * @description
 */
public class HttpConfig {

    /**
     * 连接超时
     */
    public static final int TIMEOUT = 3000;

    /**
     * 字符编码
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * 默认设备
     */
    public static final String DEFAULT_USER_AGENT = "User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";

    /**
     * 手机设备
     */
    public static final String MOBILE_USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Mobile Safari/537.36";

    /**
     * 标准Cookie策略
     */
    public static final RequestConfig REQUESTCONFIG = RequestConfig.custom()
            .setSocketTimeout(TIMEOUT)
            .setCookieSpec(CookieSpecs.STANDARD_STRICT).build();

    private String url;

    private String param;

    private HttpGet httpGet;

    private HttpPost httpPost;

    private CloseableHttpClient client;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public HttpGet getHttpGet() {
        return httpGet;
    }

    public void setHttpGet(HttpGet httpGet) {
        this.httpGet = httpGet;
    }

    public HttpPost getHttpPost() {
        return httpPost;
    }

    public void setHttpPost(HttpPost httpPost) {
        this.httpPost = httpPost;
    }

    public CloseableHttpClient getClient() {
        return client;
    }

    public void setClient(CloseableHttpClient client) {
        this.client = client;
    }
}
