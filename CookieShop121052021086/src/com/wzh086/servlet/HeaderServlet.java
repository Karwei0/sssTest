package com.wzh086.servlet;

import com.wzh086.model.Type;
import com.wzh086.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/HeaderServlet")
public class HeaderServlet extends HttpServlet {
    TypeService tService = new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> typeList = null;
        try {
            typeList = tService.getTypeList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("typeList", typeList);
        request.getRequestDispatcher("/header.jsp").forward(request, response);

    }
}
