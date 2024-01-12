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

@WebServlet("/user_register")
public class UserRegisterServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = new User();
        try {
            BeanUtils.copyProperties(u, request.getParameterMap());
            System.out.println(u.getPassword());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        request.setAttribute("u", u);
        if(u.getPassword() == null){
            response.sendRedirect("/user_center.jsp");
            return;
        }
        if("0".equals(uService.register(u))){
            request.setAttribute("msg", "注册成功，请登录");
            request.getRequestDispatcher("/user_login.jsp").forward(request, response);
            return;
        }
        if(uService.register(u).contains("1")){
            request.setAttribute("failUserNameMsg", "用户名重复");
            //request.getRequestDispatcher("/user_register.jsp").forward(request, response);
        }
        if(uService.register(u).contains("2")){
            request.setAttribute("failEmailMsg", "邮箱重复");
            //request.getRequestDispatcher("/user_register.jsp").forward(request, response);
        }
        if(uService.register(u).contains("3")){
            request.setAttribute("failUserNameMsg", "用户名必须以非数字开头");
            //request.getRequestDispatcher("/user_register.jsp").forward(request, response);
        }
        if(uService.register(u).contains("4")){
            request.setAttribute("failEmailMsg", "不符合邮箱格式");
            //request.getRequestDispatcher("/user_register.jsp").forward(request, response);
        }
        if(uService.register(u).contains("5")){
            request.setAttribute("failPwdMsg", "密码强度过低");
            //request.getRequestDispatcher("/user_register.jsp").forward(request, response);
        }
        if(uService.register(u).contains("6")){
            request.setAttribute("failNameMsg", "收件人不能是数字");
            //request.getRequestDispatcher("/user_register.jsp").forward(request, response);
        }
        if(uService.register(u).contains("7")){
            request.setAttribute("failPhoneMsg", "电话只能是数字，且长度在7-12");
            //request.getRequestDispatcher("/user_register.jsp").forward(request, response);
        }
        if(uService.register(u).contains("8")){
            request.setAttribute("failAddressMsg", "收件地址不能全是数字*");
            //request.getRequestDispatcher("/user_register.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/user_register.jsp").forward(request, response);

    }
}
