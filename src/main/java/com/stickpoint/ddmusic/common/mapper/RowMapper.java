package com.stickpoint.ddmusic.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description: RowMapper
 *
 * @ClassName : RowMapper
 * @Date 2022/11/23 9:41
 * @Author puye(0303)
 * @PackageName com.stickpoint.ddmusic.common.mapper
 */
public interface RowMapper<T> {

    /**
     * 行操作
     * @param rs 结果集
     * @param index 下标
     * @return 返回一个经过筛选之后被确认的正常的对象
     * @throws SQLException 抛出一个执行过程中可能出现的sql异常
     */
    T mapRow(ResultSet rs, int index) throws SQLException;
}
