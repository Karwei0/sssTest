package com.wzh086.servlet;

import com.wzh086.model.Page;
import com.wzh086.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/user_list")
public class AdminUserListServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        Page page = null;
        try {
            page = uService.getUserList(pageNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("p", page);
        request.getRequestDispatcher("/admin/user_list.jsp").forward(request, response);
    }
}
