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

@WebServlet("/admin/order_status")
public class AdminOrderStatusServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        int status = 0;
        Page page = null;
        if(request.getParameter("id") != null){
            id = Integer.parseInt(request.getParameter("id"));
        }
        if(request.getParameter("status") != null){
            status = Integer.parseInt(request.getParameter("status"));
        }
        try {
            System.out.println(oService.updateStatus(id, status));
            page = oService.selectAdminOrderList(status, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("p", page);
        request.getRequestDispatcher("/admin/order_list").forward(request, response);
    }
}
