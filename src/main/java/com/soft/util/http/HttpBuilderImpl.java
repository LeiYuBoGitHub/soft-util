package com.soft.util.http;

import com.alibaba.fastjson.JSONObject;
import com.soft.util.StringUtil;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author 野性的呼唤
 * @date 2021/7/27 17:21
 * @description
 */
public class HttpBuilderImpl implements HttpBuilder {

    public HttpBuilderImpl() {
        httpConfig = new HttpConfig();
    }

    private HttpConfig httpConfig;

    private static CloseableHttpClient httpClient;

    private static CloseableHttpResponse response;

    @Override
    public HttpBuilder url(String url) {
        httpConfig.setUrl(url);
        return this;
    }

    @Override
    public HttpBuilder param(String json) {
        httpConfig.setParam(json);
        return this;
    }

    @Override
    public HttpBuilder param(Map<String, Object> map) {
        String json = JSONObject.toJSONString(map);
        httpConfig.setParam(json);
        return this;
    }

    @Override
    public String get() {
        httpClient = HttpClients.createDefault();
        try {
            response = httpClient.execute(httpGet());
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    @Override
    public String post() {
        httpClient = HttpClients.createDefault();
        try {
            response = httpClient.execute(httpPost());
            if (response != null) {
                return EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public InputStream getContent() {
        httpClient = HttpClients.createDefault();
        try {
            response = httpClient.execute(httpGet());
            if (response != null) {
                return response.getEntity().getContent();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    @Override
    public InputStream postContent() {
        if (StringUtil.isBlank(httpConfig.getUrl())) {
            return null;
        }
        httpClient = HttpClients.createDefault();
        try {
            response = httpClient.execute(httpPost());
            if (response != null) {
                return response.getEntity().getContent();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpBuilder builder() {
        httpConfig = new HttpConfig();
        return new HttpBuilderImpl();
    }

    private HttpGet httpGet() {
        if (StringUtil.isBlank(httpConfig.getUrl())) {
            return null;
        }
        return new HttpGet(httpConfig.getUrl());
    }

    private HttpPost httpPost() {
        if (StringUtil.isBlank(httpConfig.getUrl())) {
            return null;
        }
        HttpPost httpPost = new HttpPost(httpConfig.getUrl());
        if (StringUtil.isNotBlank(httpConfig.getParam())) {
            StringEntity postEntity = new StringEntity(httpConfig.getParam(),"UTF-8");
            httpPost.setEntity(postEntity);
        }
        httpPost.addHeader("Content-Type", "application/json");
        return httpPost;
    }

    private static void close() {
        close(response);
        close(httpClient);
    }

    private static void close(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(CloseableHttpClient httpClient) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
