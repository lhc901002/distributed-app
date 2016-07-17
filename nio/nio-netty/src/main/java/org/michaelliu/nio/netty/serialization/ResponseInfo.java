package org.michaelliu.nio.netty.serialization;

import java.io.Serializable;

/**
 * Created by Michael on 7/6/16.
 */
public class ResponseInfo implements Serializable{

    private static final long serialVersionUID = 8763442193548809640L;

    private Integer status;

    private String statusInfo;

    public ResponseInfo() {}

    public ResponseInfo(Integer status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "status=" + status +
                ", statusInfo='" + statusInfo + '\'' +
                '}';
    }

}
