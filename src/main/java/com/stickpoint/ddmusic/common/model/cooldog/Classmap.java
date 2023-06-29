package com.stickpoint.ddmusic.common.model.cooldog;

import java.io.Serial;
import java.io.Serializable;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.model.cooldog
 * @Author: fntp
 * @CreateTime: 2022-11-19  17:07
 * @Description: TODO
 * @Version: 1.0
 */
public class Classmap implements Serializable {
    @Serial
    private static final long serialVersionUID = 1038730094531018955L;
    private long attr0;
    public void setAttr0(long attr0) {
        this.attr0 = attr0;
    }
    public long getAttr0() {
        return attr0;
    }

}
