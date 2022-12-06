package com.stickpoint.ddmusic.common.model.neteasy;

import java.util.List;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.model.neteasy
 * @Author: fntp
 * @CreateTime: 2022-11-19  16:27
 * @Description: TODO
 * @Version: 1.0
 */
public class Artist {
    private long id;
    private String name;
    private List<String> tns;
    private List<String> alias;
    private List<String> alia;
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setTns(List<String> tns) {
        this.tns = tns;
    }
    public List<String> getTns() {
        return tns;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }
    public List<String> getAlias() {
        return alias;
    }

    public void setAlia(List<String> alia) {
        this.alia = alia;
    }
    public List<String> getAlia() {
        return alia;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tns=" + tns +
                ", alias=" + alias +
                ", alia=" + alia +
                '}';
    }
}
