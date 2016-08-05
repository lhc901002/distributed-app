package org.michaelliu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Michael on 8/5/16.
 */
public class TransactionLog implements Serializable {

    private static final long serialVersionUID = 287165150572113238L;

    private String id;

    private Long accountId;

    private Integer amount;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TransactionLog{" +
                "id='" + id + '\'' +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", createTime=" + createTime +
                '}';
    }

}
