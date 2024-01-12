package com.wzh086.servlet;

import com.wzh086.model.Page;
import com.wzh086.model.User;
import com.wzh086.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/admin/user_edit")
public class AdminUserEditServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = new User();
        String s = "";
        try {
            BeanUtils.copyProperties(u, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
        try {
            s = uService.updateUserAddress(u);//同一个方法
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("u", u);
        if(s.contains("0") || "".equals(s)){
            request.setAttribute("msg", "修改成功");
            request.getRequestDispatcher("/admin/user_list").forward(request, response);
            return;
        }
        if(s.contains("6")){
            request.setAttribute("failMsg", "收件人不能是数字");
            request.getRequestDispatcher("/admin/user_editshow").forward(request, response);
        }
        if(s.contains("7")){
            request.setAttribute("failMsg", "电话只能是数字，且长度在7-12");
            request.getRequestDispatcher("/admin/user_editshow").forward(request, response);
        }
        if(s.contains("8")){
            request.setAttribute("failMsg", "收件地址不能全是数字*");
            request.getRequestDispatcher("/admin/user_editshow").forward(request, response);
        }
    }
}
