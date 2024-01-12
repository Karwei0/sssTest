package com.wzh086.servlet;

import com.wzh086.model.Goods;
import com.wzh086.model.Order;
import com.wzh086.service.GoodsService;

import javax.print.MultiDocPrintService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/goods_buy")
public class GoodsBuyServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order o = null;
        if(request.getSession().getAttribute("order") != null){
            o = (Order)request.getSession().getAttribute("order");
        }else{
            o = new Order();
            request.getSession().setAttribute("order", o);
        }
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        Goods goods = null;
        try {
            goods = gService.getGoodsById(goodsid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(goods.getStock() > 0){
            o.addGoods(goods);
            response.getWriter().write("ok");
        }else{
            response.getWriter().write("fail");
        }
    }
}
