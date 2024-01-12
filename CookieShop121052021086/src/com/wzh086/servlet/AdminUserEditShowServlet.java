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

@WebServlet("/admin/user_editshow")
public class AdminUserEditShowServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = -1;
        User u = new User();
        if(request.getParameter("id") != null && !"".equals(request.getParameter("id"))){
            uid = Integer.parseInt(request.getParameter("id"));
        }
        try {
            u = uService.getUserById(uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("u", u);
        request.getRequestDispatcher("/admin/user_edit.jsp").forward(request, response);

    }
}
