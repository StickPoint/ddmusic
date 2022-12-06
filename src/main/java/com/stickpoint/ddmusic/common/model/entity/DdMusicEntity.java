package com.stickpoint.ddmusic.common.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.model.entity
 * @Author: fntp
 * @CreateTime: 2022-11-20  11:48
 * @Description: TODO
 * @Version: 1.0
 */
public abstract class DdMusicEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1329753944253411300L;
    /**
     * 顶点音乐乐库唯一id
     */
    private String ddId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public String getDdId() {
        return ddId;
    }

    public void setDdId(String ddId) {
        this.ddId = ddId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DdMusicEntity{" +
                "ddId='" + ddId + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
