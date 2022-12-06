package com.stickpoint.ddmusic.common.model.coolDog;

import java.io.Serial;
import java.io.Serializable;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.model.coolDog
 * @Author: fntp
 * @CreateTime: 2022-11-19  17:01
 * @Description: TODO
 * @Version: 1.0
 */
public class Qualitymap implements Serializable {
    @Serial
    private static final long serialVersionUID = -3809682403497586938L;
    private int attr0;
    public void setAttr0(int attr0) {
        this.attr0 = attr0;
    }
    public int getAttr0() {
        return attr0;
    }

}