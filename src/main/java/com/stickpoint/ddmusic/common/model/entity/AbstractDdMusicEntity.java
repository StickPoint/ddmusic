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
public abstract class AbstractDdMusicEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1329753944253411300L;
    /**
     * 数字ID 排序用的
     */
    private String ddNumber;
    /**
     * 顶点音乐乐库唯一id
     */
    private String ddId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String ddAlbum;

    private String ddArtists;

    private String ddTimes;

    private String ddTitle;

    public String getDdTitle() {
        return ddTitle;
    }

    public void setDdTitle(String ddTitle) {
        this.ddTitle = ddTitle;
    }

    public String getDdAlbum() {
        return ddAlbum;
    }

    public void setDdAlbum(String ddAlbum) {
        this.ddAlbum = ddAlbum;
    }

    public String getDdArtists() {
        return ddArtists;
    }

    public void setDdArtists(String ddArtists) {
        this.ddArtists = ddArtists;
    }

    public String getDdTimes() {
        return ddTimes;
    }

    public void setDdTimes(String ddTimes) {
        this.ddTimes = ddTimes;
    }

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

    public String getDdNumber() {
        return ddNumber;
    }

    public void setDdNumber(String ddNumber) {
        this.ddNumber = ddNumber;
    }

    @Override
    public String toString() {
        return "DdMusicEntity{" +
                "numberId='" + ddNumber + '\'' +
                ", ddId='" + ddId + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", ddAlbum='" + ddAlbum + '\'' +
                ", ddArtists='" + ddArtists + '\'' +
                ", ddTimes='" + ddTimes + '\'' +
                '}';
    }
}