package com.wzh086.utils;

import com.wzh086.dao.GoodsDao;
import com.wzh086.dao.TypeDao;
import com.wzh086.dao.UserDao;

import java.sql.SQLException;

public class TestDB {

    public static void main(String[] args) throws SQLException {
        GoodsDao goodsDao = new GoodsDao();
        TypeDao typeDao = new TypeDao();
        System.out.println(goodsDao.getScrollGoods());
        System.out.println(typeDao.getTypeList());
    }
}
