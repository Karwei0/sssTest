package com.wzh086.servlet;

import com.wzh086.model.Page;
import com.wzh086.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/goods_search")
public class GoodsSearchServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = null;
        int pageNumber = 1;
        if(request.getParameter("keyword") != null && !"".equals(request.getParameter("keyword"))){
            keyword = request.getParameter("keyword");
        }
        Page page = null;
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        try {
            page = gService.selectSearchGoodsList(keyword, pageNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("p", page);
        request.setAttribute("keyword", keyword);//要记得设置keyword
        request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
    }
}
