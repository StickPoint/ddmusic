package com.stickpoint.ddmusic.common.model.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.model.vo
 * @Author: fntp
 * @CreateTime: 2022-11-20  11:43
 * @Description: TODO
 * @Version: 1.0
 */
public class RequestBaseInfoVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4287998288993810660L;

    private String id;

    private String musicQualityLevel;
    /**
     * 数据的偏移量offset
     */
    private String pageNumber;
    /**
     * 搜索结果的数量 单页的数量
     */
    private String pageSize;

    private String searchKey;
    /**
     * 通用参数名称 type类型
     * 在网易云请求中：
     *  1: 单曲, 10: 专辑, 100: 歌手, 1000: 歌单, 1002: 用户, 1004: MV, 1006: 歌词, 1009: 电台, 1014: 视频
     */
    private Integer type;
    /**
     * 网易云音乐id
     */
    private String wid;
    /**
     * 网易云音乐MV视频分辨率
     */
    private String r;
    /**
     * 排序方式, 1:按推荐排序, 2:按热度排序, 3:按时间排序
     */
    private Integer sortType;
    /**
     * 当sortType为 3 时且页数不是第一页时需传入,值为上一条数据的 time
     */
    private Integer cursor;
    /**
     * 网易云 歌单 ID
     */
    private String pid;
    /**
     * 专用于设置网易云的cookie
     */
    private String data;

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public Integer getCursor() {
        return cursor;
    }

    public void setCursor(Integer cursor) {
        this.cursor = cursor;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusicQualityLevel() {
        return musicQualityLevel;
    }

    public void setMusicQualityLevel(String musicQualityLevel) {
        this.musicQualityLevel = musicQualityLevel;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public String toString() {
        return "RequestBaseInfoVO{" +
                "musicId='" + id + '\'' +
                ", musicQualityLevel='" + musicQualityLevel + '\'' +
                ", pageNumber='" + pageNumber + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", searchKey='" + searchKey + '\'' +
                '}';
    }
}
