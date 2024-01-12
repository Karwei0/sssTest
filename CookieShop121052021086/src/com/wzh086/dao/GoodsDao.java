package com.wzh086.dao;

import com.wzh086.model.Goods;
import com.wzh086.model.Page;
import com.wzh086.model.Type;
import com.wzh086.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsDao {
    public List<Map<String, Object>> getScrollGoods() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT t_goods.id, name, cover, image1, image2, price, intro, stock, type_id FROM T_GOODS, T_RECOMMEND WHERE t_recommend.type=1 and t_goods.id=t_recommend.goods_id";
        return runner.query(sql, new MapListHandler());
    }

    public List<Map<String, Object>> getHotList() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT t_goods.id, name, cover, image1, image2, price, intro, stock, type_id FROM T_GOODS, T_RECOMMEND WHERE t_recommend.type=2 and t_goods.id=t_recommend.goods_id;";
        return  runner.query(sql, new MapListHandler());
    }

    public List<Map<String, Object>> getNewList() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT t_goods.id, name, cover, image1, image2, price, intro, stock, type_id FROM T_GOODS, T_RECOMMEND WHERE t_recommend.type=3 and t_goods.id=t_recommend.goods_id;";
        return  runner.query(sql, new MapListHandler());
    }
    public Goods getGoodsById(int goodsid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from t_goods g where g.id=?;";
        Goods goods = runner.query(sql, new BeanHandler<>(Goods.class), goodsid);
        Type type = null;
        sql = "select t.id, t.name from t_goods g, t_type t where t.id=g.type_id and g.id=?";
        type = runner.query(sql, new BeanHandler<>(Type.class), goodsid);
        goods.setType(type);
        return goods;
    }
    public List<Goods> getAllGoodsList() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from t_goods;";
        return runner.query(sql, new BeanListHandler<>(Goods.class));
    }
    //根据type选择goods
    public List<Goods> getGoodsListByTypeId(int typeid, int pageNumber, int pageSize) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = null;
        if(typeid == 0){
            sql = "select * from t_goods limit ?, ?;";
            return runner.query(sql, new BeanListHandler<>(Goods.class), (pageNumber - 1)*pageSize, pageSize);
        }else{
            sql = "select * from t_goods where type_id=? limit ?, ?;";
        }
        return runner.query(sql, new BeanListHandler<>(Goods.class), typeid, (pageNumber - 1)*pageSize, pageSize);
    }
    //获得type对应的总条目
    public int getGoodListTotalCountByTypeId(int typeid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = null;
        if(typeid == 0){
            sql = "select count(*) from t_goods;";
            return  runner.query(sql, new ScalarHandler<Long>()).intValue();
        }else{
            sql = "select count(*)  from t_goods where type_id=?;";
        }
        return  runner.query(sql, new ScalarHandler<Long>(), typeid).intValue();
    }


    //推荐信息查询商品列表
    public List<Goods> getGoodsListByRecommendType(int type, int pageNumber, int pageSize) throws SQLException {
        String sql = "select b.* from t_recommend a, t_goods b where a.goods_id = b.id and a.type=? limit ?, ?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<>(Goods.class), type, (pageNumber - 1)*pageSize, pageSize);
    }
    //查总条目
    public int getGoodListTotalCountByRecommendType(int type) throws SQLException {
        String sql = "select count(b.id) from t_recommend a, t_goods b where a.goods_id = b.id and a.type=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return  runner.query(sql, new ScalarHandler<Long>(), type).intValue();
    }
    //获取搜索的goods
    public List<Goods> getSearchGoodsList(String keyword, int pageNumber, int pageSize) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from t_goods where name like ? limit ?, ?;";
        return runner.query(sql, new BeanListHandler<>(Goods.class), "%"+keyword+"%", (pageNumber - 1) * pageNumber, pageSize);
    }
    //获取搜索的总条目
    public int getSearchGoodsListTotalCount(String keyword) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from t_goods where name like ?;";
        return runner.query(sql, new ScalarHandler<Long>(), "%"+keyword+"%").intValue();
    }

    public int getAdminGoodsListTotalCount(int recommend_type) throws SQLException {
        String sql = "select count(a.id) from t_goods a;";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        if(recommend_type == 0){
            return runner.query(sql, new ScalarHandler<Long>()).intValue();
        }
        sql = "select count(a.id) from t_goods a left join t_recommend b on b.goods_id=a.id where b.type=?;";
        return runner.query(sql ,new ScalarHandler<Long>(), recommend_type).intValue();
    }

    public List<Goods> selectAdminGoodsList(int recommend_type, int pageNumber, int pageSize) throws SQLException {
        String sql = "select a.*, c.name as typeName, sum(case when b.type=1 then 100 when b.type=2 then 20 when b.type=3 then 3 else 0 end) as recommend_type_str " +
                "from t_goods a " +
                "left join t_recommend b on b.goods_id=a.id " +
                "left join t_type c on a.type_id=c.id " +
                "where b.type = ? " +
                "group by a.id " +
                "order by a.id limit ?, ?;";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        if(recommend_type == 0){
            sql =  "select a.*, c.name as typeName, sum(case when b.type=1 then 100 when b.type=2 then 20 when b.type=3 then 3 else 0 end) as recommend_type_str " +
                    "from t_goods a " +
                    "left join t_recommend b on b.goods_id=a.id " +
                    "left join t_type c on a.type_id=c.id " +
                    "group by a.id " +
                    "order by a.id limit ?, ?;";
            return runner.query(sql, new BeanListHandler<>(Goods.class), (pageNumber - 1) * pageSize, pageSize);
        }
        return runner.query(sql, new BeanListHandler<>(Goods.class), recommend_type, (pageNumber - 1) * pageSize, pageSize);
    }

    public boolean addToRecommend(int goodsid, int recommend_type) throws SQLException {
        String sql = "insert into t_recommend(type, goods_id) values(?, ?);";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        int row = runner.update(sql, recommend_type, goodsid);
        return row > 0;
    }

    public boolean removeFromRecommend(int goodsid, int recommend_type) throws SQLException {
        String sql = "delete from t_recommend where goods_id=? and type=?;";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        int row = runner.update(sql, goodsid, recommend_type);
        return row > 0;
    }

    public boolean addGoods(Goods goods) throws SQLException {
        String sql = "insert into t_goods(name, cover, image1, image2, price, intro, stock, type_id) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?);";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        int row = runner.update(sql, goods.getName(), goods.getCover(), goods.getImage1(), goods.getImage2(),
                goods.getPrice(), goods.getIntro(), goods.getStock(), goods.getType_id());
        return  row > 0;
    }

    public boolean deleteGoods(int goodsid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from t_orderitem where goods_id=?";
        int row = runner.query(sql, new ScalarHandler<Long>(), goodsid).intValue();
        if(row > 0){
            return false;
        }
        sql = "delete from t_goods where id=?;";
        row = runner.update(sql, goodsid);
        return row > 0;
    }

    public boolean update(Goods goods) throws SQLException {
        String sql = "update t_goods set name=?, cover=?, image1=?, image2=?, price=?, intro=?, stock=?, type_id=? where id=?;";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        System.out.println((goods.getName() + " " + goods.getCover() + " " + goods.getImage1() + " " + goods.getImage2() + " " + goods.getPrice() + " " + goods.getIntro() + " " + goods.getStock() + " " + goods.getType_id() + " " + goods.getId()));

        int row = runner.update(sql, goods.getName(), goods.getCover(), goods.getImage1(), goods.getImage2(), goods.getPrice(), goods.getIntro(), goods.getStock(), goods.getType_id(), goods.getId());
        return row > 0;
    }
}
