package org.michaelliu.vo;

import java.io.Serializable;

/**
 * Created by Michael on 8/5/16.
 */
public class TransactionLogVo implements Serializable {

    private static final long serialVersionUID = -7859851570861572392L;

    private String id;

    private Long accountId;

    private Integer amount;

    private String createTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TransactionLogVo{" +
                "id='" + id + '\'' +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", createTime='" + createTime + '\'' +
                '}';
    }

}
