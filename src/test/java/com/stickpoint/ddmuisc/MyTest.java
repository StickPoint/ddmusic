package com.stickpoint.ddmuisc;

import com.stickpoint.ddmusic.common.utils.HttpUtils;

/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmuisc
 * @Author: fntp
 * @CreateTime: 2022-11-02  22:19
 * @Description: TODO
 * @Version: 1.0
 */
public class MyTest {

    public static void main(String[] args) {
        String getResult = HttpUtils.sendGet("http://101.37.160.25:5000/v1/qq/search", "key=周杰伦&offset=1&limit=100");
        System.out.println(getResult);
    }
}
