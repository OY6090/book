package com.oy.online.test;

import com.oy.online.util.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilTest {

    @Test
    public void testJdbcUtils(){
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
    }
}
