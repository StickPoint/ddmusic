package com.stickpoint.ddmusic.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description: ResultSetExtractor
 *
 * @ClassName : ResultSetExtractor
 * @Date 2022/11/23 9:42
 * @Author fntp
 * @PackageName com.stickpoint.ddmusic.common.mapper
 */
public interface ResultSetExtractor<T> {

    /**
     * 此方法专用于处理批处理后产生的结果集
     * 对产生的数据集合进行筛选处理加工操作最后返回所需要的数据结构的对象
     * @param rs 传入一个结果集
     * @return 返回一个最终确定的类型对象
     * @throws SQLException 在处理的过程中抛出一个sql处理异常
     */
    T extractData(ResultSet rs) throws SQLException;
}
