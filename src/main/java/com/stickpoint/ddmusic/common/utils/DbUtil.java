package com.stickpoint.ddmusic.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * description: DBUtil
 *
 * @ClassName : DBUtil
 * @Date 2022/10/8 13:44
 * @Author puye(0303)
 * @PackageName com.stickpoint.ddmusic.common.utils
 */
public class DbUtil {

    private static Statement state;

    static {
        try {
           Connection connection =  DriverManager.getConnection("jdbc:sqlite:dbs/xwf.db");
           state = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化本地数据库
     */
    public static void initLocalDateBase(){
        try {
            state.executeUpdate("drop table if exists `dd_user`");
            state.executeUpdate("drop table if exists `dd_localMusic`");
            state.executeUpdate("drop table if exists `dd_neteasy`");
            state.executeUpdate("drop table if exists `dd_singer`");
            state.executeUpdate("drop table if exists `dd_album`");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
