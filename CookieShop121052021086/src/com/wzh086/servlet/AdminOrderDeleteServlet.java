package com.wzh086.servlet;

import com.wzh086.model.Page;
import com.wzh086.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/order_delete")
public class AdminOrderDeleteServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        int pageNumber = 0;
        int status = 0;
        Page page = null;
        boolean success = false;
        if(request.getParameter("id") != null){
            id = Integer.parseInt(request.getParameter("id"));
        }
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        if(request.getParameter("status") != null){
            status = Integer.parseInt(request.getParameter("status"));
        }

        try {
            success = oService.delete(id);
            page = oService.selectAdminOrderList(status, pageNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(success){
            request.setAttribute("msg", "删除成功");
        }else{
            request.setAttribute("failMsg", "删除失败");
        }
        request.setAttribute("p", page);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/admin/order_list").forward(request, response);

    }
}
