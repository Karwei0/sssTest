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

@WebServlet("/admin/goods_editshow")
public class AdminGoodsEditShowServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        Goods goods = null;
        int pageNumber = 1;
        int type = 0;
        if(request.getParameter("id") != null){
            id = Integer.parseInt(request.getParameter("id"));
        }else if(request.getAttribute("g") != null){
            Goods goods1 = (Goods)request.getAttribute("g");
            id = goods1.getId();
        }
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        if(request.getParameter("type") != null){
            type = Integer.parseInt(request.getParameter("type"));
        }
        try {
            goods = gService.getGoodsById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("g", goods);
        request.setAttribute("pageNumber", String.valueOf(pageNumber));
        request.setAttribute("type", String.valueOf(type));
        request.getRequestDispatcher("/admin/goods_edit.jsp").forward(request, response);
    }
}
