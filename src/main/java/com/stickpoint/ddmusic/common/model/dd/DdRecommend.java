package com.stickpoint.ddmusic.common.model.dd;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author puye(0303)
 * @since 2023/6/29
 */
public class DdRecommend implements Serializable {

    @Serial
    private static final long serialVersionUID = -432900040158771672L;
    /**
     * 歌单名称
     */
    private String name;
    /**
     * 歌单封面
     */
    private String picUrl;
    /**
     * 音乐歌单id
     */
    private String id;
    /**
     * 音乐平台
     */
    private Integer platFormType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPlatFormType() {
        return platFormType;
    }

    public void setPlatFormType(Integer platFormType) {
        this.platFormType = platFormType;
    }

    @Override
    public String toString() {
        return "DdRecommend{" +
                "name='" + name + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", id='" + id + '\'' +
                ", platFormType=" + platFormType +
                '}';
    }
}
