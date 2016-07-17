package org.michaelliu.rpc.vo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by Michael on 7/11/16.
 */
public class StudentVo implements Serializable {

    private static final long serialVersionUID = 2282983537122951456L;

    private Long id;

    private String name;

    private Integer age;

    private String createTime;

    private String updateTime;

    public StudentVo() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public boolean containAllRequiredField() {
        return null != id || StringUtils.isNotEmpty(name) || null != age ||
                StringUtils.isNotEmpty(createTime) || StringUtils.isNotEmpty(updateTime);
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public static class Builder {

        private Long id;

        private String name;

        private Integer age;

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

        public Builder setAge(Integer age) {
            this.age = age;
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

        public StudentVo build() {
            return new StudentVo(this);
        }

    }

    private StudentVo(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.createTime = builder.createTime;
        this.updateTime = builder.updateTime;
    }

}
