package com.wzh086.servlet;

import com.wzh086.model.Type;
import com.wzh086.service.TypeService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/type_edit")
public class AdminTypeEditServlet extends HttpServlet {
    private TypeService tService = new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Type t = new Type();
        try {
            BeanUtils.copyProperties(t, request.getParameterMap());
            tService.update(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("msg", "修改成功");
        request.getRequestDispatcher("/admin/type_list").forward(request, response);
    }
}
