package com.wzh086.servlet;

import com.wzh086.model.Page;
import com.wzh086.model.Type;
import com.wzh086.service.GoodsService;
import com.wzh086.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/goods_list")
public class GoodsListServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    private TypeService tService = new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        int typeid = 0;
        /*if(request.getParameter("typeid") != null &&  !"".equals(request.getParameter("typeid"))){
            typeid = Integer.parseInt(request.getParameter("typeid"));
        }*/
        if(request.getParameter("typeid") != null &&  !"".equals(request.getParameter("typeid"))){
            typeid = Integer.parseInt(request.getParameter("typeid"));
        }
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        Page page = null;
        Type type = null;
        try {
            page = gService.selectGoodsListByTypeId(typeid, pageNumber);
            type = tService.getTypeById(typeid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("p", page);
        request.setAttribute("t", type);
        request.setAttribute("id", typeid);
        request.getRequestDispatcher("/goods_list.jsp").forward(request, response);

    }
}
