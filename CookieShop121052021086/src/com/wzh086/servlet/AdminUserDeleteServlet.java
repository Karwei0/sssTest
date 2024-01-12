package com.wzh086.servlet;

import com.wzh086.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/user_delete")
public class AdminUserDeleteServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean success = false;
        try {
            success = uService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(success) {
            request.setAttribute("msg", "客户删除成功");
        }else {
            request.setAttribute("failMsg", "客户有下的订单，请先删除该客户下的订单，再来删除客户！");
        }
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
    }
}
