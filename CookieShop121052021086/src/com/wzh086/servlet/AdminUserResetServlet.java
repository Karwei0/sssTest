package com.wzh086.servlet;

import com.wzh086.model.User;
import com.wzh086.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/user_reset")
public class AdminUserResetServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = -1;
        String username = null;
        String email = null;
        String pwd = null;
        boolean success = false;
        if(request.getParameter("id") != null){
            uid = Integer.parseInt(request.getParameter("id"));
        }
        if(request.getParameter("username") != null){
            username = request.getParameter("username");
        }
        if(request.getParameter("email") != null){
            email = request.getParameter("email");
        }
        if(request.getParameter("password") != null){
            pwd = request.getParameter("password");
        }
        User user = new User();
        user.setId(uid);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(pwd);
        try {
            success = uService.reset(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(success){
            request.setAttribute("msg", "密码重置成功");
        }else{
            request.setAttribute("failMsg", "重置密码强度过低");
        }
        request.getRequestDispatcher("/admin/user_list").forward(request, response);
    }
}
