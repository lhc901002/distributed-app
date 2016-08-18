package org.michaelliu.http.client.apache;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 8/18/16.
 */
public class HttpEntityExample {

    public void runHttpEntity() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    byte[] buffer = new byte[1024];
                    StringBuffer stringBuffer = new StringBuffer();
                    int read;
                    while ((read = instream.read(buffer)) != -1) {
                        stringBuffer.append(new String(buffer, 0, read, Charset.forName("UTF-8")));
                    }
                    System.out.println(stringBuffer.toString());
                } finally {
                    instream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void runForm() {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("param1", "value1"));
        formparams.add(new BasicNameValuePair("param2", "value2"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httpPost = new HttpPost("http://localhost:8080/http");
        httpPost.setEntity(entity);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (entity != null) {
                InputStream instream = httpEntity.getContent();
                try {
                    byte[] buffer = new byte[1024];
                    StringBuffer stringBuffer = new StringBuffer();
                    int read;
                    while ((read = instream.read(buffer)) != -1) {
                        stringBuffer.append(new String(buffer, 0, read, Charset.forName("UTF-8")));
                    }
                    System.out.println(stringBuffer.toString());
                } finally {
                    instream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HttpEntityExample httpEntityExample = new HttpEntityExample();
//        httpEntityExample.runHttpEntity();
        httpEntityExample.runForm();
    }

}
