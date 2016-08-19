package org.michaelliu.http.client.apache;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by Michael on 8/19/16.
 */
public class RequestConfigExample {

    public void runHttpClient() {
        String url = "https://www.baidu.com";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(1000)
                .setConnectTimeout(1000)
                .build();
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
