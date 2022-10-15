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
            Class<?> derbyEmbedDriver = Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection connection =  DriverManager.getConnection("jdbc:derby:ddmusic;create=true;");
           state = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化本地数据库
     */
    public static void initLocalDateBase(){

    }
}
