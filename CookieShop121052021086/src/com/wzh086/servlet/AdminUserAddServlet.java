package com.wzh086.servlet;

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

@WebServlet("/admin/user_add")
public class AdminUserAddServlet extends HttpServlet {
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
        }
        try {
            s = uService.register(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("u", u);
        if("0".equals(s)){
            request.setAttribute("msg", "添加成功");
            request.getRequestDispatcher("/admin/user_list").forward(request, response);
            return;
        }
        if(s.contains("1")){
            request.setAttribute("failMsg", "用户名重复");
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }
        if(s.contains("2")){
            request.setAttribute("failMsg", "邮箱重复");
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }
        if(uService.register(u).contains("3")){
            request.setAttribute("failMsg", "用户名必须以非数字开头");
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }
        if(uService.register(u).contains("4")){
            request.setAttribute("failMsg", "不符合邮箱格式");
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }
        if(uService.register(u).contains("5")){
            request.setAttribute("failMsg", "密码强度过低");
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }
        if(uService.register(u).contains("6")){
            request.setAttribute("failMsg", "收件人不能是数字");
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }
        if(uService.register(u).contains("7")){
            request.setAttribute("failMsg", "电话只能是数字，且长度在7-12");
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }
        if(uService.register(u).contains("8")){
            request.setAttribute("failMsg", "收件地址不能全是数字*");
            request.getRequestDispatcher("/admin/user_add.jsp").forward(request, response);
        }


    }
}
