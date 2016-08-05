package org.michaelliu.vo;

import java.io.Serializable;

/**
 * Created by Michael on 8/4/16.
 */
public class AccountVo implements Serializable {

    private static final long serialVersionUID = -5331653068526942011L;

    private Long id;

    private String name;

    private Integer balance;

    private String createTime;

    private String updateTime;

    public AccountVo() {
    }

    public AccountVo(Long id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AccountVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public static class Builder {

        private Long id;

        private String name;

        private Integer balance;

        private String createTime;

        private String updateTime;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBalance(Integer balance) {
            this.balance = balance;
            return this;
        }

        public Builder setCreateTime(String createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public AccountVo build() {
            return new AccountVo(this);
        }

    }

    private AccountVo(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.balance = builder.balance;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
    }

}
