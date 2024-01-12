package com.wzh086.dao;

import com.wzh086.model.User;
import com.wzh086.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserDao {
    public void addUser(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into " +
                "t_user(username, email, password, name, phone, address, isadmin," +
                "isvalidate) values(?,?,?,?,?,?,?,?)";
        runner.update(sql, user.getUsername(), user.getEmail(), user.getPassword(),
                user.getName(), user.getPhone(), user.getAddress(), user.isIsadmin(), user.isIsvalidate());
    }
    public boolean isUsernameExit(String username) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from t_user where username=?;";

        Object obj = runner.query(sql, new ScalarHandler<>(1), username);
        int res = Integer.parseInt(String.valueOf(obj));
        if(res >= 1){
            return true;
        }
        return false;
    }

    public boolean isEmailExit(String email) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from t_user where email=?;";
        Object obj = runner.query(sql, new ScalarHandler<>(1), email);
        int res = Integer.parseInt(String.valueOf(obj));
        System.out.println(email);
        if(res != 0){
            return true;
        }
        return false;
    }

    public User getUserByUEAndPassWord(String ue, String password) throws SQLException {
        String sql = "select * from t_user where password=? and (username=? or email=?);";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<>(User.class), password, ue, ue);
    }
    public boolean updateUserAddress(User u) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update t_user set name=?, phone=?, address=? where id=?";
        int row = runner.update(sql, u.getName(), u.getPhone(), u.getAddress(), u.getId());
        return row > 0;
    }

    public boolean updateUserPwd(User u) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update t_user set password=? where id=?";
        int row = runner.update(sql, u.getPassword(), u.getId());
        return row > 0;
    }

    public List<User> getUserList(int pageNumber, int pageSize) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from t_user limit ?, ?;";
        return runner.query(sql , new BeanListHandler<>(User.class), (pageNumber - 1) * pageSize, pageSize);
    }

    public int getUserListTotalCount() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from t_user;";
        return runner.query(sql, new ScalarHandler<Long>()).intValue();
    }

    public boolean delete(int uid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from t_user where id=?;";
        int row = runner.update(sql, uid);
        return row > 0;
    }

    public boolean reset(User u) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update t_user set password=? where id=?";
        int row = runner.update(sql, u.getPassword(), u.getId());
        return row > 0;
    }

    public User getUserById(int uid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from t_user where id=?;";
        return runner.query(sql, new BeanHandler<>(User.class), uid);
    }
}
