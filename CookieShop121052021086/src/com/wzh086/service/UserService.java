package com.wzh086.service;

import com.wzh086.dao.UserDao;
import com.wzh086.model.Page;
import com.wzh086.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao uDao= new UserDao();
    public String register(User u){
        /*
        * 0：成功
        * 1：用户名重复
        * 2：邮箱重复
        * 3：用户名必须以非数字开头
        * 4：不符合邮箱格式
        * 5：密码长度在6-15位
        * 6：收件人不能是数字
        * 7：电话只能是数字，且长度在7-12（调查过）
        * 8：收件地址不能全是数字*/
        StringBuilder sb = new StringBuilder();
        try {
            if (uDao.isUsernameExit(u.getUsername())) {
                sb.append("1");
            }
            if (uDao.isEmailExit(u.getEmail())){
                sb.append("2");
            }
            if(u.getUsername().matches("^\\d+") || "".equals(u.getUsername())){
                sb.append("3");
            }
            if(!u.getEmail().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
                sb.append("4");
            }
            if(!u.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,15}$")){
                sb.append("5");
            }
            if(u.getName().matches("^\\d+$") && (!"".equals(u.getName()) || u.getName() != null)){
                sb.append("6");
            }
            if(!"".equals(u.getPhone()) && !u.getPhone().matches("^\\d{7,12}$")){
                sb.append("7");
            }
            if(u.getAddress().matches("^\\d+$") && (!"".equals(u.getAddress()) || u.getAddress() != null)){
                sb.append("8");
            }
            if(sb.toString() == null || "".equals(sb.toString())){
                uDao.addUser(u);
                return "0";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public User getUserByUEAndPassWord(String ue, String password) throws SQLException {
        User user = uDao.getUserByUEAndPassWord(ue, password);
        return user;
    }

    public String updateUserAddress(User u) throws SQLException {
        StringBuilder sb = new StringBuilder();
        if(u.getName().matches("^\\d+$") && (!"".equals(u.getName()) || u.getName() != null)){
            sb.append("6");
        }
        if(!"".equals(u.getPhone()) && !u.getPhone().matches("^\\d{7,12}$")){
            sb.append("7");
        }
        if(u.getAddress().matches("^\\d+$") && (!"".equals(u.getAddress()) || u.getAddress() != null)){
            sb.append("8");
        }
        if(sb.toString() == null || "".equals(sb.toString())){
            uDao.updateUserAddress(u);
            return "0";
        }
        return sb.toString();
    }

    public boolean updateUserPwd(User u) throws SQLException {
        return uDao.updateUserPwd(u);
    }

    public Page getUserList(int pageNumber) throws SQLException {
        Page page = new Page();
        int pageSize = 10;
        int totalCount = uDao.getUserListTotalCount();
        List<User> list = uDao.getUserList(pageNumber, pageSize);
        page.setPageNumber(pageNumber);
        page.setPageSizeAndTotalCount(pageSize, totalCount);
        page.setList(list);
        return page;
    }

    public boolean delete(int uid) throws SQLException {
        return uDao.delete(uid);
    }

    public boolean reset(User u) throws SQLException {
        if(!u.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,15}$")){
            return false;
        }
        return uDao.reset(u);
    }

    public User getUserById(int uid) throws SQLException {
        return uDao.getUserById(uid);
    }
}
