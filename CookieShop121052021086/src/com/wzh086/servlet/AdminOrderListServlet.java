package com.wzh086.servlet;

import com.wzh086.model.Order;
import com.wzh086.model.Page;
import com.wzh086.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/order_list")
public class AdminOrderListServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page p = null;
        int pageNumber = 1;
        int status = 0;
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        if(request.getParameter("status") != null){
            status = Integer.parseInt(request.getParameter("status"));
        }
        try {
            p = oService.selectAdminOrderList(status, pageNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("status", status);
        request.setAttribute("p", p);

        request.getRequestDispatcher("/admin/order_list.jsp").forward(request, response);
    }
}
