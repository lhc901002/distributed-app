package org.michaelliu.http.client.apache;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by Michael on 8/19/16.
 */
public class ResponseHandlerExample {

    private ResponseHandler<ResponseVo> handler = new ResponseHandler<ResponseVo>() {

        @Override
        public ResponseVo handleResponse(HttpResponse response) throws IOException {
            ResponseVo rspVo = new ResponseVo();
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine);
            rspVo.setStatus(statusLine.getStatusCode());
            if (statusLine.getStatusCode() >= 300) {
                rspVo.setResponseInfo("http returns error code " + statusLine.getStatusCode());
            } else {
                HttpEntity entity = response.getEntity();
                if (entity == null || entity.getContent() == null) {
                    rspVo.setResponseInfo("Response contains no content");
                } else {
                    ContentType contentType = ContentType.getOrDefault(entity);
                    rspVo.setContentType(contentType.getMimeType());
                    Charset charset = contentType.getCharset();
                    if (charset == null) {
                        charset = Charset.forName("UTF-8");
                    }
                    InputStream instream = entity.getContent();
                    try {
                        byte[] buffer = new byte[1024];
                        StringBuffer stringBuffer = new StringBuffer();
                        int read;
                        while ((read = instream.read(buffer)) != -1) {
                            stringBuffer.append(new String(buffer, 0, read, charset));
                        }
                        rspVo.setResponseInfo(stringBuffer.toString());
                    } finally {
                        instream.close();
                    }
                }
            }
            System.out.println(JSON.toJSONString(rspVo));
            return rspVo;
        }

    };

    public void runHandler() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        try {
            httpclient.execute(httpget, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new ResponseHandlerExample().runHandler();
    }

}
