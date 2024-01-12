package com.wzh086.servlet;

import com.wzh086.model.Goods;
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
import java.util.List;
import java.util.Map;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    private TypeService tService = new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> scrollGoods = null;
        List<Map<String, Object>> hotList = null;
        List<Map<String, Object>> newList = null;
        List<Type> typeList = null;
        try {
            scrollGoods = gService.getScrollGoods();
            hotList = gService.getHotList();
            newList = gService.getNewList();
            typeList = tService.getTypeList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("scroll", scrollGoods);
        request.setAttribute("hotList", hotList);
        request.setAttribute("newList", newList);
        request.setAttribute("typeList", typeList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
