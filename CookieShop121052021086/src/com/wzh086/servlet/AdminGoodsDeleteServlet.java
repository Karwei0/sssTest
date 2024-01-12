package com.wzh086.servlet;

import com.wzh086.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/goods_delete")
public class AdminGoodsDeleteServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if(request.getParameter("id") != null){
            id = Integer.parseInt(request.getParameter("id"));
        }
        boolean success = false;
        try {
            success = gService.deleteGoods(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(success){
            request.setAttribute("msg", "删除成功");
        }else{
            request.setAttribute("failMsg", "该物品已有订单，无法删除");
        }
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }
}
