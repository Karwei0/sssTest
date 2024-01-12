package com.wzh086.service;

import com.wzh086.dao.TypeDao;
import com.wzh086.model.Type;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TypeService {
    private TypeDao tDao = new TypeDao();
    public List<Type> getTypeList() throws SQLException {
        List<Type> typeList = tDao.getTypeList();
        return typeList;
    }

    public Type getTypeById(int typeid) throws SQLException {
        Type t = tDao.getTypeById(typeid);
        return t;
    }

    public void update(Type t) throws SQLException {
        tDao.update(t);
    }

    public boolean delete(int tid) throws SQLException {
        return tDao.delete(tid);
    }

    public boolean insert(String name) throws SQLException {
        return tDao.insert(name);
    }
}
