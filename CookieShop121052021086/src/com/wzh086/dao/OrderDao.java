package com.wzh086.dao;

import com.wzh086.model.Order;
import com.wzh086.model.OrderItem;
import com.wzh086.model.Page;
import com.wzh086.model.User;
import com.wzh086.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.lang.model.element.QualifiedNameable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    public List<Order> SelectAll(int userid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        if(userid != 0){
            String sql = "select * from t_order where user_id=? order by datetime desc;";
            return runner.query(sql, new BeanListHandler<>(Order.class), userid);
        }
        String sql = "select * from t_order, t_user u where user_id=u.id order by datetime desc;";
        return runner.query(sql, new BeanListHandler<>(Order.class));
    }
    public List<OrderItem> selectAllItem(int orderid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select i.id,i.price,i.amount,g.name from t_orderitem i, t_goods g where order_id=? and i.goods_id=g.id;";
        return runner.query(sql, new BeanListHandler<>(OrderItem.class), orderid);
    }

    public boolean addOrder(Connection conn, Order order) throws SQLException {
        String sql = "INSERT INTO t_order" +
                "(total, amount, status, paytype, name, phone, address, datetime, user_id)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        int rows = runner.update(conn, sql, order.getTotal(), order.getAmount(), order.getStatus(), order.getPaytype(),
                order.getName(), order.getPhone(), order.getAddress(), order.getDatetime(), order.getUser_id());

        return rows>0;
    }
    public int getLastInsertOrderId(Connection conn) throws SQLException {
        String sql = "select last_insert_id();";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(conn, sql, new ScalarHandler<BigInteger>()).intValue();
    }
    public boolean addOrderItem(Connection conn, OrderItem item) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select stock from t_goods where id=?;";
        int stock = runner.query(sql, new ScalarHandler<Integer>(), item.getGoods_id());
        if(stock < item.getAmount()){
            return false;
        }
        sql = "INSERT INTO t_orderitem(`price`, `amount`, `goods_id`, `order_id`)" +
                "VALUES(?, ?, ?, ?)";
        int rows = runner.update(conn, sql, (int)item.getPrice(), item.getAmount(), item.getGoods().getId(), item.getOrder_id());
        return rows > 0;
    }
    public int getAdminOrderListTotalCount(int status) throws SQLException {
        String sql = "select count(id) from t_order;";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        if(status == 0){
            return runner.query(sql, new ScalarHandler<Long>()).intValue();
        }
        sql = "select count(id) from t_order where status=?;";
        return runner.query(sql ,new ScalarHandler<Long>(), status).intValue();
    }

    public List<Order> getAdminOrderList(int status, int pageNumber, int pageSize) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        List<Order> orderList = null;
        if(status == 0){
            String sql = "select a.* from t_order a order by a.datetime desc, id desc limit ?, ?;";
            orderList = runner.query(sql, new BeanListHandler<>(Order.class), (pageNumber - 1) * pageSize, pageSize);
        }else{
            String sql = "select a.* from t_order a, t_user b where a.user_id=b.id and status=? limit ?, ?;";
            orderList = runner.query(sql, new BeanListHandler<>(Order.class), status, (pageNumber - 1) * pageSize, pageSize);
        }
        String sql = "select * from t_user where id=?;";
        String sql1 = "select i.id,i.price,i.amount,g.name from t_orderitem i, t_goods g where order_id=? and i.goods_id=g.id;";
        for(Order order : orderList){
            User u = runner.query(sql, new BeanHandler<>(User.class), order.getUser_id());
            order.setUser(u);
            List<OrderItem> itemsList = runner.query(sql1, new BeanListHandler<>(OrderItem.class), order.getId());
            order.setItemList(itemsList);
        }
        return orderList;
    }

    public boolean updateStatus(int orderid, int status) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update t_order set status=? where id=?";
        int row = runner.update(sql, status, orderid);
        return row > 0;
    }

    public boolean delete(Connection conn, int orderid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from t_order where id=?";
        int row = runner.update(conn, sql, orderid);
        return row > 0;
    }
    public boolean deleteItem(Connection conn, int orderid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from t_orderitem where order_id=?";
        int row = runner.update(conn, sql, orderid);
        return row > 0;
    }
    public boolean updateStock(OrderItem item) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update t_goods set stock=stock - ? where id=?";
        int row = runner.update(sql, item.getAmount(), item.getGoods_id());
        return row > 0;
    }
}
