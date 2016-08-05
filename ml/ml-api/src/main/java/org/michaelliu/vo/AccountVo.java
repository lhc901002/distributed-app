package org.michaelliu.vo;

import java.io.Serializable;

/**
 * Created by Michael on 8/4/16.
 */
public class AccountVo implements Serializable {

    private static final long serialVersionUID = -5331653068526942011L;

    private Long id;

    private String name;

    public AccountVo() {
    }

    public AccountVo(Long id, String name) {
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

    @Override
    public String toString() {
        return "AccountVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
