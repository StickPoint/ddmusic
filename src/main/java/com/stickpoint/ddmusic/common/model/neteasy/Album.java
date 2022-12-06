package com.stickpoint.ddmusic.common.model.neteasy;
import com.google.gson.annotations.SerializedName;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
public class Album implements Serializable {

    @Serial
private static final long serialVersionUID = -4295873927533863374L;
    private long id;
    private String name;
    private String picUrl;
    private List<String> tns;
    @SerializedName("pic_str")
    private String picStr;
    private long pic;
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

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public void setTns(List<String> tns) {
        this.tns = tns;
    }
    public List<String> getTns() {
        return tns;
    }

    public void setPicStr(String picStr) {
        this.picStr = picStr;
    }
    public String getPicStr() {
        return picStr;
    }

    public void setPic(long pic) {
        this.pic = pic;
    }
    public long getPic() {
        return pic;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", tns=" + tns +
                ", pic_str='" + picStr + '\'' +
                ", pic=" + pic +
                '}';
    }
}