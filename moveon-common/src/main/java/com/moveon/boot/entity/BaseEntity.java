package com.moveon.boot.entity;

import com.moveon.boot.annotation.entity.TableField;

import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/21 21:33
 * @Version 1.0
 **/
public class BaseEntity {

    @TableField
    private Long id;

    @TableField
    private Date createTime;

    @TableField
    private Long createBy;

    @TableField
    private Date updateTime;

    @TableField
    private Long updateBy;

    /**
     * 删除标记
     * 0: 未删除  1: 已删除
     */
    @TableField
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
