package org.michaelliu.nio.netty.serialization;

import java.io.Serializable;

/**
 * Created by Michael on 7/4/16.
 */
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = 3756302160365894878L;

    private Integer id;

    private Integer type;

    private String message;

    public RequestInfo() {}

    public RequestInfo(Integer id, Integer type, String message) {
        this.id = id;
        this.type = type;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "id=" + id +
                ", type=" + type +
                ", message='" + message + '\'' +
                '}';
    }

}
