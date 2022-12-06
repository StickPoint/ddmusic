package com.stickpoint.ddmusic.common.model.coolDog;

import com.google.gson.annotations.SerializedName;

import java.io.Serial;
import java.io.Serializable;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.model.coolDog
 * @Author: fntp
 * @CreateTime: 2022-11-19  16:59
 * @Description: TODO
 * @Version: 1.0
 */
public class Singers implements Serializable {
    @Serial
    private static final long serialVersionUID = -6478661012309729877L;
    @SerializedName("ip_id")
    private int ipId;
    private String name;
    private int id;
    public void setIpId(int ipId) {
        this.ipId = ipId;
    }
    public int getIpId() {
        return ipId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

}