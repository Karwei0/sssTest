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
import java.sql.SQLException;

@WebServlet("/user_changeaddress")
public class UserChangeAddressServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User)request.getSession().getAttribute("user");
        String s = "";
        try {
            BeanUtils.copyProperties(u, request.getParameterMap());
            s = uService.updateUserAddress(u);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(s.contains("6")){
            request.setAttribute("failMsg", "收件人不能是数字");
        }
        if(s.contains("7")){
            request.setAttribute("failMsg", "电话只能是数字，且长度在7-12");
        }
        if(s.contains("8")){
            request.setAttribute("failMsg", "收件地址不能全是数字");
        }
        if("0".equals(s)){
            request.setAttribute("msg", "收件信息更新成功！");
        }
        request.getRequestDispatcher("/user_center.jsp").forward(request, response);

    }
}
