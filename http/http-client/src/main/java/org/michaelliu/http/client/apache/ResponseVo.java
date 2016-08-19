package org.michaelliu.http.client.apache;

/**
 * Created by Michael on 8/19/16.
 */
public class ResponseVo {

    private int status;

    private String contentType;

    private String responseInfo;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

}
