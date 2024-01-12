package com.wzh086.servlet;

import com.wzh086.model.Goods;
import com.wzh086.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/goods_detail")
public class GoodsDetailServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int goodsid = 0;
        Goods goods = null;
        if(request.getParameter("id") != null && !"".equals(request.getParameter("id"))){
            goodsid = Integer.parseInt(request.getParameter("id"));
        }
        try {
            goods = goodsService.getGoodsById(goodsid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("g", goods);
        request.getRequestDispatcher("/goods_detail.jsp").forward(request, response);
    }
}
