package com.wzh086.servlet;

import com.wzh086.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/type_delete")
public class AdminTypeDeleteServlet extends HttpServlet {
    private TypeService tService = new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tid = Integer.parseInt(request.getParameter("id"));
        boolean success = false;
        try {
            success = tService.delete(tid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(success){
            request.setAttribute("msg", "删除成功");
        }else{
            request.setAttribute("failMsg", "类目下包含商品，无法直接删除类目！");
        }
        request.getRequestDispatcher("/admin/type_list").forward(request, response);
    }
}
