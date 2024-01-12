package com.wzh086.dao;

import com.wzh086.model.Type;
import com.wzh086.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TypeDao {
    public List<Type> getTypeList() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM t_type;";
        return runner.query(sql, new BeanListHandler<Type>(Type.class));
    }

    public Type getTypeById(int typeid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM t_type WHERE id=?";
        return runner.query(sql, new BeanHandler<Type>(Type.class), typeid);
    }

    public void update(Type t) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update t_type set name=? where id=?";
        runner.update(sql, t.getName(), t.getId());
    }

    public boolean delete(int tid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from t_type where id=?";
        int row =runner.update(sql, tid);
        return row > 0;
    }

    public boolean insert(String name) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from t_type where name=?";
        int row = runner.query(sql, new ScalarHandler<Long>(), name).intValue();
        if(row > 0){
            return false;
        }
        sql = "insert into t_type(name) values(?)";
        row = runner.update(sql, name);
        return row > 0;
    }
}
