package com.stickpoint.ddmusic.common.model.neteasy;
import com.stickpoint.ddmusic.common.annotation.TableColumnField;
import com.stickpoint.ddmusic.common.model.entity.AbstractDdMusicEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.model.neteasy
 * @Author: fntp
 * @CreateTime: 2022-11-07  22:03
 * @Description: TODO
 * @Version: 1.0
 */
public class NetEasyMusicEntityAbstract extends AbstractDdMusicEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 3085656316138757278L;
    /**
     * 歌曲名称
     */
    private String name;
    /**
     * 获得歌曲信息的关键字段 id
     */
    private long id;
    /**
     * 专辑 album al
     */
    private Album al;

    private String dt;

    @TableColumnField("序号")
    private String ddNumber;

    @TableColumnField("标题")
    private String ddTitle;

    @TableColumnField("歌手")
    private String ddArtists;

    @TableColumnField("专辑")
    private String ddAlbum;

    @TableColumnField("时长")
    private String ddTimes;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    /**
     * 艺术家歌唱者
     */
    private List<Artist> ar;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public List<Artist> getAr() {
        return ar;
    }

    public void setAr(List<Artist> ar) {
        this.ar = ar;
    }

    @Override
    public String getDdNumber() {
        return ddNumber;
    }

    @Override
    public void setDdNumber(String ddNumber) {
        this.ddNumber = ddNumber;
    }

    @Override
    public String getDdTitle() {
        return ddTitle;
    }

    @Override
    public void setDdTitle(String ddTitle) {
        this.ddTitle = ddTitle;
    }

    @Override
    public String getDdArtists() {
        return ddArtists;
    }

    @Override
    public void setDdArtists(String ddArtists) {
        this.ddArtists = ddArtists;
    }

    @Override
    public String getDdAlbum() {
        return ddAlbum;
    }

    @Override
    public void setDdAlbum(String ddAlbum) {
        this.ddAlbum = ddAlbum;
    }

    @Override
    public String getDdTimes() {
        return ddTimes;
    }

    @Override
    public void setDdTimes(String ddTimes) {
        this.ddTimes = ddTimes;
    }

    public Album getAl() {
        return al;
    }
    public void setAl(Album al) {
        this.al = al;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NetEasyMusicEntityAbstract that = (NetEasyMusicEntityAbstract) o;
        return id == that.id && name.equals(that.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
