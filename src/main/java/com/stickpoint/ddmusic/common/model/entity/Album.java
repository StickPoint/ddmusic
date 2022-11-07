package com.stickpoint.ddmusic.common.model.entity;
import java.net.URL;
import java.util.Date;
import java.util.Objects;

/**
 * description: Album 专辑类
 *
 * @ClassName : Album
 * @Date 2022/10/8 12:53
 * @Author puye(0303)
 * @PackageName com.stickpoint.ddmusic.common.entity
 */
public class Album {
    /**
     * 专辑名称
     */
    private String name;
    /**
     * 发布日期
     */
    private Date publishDate;
    /**
     * 网络url地址
     */
    private URL netUrl;
    /**
     * 来源类型
     */
    private Integer sourceTye;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;

    public Album(AlbumBuilder builder) {
        this.createdTime = builder.createdTime;
        this.updatedTime = builder.updatedTime;
        this.name = builder.name;
        this.netUrl = builder.netUrl;
        this.publishDate = builder.publishDate;
        this.sourceTye = builder.sourceTye;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public URL getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(URL netUrl) {
        this.netUrl = netUrl;
    }

    public Integer getSourceTye() {
        return sourceTye;
    }

    public void setSourceTye(Integer sourceTye) {
        this.sourceTye = sourceTye;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Album album = (Album) o;
        return name.equals(album.name) && netUrl.equals(album.netUrl) && sourceTye.equals(album.sourceTye);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, netUrl, sourceTye);
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", publishDate=" + publishDate +
                ", netUrl=" + netUrl +
                ", sourceTye=" + sourceTye +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }

    /**
     * 构建内部类 链式builder构建者模式
     */
    private static final class AlbumBuilder {
        /**
         * 专辑名称
         */
        private String name;
        /**
         * 发布日期
         */
        private Date publishDate;
        /**
         * 网络url地址
         */
        private URL netUrl;
        /**
         * 来源类型
         */
        private Integer sourceTye;
        /**
         * 创建时间
         */
        private Date createdTime;
        /**
         * 更新时间
         */
        private Date updatedTime;

        /**
         *
         * @param name
         * @return
         */
        public AlbumBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         *
         * @param publishDate
         * @return
         */
        public AlbumBuilder setPublishDate(Date publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        /**
         *
         * @param netUrl
         * @return
         */
        public AlbumBuilder setNetUrl(URL netUrl) {
            this.netUrl = netUrl;
            return this;
        }

        /**
         * 设置数据源
         * @param sourceTye 数据来源
         * @return 返回一个构建者 builder
         */
        public AlbumBuilder setSourceTye(Integer sourceTye) {
            this.sourceTye = sourceTye;
            return this;
        }

        /**
         * 设置更新日期
         * @param createdTime 创建日期
         * @return 返回builder
         */
        public AlbumBuilder setCreatedTime(Date createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        /**
         * 设置更新日期
         * @param updatedTime 更新日期
         * @return 返回builder
         */
        public AlbumBuilder setUpdatedTime(Date updatedTime) {
            this.updatedTime = updatedTime;
            return this;
        }

        /**
         * 构建一个Album对象
         * @return 返回一个专辑对象
         */
        public Album build(){
            return new Album(this);
        }
    }
}
