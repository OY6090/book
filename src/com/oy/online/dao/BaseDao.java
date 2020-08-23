package com.oy.online.dao;

import com.oy.online.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    // 使用DbUtils 操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     *  update() 方法来执行： Insert\Update\Delete 语句
     * @param sql
     * @param args
     * @return 如果返回-1，执行失败，返回其他表示影响的行数
     */
    public int update(String sql, Object...args){
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.update(conn, sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     *  查询返回一个javaBean 的sql 语句
     * @param type 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql, new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    /**
     *  查询返回多个javaBean 的sql语句
     * @param type 返回的类型
     * @param sql 执行的sql语句
     * @param args sql 对应的参数值
     * @param <T> 返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    /**
     *  执行一行一列的 sql 语句
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }
}
