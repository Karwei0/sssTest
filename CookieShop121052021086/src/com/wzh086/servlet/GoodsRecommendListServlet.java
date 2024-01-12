package com.wzh086.servlet;

import com.wzh086.model.Page;
import com.wzh086.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/goodsrecommend_list")
public class GoodsRecommendListServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        int type = Integer.valueOf(request.getParameter("type"));
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
        }
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        Page page = null;
        try {
            page = gService.selectGoodsListByRecommendType(type, pageNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("p", page);
        request.setAttribute("t", type);
        request.getRequestDispatcher("/goodsrecommend_list.jsp").forward(request, response);
    }
}
