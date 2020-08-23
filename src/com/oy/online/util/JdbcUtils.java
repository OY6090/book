package com.oy.online.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
*@Description JDBC工具类
*@Author OY
*@Date 2020/8/11
*@Time 17:56
*/
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();
    static{
        try {
            Properties properties = new Properties();
            // 读取jdbc.properties 属性配置文件
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(is);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null，说明获取连接失败，有值就是获取连接成功
     */
    public static Connection getConnection(){
        Connection conn = conns.get();
           if(conn == null){
               try {
                   // 从数据库连接池获取使用
                   conn = dataSource.getConnection();
                   // 保存到ThreadLocal 对象中,供后面的jdbc 使用
                   conns.set(conn);
                   // 设置手动管理事务
                   conn.setAutoCommit(false);
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }
           }
        return conn;
    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection != null){
            try {
                connection.commit(); // 提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        // 一定要执行remove 操作，否则就会出错。（因为 Tocat 服务器底层使用了线程池技术）
        conns.remove();
    }
    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection != null){
            try {
                connection.rollback(); // 回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，结束资源
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        // 一定要执行remove 操作，否则就会出错。（因为 Tocat 服务器底层使用了线程池技术）
        conns.remove();
    }

    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
   /* public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }*/
}
