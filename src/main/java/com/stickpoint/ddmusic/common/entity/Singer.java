package com.stickpoint.ddmusic.common.entity;

import java.util.Date;
import java.util.List;

/**
 * description: Singer
 *
 * @ClassName : Singer
 * @Date 2022/10/8 12:52
 * @Author puye(0303)
 * @PackageName com.stickpoint.ddmusic.common.entity
 */
public class Singer {
    /**
     * 名字
     */
    private String firstName;
    /**
     * 姓氏
     */
    private String lastName;
    /**
     * 歌手出生年月
     */
    private Date birthDate;
    /**
     * 专辑列表
     */
    private List<String> albumList;
}
