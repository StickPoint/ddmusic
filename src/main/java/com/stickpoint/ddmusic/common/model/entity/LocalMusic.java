package com.stickpoint.ddmusic.common.model.entity;

import java.net.URL;

/**
 * description: LocalMusic
 *
 * @ClassName : LocalMusic
 * @Date 2022/10/8 12:49
 * @Author fntp
 * @PackageName com.stickpoint.ddmusic.common.entity
 */
public class LocalMusic {
    /**
     * 音乐名称
     */
    private String musicName;
    /**
     * 歌手名称
     */
    private String singer;
    /**
     * 音乐类型字符串：mp3 m4a wav
     */
    private String musicTypeStr;
    /**
     * 状态： 0--本地存在文件 ； 1--本地已删除； 2--未下载
     */
    private Integer status;
    /**
     * 下载到本地之后的本地地址
     * 或者是扫描之后加载的本地地址
     */
    private URL localUrl;
    /**
     * 网络地址
     */
    private URL netUrl;
    /**
     * 文件大小长度
     */
    private Integer length;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getMusicTypeStr() {
        return musicTypeStr;
    }

    public void setMusicTypeStr(String musicTypeStr) {
        this.musicTypeStr = musicTypeStr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public URL getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(URL localUrl) {
        this.localUrl = localUrl;
    }

    public URL getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(URL netUrl) {
        this.netUrl = netUrl;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    private static final class Builder{

    }
}
