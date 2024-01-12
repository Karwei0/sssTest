package com.wzh086.service;

import com.mysql.cj.util.StringUtils;
import com.wzh086.dao.GoodsDao;
import com.wzh086.model.Goods;
import com.wzh086.model.Order;
import com.wzh086.model.Page;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsService {
    private GoodsDao gDao = new GoodsDao();
    public List<Map<String, Object>> getScrollGoods() throws SQLException {
        List<Map<String, Object>> scrollGoods = gDao.getScrollGoods();
        return scrollGoods;
    }

    public List<Map<String, Object>> getHotList() throws SQLException {
        List<Map<String, Object>> hotList = gDao.getHotList();
        return hotList;
    }

    public List<Map<String, Object>> getNewList() throws SQLException {
        List<Map<String, Object>> newList = gDao.getHotList();
        return newList;
    }
    public List<Goods> getAllGoodsList() throws SQLException {
        return gDao.getAllGoodsList();
    }
    //获得type的商品列表
    public Page selectGoodsListByTypeId(int typeid, int pageNumber) throws SQLException {
        Page page = new Page();
        int pageSize = 8;
        page.setPageNumber(pageNumber);
        int totalCount = gDao.getGoodListTotalCountByTypeId(typeid);
        page.setPageSizeAndTotalCount(pageSize, totalCount);
        List<Goods> list = gDao.getGoodsListByTypeId(typeid, pageNumber, pageSize);
        page.setList(list);
        return page;
    }

    //获得recommendtype的商品列表
    public Page selectGoodsListByRecommendType(int type, int pageNumber) throws SQLException {
        Page page = new Page();
        int pageSize = 8;
        page.setPageNumber(pageNumber);
        int totalCount = gDao.getGoodListTotalCountByRecommendType(type);

        page.setPageSizeAndTotalCount(pageSize, totalCount);
        List<Goods> list = gDao.getGoodsListByRecommendType(type, pageNumber, pageSize);
        page.setList(list);
        return page;
    }

    //获得搜索的条目
    public Page selectSearchGoodsList(String keyword, int pageNumber) throws SQLException {
        Page page = new Page();
        int pageSize = 8;
        page.setPageNumber(pageNumber);
        int totalCount = gDao.getSearchGoodsListTotalCount(keyword);
        page.setPageSizeAndTotalCount(pageSize, totalCount);
        List<Goods> list = gDao.getSearchGoodsList(keyword, pageNumber, pageSize);
        page.setList(list);
        return page;
    }
    public Goods getGoodsById(int goodsid) throws SQLException {
        Goods goods = gDao.getGoodsById(goodsid);
        return goods;
    }


    public Page selectAdminGoodsList(int recommend_type, int pageNumber) throws SQLException {
        Page page = new Page();
        page.setPageNumber(pageNumber);
        int pageSize = 8;
        int totalCount = gDao.getAdminGoodsListTotalCount(recommend_type);
        page.setPageSizeAndTotalCount(pageSize, totalCount);
        List list = gDao.selectAdminGoodsList(recommend_type, pageNumber, pageSize);
        page.setList(list);
        return page;
    }

    public boolean addToRecommend(int goodsid, int recommend_type) throws SQLException {
        return gDao.addToRecommend(goodsid, recommend_type);
    }
    public boolean removeFromRecommend(int goodsid, int recommend_type) throws SQLException {
        return gDao.removeFromRecommend(goodsid, recommend_type);
    }

    /**
     * 1:price<=0
     * 2:stock<0*/
    public String addGoods(Goods goods) throws SQLException {
        StringBuilder sb = new StringBuilder();

        if(goods.getPrice() <= 0){
            sb.append("1");
        }
        if(goods.getStock() < 0){
            sb.append("2");
        }
        if(sb.toString() == null || "".equals(sb.toString())){
            gDao.addGoods(goods);
            return "0";
        }
        return sb.toString();
    }

    public boolean deleteGoods(int goodsid) throws SQLException {
        return gDao.deleteGoods(goodsid);
    }

    public String update(Goods goods) throws SQLException {
        StringBuilder sb = new StringBuilder();

        if(goods.getPrice() <= 0){
            sb.append("1");
        }
        if(goods.getStock() < 0){
            sb.append("2");
        }
        if(sb.toString() == null || "".equals(sb.toString())){
            gDao.update(goods);
            return "0";
        }
        return sb.toString();
    }
}
