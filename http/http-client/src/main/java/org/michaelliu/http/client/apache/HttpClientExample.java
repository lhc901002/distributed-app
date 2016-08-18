package org.michaelliu.http.client.apache;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by Michael on 8/18/16.
 */
public class HttpClientExample {

    public static void main(String[] args) throws IOException {
        String url = "https://www.baidu.com";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        // run debug to check the details of response
        CloseableHttpResponse response = httpclient.execute(httpget);

        System.out.println(response);
    }

}
