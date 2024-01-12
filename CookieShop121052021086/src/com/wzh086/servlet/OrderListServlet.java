package com.wzh086.servlet;

import com.wzh086.model.Order;
import com.wzh086.model.User;
import com.wzh086.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/order_list")
public class OrderListServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = null;
        request.removeAttribute("order");
        if(request.getSession().getAttribute("user") != null){
            u = (User)request.getSession().getAttribute("user");
        }else{
            response.sendRedirect("/");
            return;
        }
        List<Order> list = null;
        try {
            list = oService.selectAll(u.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("orderList", list);
        request.getRequestDispatcher("/order_list.jsp").forward(request,  response);

    }
}
