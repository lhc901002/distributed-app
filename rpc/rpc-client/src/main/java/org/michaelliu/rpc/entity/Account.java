package org.michaelliu.rpc.entity;

import java.io.Serializable;

/**
 * Created by Michael on 7/14/16.
 */
public class Account implements Serializable {

    private static final long serialVersionUID = -712228846628964582L;

    private Long id;

    private String name;

    public Account() {
    }

    public Account(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
