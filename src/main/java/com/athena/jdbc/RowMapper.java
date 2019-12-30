package com.athena.jdbc;

import java.sql.ResultSet;

/*
 * @Author xiangxz
 * @Description ORM映射定制化接口
 * @Date 9:20 PM 2019/12/6
 **/

public interface RowMapper<T> {
    /*
     * @Author xiangxz
     * @Description 行与对象映射
     * @Date 5:39 PM 2019/12/2
     * @Param
     * @return
     **/

    T mapRow(ResultSet set, int rowNum) throws Exception;
}
