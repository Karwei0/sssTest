package com.wzh086.servlet;

import com.wzh086.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/type_add")
public class AdminTypeAddServlet extends HttpServlet {
    private TypeService tService = new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        boolean success = false;
        try {
            success = tService.insert(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(success){
            request.setAttribute("msg", "添加成功");
        }else{
            request.setAttribute("failMsg", "类目名已经存在");
        }
        request.getRequestDispatcher("/admin/type_list").forward(request, response);
    }
}
