package com.wzh086.servlet;

import com.wzh086.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/goods_recommend")
public class AdminGoodsRecommendServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String method = request.getParameter("method");
        int typeTarget = Integer.parseInt(request.getParameter("typeTarget"));
        int type = 0;
        int pageNumber = 1;
        boolean success = false;
        if(request.getParameter("type") != null){
            type = Integer.parseInt(request.getParameter("type"));
        }
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }

        if("add".equals(method)){
            try {
                success = gService.addToRecommend(id, typeTarget);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if("remove".equals(method)){
            try {
                success = gService.removeFromRecommend(id, typeTarget);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(success){
            request.setAttribute("msg", "修改成功");
        }else{
            request.setAttribute("failMsg", "修改失败");
        }
        request.setAttribute("type", type);
        request.setAttribute("pageNumber", pageNumber);
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }
}
