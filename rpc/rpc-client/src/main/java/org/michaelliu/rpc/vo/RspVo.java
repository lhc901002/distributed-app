package org.michaelliu.rpc.vo;

/**
 * Created by Michael on 6/28/16.
 */
public class RspVo {

    private Integer status;

    private String statusInfo;

    public RspVo() {}

    public RspVo(Integer status, String statusInfo) {
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

}
