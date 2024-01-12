package com.wzh086.service;

import com.wzh086.dao.OrderDao;
import com.wzh086.model.Order;
import com.wzh086.model.OrderItem;
import com.wzh086.model.Page;
import com.wzh086.utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDao oDao = new OrderDao();

    public boolean addOrder(Order order) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = DataSourceUtils.getConnection();
            conn.setAutoCommit(false);
            oDao.addOrder(conn, order);
            int orderid = oDao.getLastInsertOrderId(conn);
            order.setId(orderid);
            for (OrderItem item : order.getItemMap().values()) {
                item.setOrder_id(orderid);
                flag = oDao.addOrderItem(conn, item);

            }
            if(flag){
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        //提交成功，修改库存
        System.out.println(flag);
        if (flag) {
            for (OrderItem item : order.getItemMap().values()) {
                try {
                    oDao.updateStock(item);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public List<Order> selectAll(int userid) throws SQLException {
        List<Order> list = null;
        list = oDao.SelectAll(userid);
        for (Order o : list) {
            List<OrderItem> items = oDao.selectAllItem(o.getId());
            o.setItemList(items);
        }
        return list;
    }

    public Page selectAdminOrderList(int status, int pageNumber) throws SQLException {
        Page page = new Page();
        page.setPageNumber(pageNumber);
        int pageSize = 8;
        int totalCount = oDao.getAdminOrderListTotalCount(status);
        page.setPageSizeAndTotalCount(pageSize, totalCount);
        List list = oDao.getAdminOrderList(status, pageNumber, pageSize);
        page.setList(list);

        return page;
    }

    public boolean updateStatus(int orderid, int status) throws SQLException {
        return oDao.updateStatus(orderid, status);
    }

    public boolean delete(int orderid) throws SQLException {
        Connection con = null;
        boolean flag = true;
        try {
            con = DataSourceUtils.getDataSource().getConnection();
            con.setAutoCommit(false);
            oDao.deleteItem(con, orderid);
            oDao.delete(con, orderid);
            con.commit();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
            if (con != null){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return flag;
    }
}
