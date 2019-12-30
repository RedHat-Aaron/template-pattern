package com.athena.jdbc.dao;

import com.athena.jdbc.JdbcTemplete;
import com.athena.jdbc.Member;
import com.athena.jdbc.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 * @Author: xiaoxiang.zhang
 * @Description:成员数据访问类
 * @Date: Create in 10:15 PM 2019/12/6
 */
public class MemberDAO extends JdbcTemplete {

    public MemberDAO(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> selectAll() {
        String sql = "select * from t_member";
        return super.excuteQuery(sql, new RowMapper<Member>() {
            public Member mapRow(ResultSet res, int rowNum) throws Exception {
                Member m = new Member();
                m.setUserName(res.getString("userName"));
                m.setPassword(res.getString("password"));
                m.setAge(res.getInt("age"));
                m.setNickName(res.getString("nickName"));
                m.setAddr(res.getString("addr"));
                return m;
            }
        }, null);
    }
}
