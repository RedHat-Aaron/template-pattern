package com.athena.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiaoxiang.zhang
 * @Description:Jdbc模版类
 * @Date: Create in 9:21 PM 2019/12/6
 */
public abstract class JdbcTemplete {
    private DataSource dataSource;

    public JdbcTemplete(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> excuteQuery(String sql, RowMapper<?> rowMapper, Object[] values) {
        try {
            //贾廉预执事
            //1.加载注册驱动(Spring已经做好了)
            //2.拿到数据库链接
            Connection con = this.getConnection();
            //3.创建预编译语句对象
            PreparedStatement ps = this.createPreparedStatement(con, sql);
            //4.执行sql
            ResultSet res = this.executeQuery(ps, values);
            //5.解析结果集
            List<?> result = this.parseResultSet(res, rowMapper);
            //6.释放链接
            this.closeResource(con, ps, res);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @return java.sql.Connection
     * @Author xiangxz
     * @Description 获取链接对象
     * @Date 9:46 PM 2019/12/6
     * @Param []
     */
    protected Connection getConnection() throws Exception {
        return this.dataSource.getConnection();
    }

    /**
     * @return java.sql.PreparedStatement
     * @Author xiangxz
     * @Description 获取语句预编译对象
     * @Date 9:49 PM 2019/12/6
     * @Param [connection, sql]
     */
    protected PreparedStatement createPreparedStatement(Connection connection, String sql) throws Exception {
        return connection.prepareStatement(sql);
    }

    /**
     * @return java.sql.ResultSet
     * @Author xiangxz
     * @Description 获取返回结果集
     * @Date 9:52 PM 2019/12/6
     * @Param [ps, params]
     */
    protected ResultSet executeQuery(PreparedStatement ps, Object[] params) throws Exception {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i, params[i]);
        }
        return ps.executeQuery();
    }

    /**
     * @return java.util.List<?>
     * @Author xiangxz
     * @Description 自定义解析结果集
     * @Date 9:53 PM 2019/12/6
     * @Param [res, rowMapper]
     */
    protected List<?> parseResultSet(ResultSet res, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<Object>();
        int rowNum = 1;
        while (res.next()) {
            result.add(rowMapper.mapRow(res, rowNum++));
        }
        return result;
    }

    ;

    /**
     * @return void
     * @Author xiangxz
     * @Description 关闭资源对象
     * @Date 9:56 PM 2019/12/6
     * @Param [con, ps, res]
     */
    protected void closeResource(Connection con, PreparedStatement ps, ResultSet res) {
        try {
            if (null != res) {
                res.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ps) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    //这里如果是数据库链接池，就不用关闭链接，而是归还链接
                    if (null != con) {
                        con.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
