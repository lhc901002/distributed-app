package org.michaelliu.http.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 类HttpRequester
 *
 * @author liuhaocheng
 * @since 2016-06-16
 */
public class HttpRequester {

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final String DEFAULT_CONTENT_TYPE = "application/json";

    private static final int DEFAULT_CONNECTION_TIMEOUT = 20000;

    private static final int DEFAULT_POOL_SIZE = 100;

    private static final int HTTP_OK = 200;

    private PoolingHttpClientConnectionManager connectionManager;

    private RequestConfig requestConfig;

    private String contentCharset;

    private String contentType;

    private String requestUrl;

    public HttpRequester() {
        this(null);
    }

    public HttpRequester(String url) {
        this(url, DEFAULT_POOL_SIZE, DEFAULT_CONNECTION_TIMEOUT, DEFAULT_CHARSET, DEFAULT_CONTENT_TYPE);
    }

    public HttpRequester(String url, int poolSize, int maxTimeOut, String charset, String contentType) {
        this.requestUrl = url;
        // 设置连接池
        connectionManager = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connectionManager.setMaxTotal(poolSize);
        connectionManager.setDefaultMaxPerRoute(poolSize);

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(maxTimeOut);
        // 设置读取超时
        configBuilder.setSocketTimeout(maxTimeOut);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(maxTimeOut);
        requestConfig = configBuilder.build();
        contentCharset = charset;
        this.contentType = contentType;
    }

    public HttpRequester setUrl(String url) {
        requestUrl = url;
        return this;
    }

    public HttpRequester setCharset(String charset) {
        contentCharset = charset;
        return this;
    }

    public HttpRequester setPoolSize(int poolSize) {
        connectionManager.setMaxTotal(poolSize);
        connectionManager.setDefaultMaxPerRoute(poolSize);
        return this;
    }

    public HttpRequester setMaxTimeOut(int maxTimeOut) {
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(maxTimeOut);
        // 设置读取超时
        configBuilder.setSocketTimeout(maxTimeOut);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(maxTimeOut);
        requestConfig = configBuilder.build();
        return this;
    }

    public String sendGetRequest() {
        return sendGetRequest(requestUrl, new HashMap<String, String>());
    }

    public String sendGetRequest(Map<String, String> params) {
        return sendGetRequest(requestUrl, params);
    }

    private String sendGetRequest(String url, Map<String, String> params) {
        if (url.contains("?")) {
            if (url.contains("&")) {
                url += "&";
            }
        } else {
            url += "?";
        }
        url += URLEncodedUtils.format(getParamList(params), contentCharset);
        HttpGet httpGet = null;
        CloseableHttpClient httpClient = null;
        String rspString = null;
        try {
            httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
//            httpGet.addHeader("accept", contentCharset);
            httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HTTP_OK) {
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    rspString = toResponseJson(-1, "系统异常：返回为空");
                } else {
                    InputStream inputStream = entity.getContent();
                    rspString = IOUtils.toString(inputStream, contentCharset);
                }
            } else {
                rspString = toResponseJson(statusCode, response.getStatusLine().getReasonPhrase());
            }
            httpClient.close();
        } catch (IOException e) {
            rspString = toResponseJson(-1, "系统异常：" + e);
        } finally {
            abortConnection(httpGet, httpClient);
        }
        return rspString;
    }

    public String sendPostRequest(String content) {
        return sendPostRequest(requestUrl, content);
    }

    private String sendPostRequest(String url, String content) {
        HttpPost httpPost = null;
        CloseableHttpClient httpClient = null;
        String rspString = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(content, contentCharset);
            stringEntity.setContentEncoding(contentCharset);
            stringEntity.setContentType(contentType);
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HTTP_OK) {
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    rspString = toResponseJson(-1, "系统异常：返回为空");
                } else {
                    rspString = EntityUtils.toString(entity, contentCharset);
                }
            } else {
                rspString = toResponseJson(statusCode, response.getStatusLine().getReasonPhrase());
            }
            httpClient.close();
        } catch (IOException e) {
            rspString = toResponseJson(-1, "系统异常：" + e);
        } finally {
            abortConnection(httpPost, httpClient);
        }
        return rspString;
    }

    private List<NameValuePair> getParamList(Map<String, String> params) {
        List<NameValuePair> paramList = null;
        if (params != null) {
            paramList = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return paramList;
    }

    private String toResponseJson(int errcode, String errmsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errcode", errcode);
        jsonObject.put("errmsg", errmsg);
        return JSON.toJSONString(jsonObject);
    }

    private void abortConnection(final HttpRequestBase httpRequestBase, final HttpClient httpClient){
        if (httpRequestBase != null) {
            httpRequestBase.abort();
        }
        if (httpClient != null) {
            connectionManager.shutdown();
        }
    }

}
