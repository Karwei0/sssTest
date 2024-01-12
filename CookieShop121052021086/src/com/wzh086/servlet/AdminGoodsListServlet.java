package com.wzh086.servlet;

import com.wzh086.model.Goods;
import com.wzh086.model.Page;
import com.wzh086.service.GoodsService;

import javax.naming.InsufficientResourcesException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/goods_list")
public class AdminGoodsListServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page p = null;
        int pageNumber = 1;
        int type = 0;
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        if(request.getParameter("type") != null){
            type = Integer.parseInt(request.getParameter("type"));
        }
        try {
            p = gService.selectAdminGoodsList(type, pageNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("type", type);
        request.setAttribute("p", p);
        request.getRequestDispatcher("/admin/goods_list.jsp").forward(request, response);
    }
}
