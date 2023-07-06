package com.stickpoint.ddmusic.common.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * description: SqlHelper
 *
 * @ClassName : SqlHelper
 * @Date 2022/11/23 9:43
 * @Author puye(0303)
 * @PackageName com.stickpoint.ddmusic.common.mapper
 */
@SuppressWarnings("unused")
public class SqlHelper {
    /**
     * sqlite-log日志
     */
    private static final Logger log = LoggerFactory.getLogger(SqlHelper.class);
    /**
     * sqlite连接对象
     */
    private Connection connection;
    /**
     * sql陈述对象
     */
    private Statement statement;
    /**
     * 结果集合对象
     */
    private ResultSet resultSet;
    /**
     * 数据库文件路径
     */
    private final String dbFilePath;
    /**
     * 单例sqlHelper对象
     */
    private static SqlHelper sqlHelper;

    /**
     * 单例构造SqlHelper对象
     * @param dbFilePath 传入一个数据表文件路径
     * @return 返回一个sqlHelper对象
     */
    public static SqlHelper getInstance(String dbFilePath) {
        if (Objects.isNull(sqlHelper)) {
            synchronized (SqlHelper.class){
                try {
                    sqlHelper = new SqlHelper(dbFilePath);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return sqlHelper;
    }
    /**
     * 构造函数
     * @param dbFilePath sqlite db 文件路径
     */
    private SqlHelper(String dbFilePath) throws ClassNotFoundException, SQLException {
        this.dbFilePath = dbFilePath;
        connection = getConnection(dbFilePath);
    }
    /**
     * 获取数据库连接
     * @param dbFilePath db文件路径
     * @return 数据库连接
     */
    public Connection getConnection(String dbFilePath) throws ClassNotFoundException, SQLException {
        Connection conn;
        // 1、加载驱动
        Class.forName("org.sqlite.JDBC");
        // 2、建立连接
        // 注意：此处有巨坑，如果后面的 dbFilePath 路径太深或者名称太长，则建立连接会失败
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
        return conn;
    }

    /**
     * 执行SQL查询
     * @param sql sql select 语句
     * @param rse 结果集处理类对象
     * @return 查询结果
     */
    public <T> T executeQuery(String sql, ResultSetExtractor<T> rse) throws SQLException, ClassNotFoundException {
        try {
            resultSet = getStatement().executeQuery(sql);
            return rse.extractData(resultSet);
        } finally {
            destroyed();
        }
    }
    /**
     * 执行select查询，返回结果列表
     *
     * @param sql sql select 语句
     * @param rm 结果集的行数据处理类对象
     * @return 返回一个查询的结果列表，返回的结果是抽象的
     */
    public <T> List<T> executeQuery(String sql, RowMapper<T> rm) throws SQLException, ClassNotFoundException {
        List<T> rsList = new ArrayList<>();
        try {
            resultSet = getStatement().executeQuery(sql);
            while (resultSet.next()) {
                rsList.add(rm.mapRow(resultSet, resultSet.getRow()));
            }
        } finally {
            destroyed();
        }
        return rsList;
    }
    /**
     * 执行数据库更新sql语句
     * @param sql sql语句
     * @return 更新行数

     */
    public int executeUpdate(String sql) throws SQLException, ClassNotFoundException {
        try {
            return getStatement().executeUpdate(sql);
        } finally {
            destroyed();
        }
    }
    /**
     * 执行多个sql更新语句
     * @param sqlStr sql更新语句
     */
    public void executeUpdate(String...sqlStr) throws SQLException, ClassNotFoundException {
        try {
            for (String sql : sqlStr) {
                getStatement().executeUpdate(sql);
            }
        } finally {
            destroyed();
        }
    }
    /**
     * 执行数据库更新 sql List
     * @param sqlList sql列表
     */
    public void executeUpdate(List<String> sqlList) throws SQLException, ClassNotFoundException {
        try {
            for (String sql : sqlList) {
                getStatement().executeUpdate(sql);
            }
        } finally {
            destroyed();
        }
    }
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        if (null == connection) {
            connection = getConnection(dbFilePath);
        }
        return connection;
    }
    private Statement getStatement() throws SQLException, ClassNotFoundException {
        if (null == statement) {
            statement = getConnection().createStatement();
        }
        return statement;
    }
    /**
     * 数据库资源关闭和释放
     */
    public void destroyed() {
        try {
            if (null != connection) {
                connection.close();
                connection = null;
            }
            if (null != statement) {
                statement.close();
                statement = null;
            }
            if (null != resultSet) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            log.error("sqlite数据库关闭时异常 {}", e.getMessage());
        }
    }
    /**
     * 是否自动提交事务
     */
    public void setAutoCommit(Boolean status) throws SQLException {
        connection.setAutoCommit(status);
    }

}
