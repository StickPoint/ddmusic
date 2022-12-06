package com.stickpoint.ddmusic.common.model.neteasy;
import com.stickpoint.ddmusic.common.model.entity.DdMusicEntity;

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
public class NetEasyMusicEntity extends DdMusicEntity implements Serializable {

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
    public String toString() {
        return "NetEasyMusicEntity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", al=" + al +
                ", ar=" + ar +
                '}';
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
        NetEasyMusicEntity that = (NetEasyMusicEntity) o;
        return id == that.id && name.equals(that.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
