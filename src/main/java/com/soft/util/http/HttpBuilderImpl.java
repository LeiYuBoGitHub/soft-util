package com.soft.util.http;

import com.alibaba.fastjson.JSONObject;
import com.soft.util.StringUtil;
import org.apache.http.Consts;
import org.apache.http.Header;
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

    private final HttpConfig httpConfig;

    private CloseableHttpClient httpClient;

    private CloseableHttpResponse response;

    public HttpBuilderImpl() {
        httpConfig = new HttpConfig();
    }

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
    public HttpBuilder addGet(HttpGet httpGet) {
        httpConfig.setHttpGet(httpGet);
        return this;
    }

    @Override
    public HttpBuilder addPost(HttpPost httpPost) {
        httpConfig.setHttpPost(httpPost);
        return this;
    }

    @Override
    public HttpBuilder addCloseableHttpClient(CloseableHttpClient client) {
        httpConfig.setClient(client);
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
        if (httpConfig.getClient() == null) {
            httpClient = HttpClients.createDefault();
        } else {
            httpClient = httpConfig.getClient();
        }
        try {
            response = httpClient.execute(httpGet());
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String post() {
        if (httpConfig.getClient() == null) {
            httpClient = HttpClients.createDefault();
        } else {
            httpClient = httpConfig.getClient();
        }
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

    private HttpGet httpGet() {
        if (httpConfig.getHttpGet() != null) {
            return httpGet();
        }
        if (StringUtil.isBlank(httpConfig.getUrl())) {
            return null;
        }
        return new HttpGet(httpConfig.getUrl());
    }

    private HttpPost httpPost() {
        if (httpConfig.getHttpPost() != null) {
            return httpPost();
        }
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

    /**
     * 获取标准Http客户端
     * @return 结果
     */
    public CloseableHttpClient getStandardHttpClient(){
        return HttpClients.custom()
                .setDefaultRequestConfig(HttpConfig.REQUESTCONFIG)
                .build();
    }

    /**
     * 返回信息异常提示
     * @param response 结果
     */
    public static void responseException(CloseableHttpResponse response){
        System.out.println("**********返回发生异常!已输出相关信息**********");
        System.out.println("状态码:"+response.getStatusLine().getStatusCode());
        System.out.println("国家:"+response.getLocale().getCountry());
        System.out.println("-----返回头信息-----");
        Header[] head = response.getAllHeaders();
        for (Header header : head) {
            System.out.println(header);
        }
        System.out.println("-----返回头信息-----");
        System.out.println("**********返回发生异常!已输出相关信息**********");
    }

    public void close() {
        close(response);
        close(httpClient);
    }

    private void close(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close(CloseableHttpClient httpClient) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
