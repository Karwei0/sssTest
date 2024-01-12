package com.wzh086.servlet;

import com.wzh086.model.User;
import com.wzh086.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user_login")
public class UserLoginServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ue = request.getParameter("ue");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = uService.getUserByUEAndPassWord(ue, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user == null){
            request.setAttribute("failMsg", "用户名/邮箱或密码错误");
            request.getRequestDispatcher("/user_login.jsp").forward(request, response);
        }else{
            request.getSession().setAttribute("user", user);
            if(user.isIsadmin()){
                response.sendRedirect("/admin/index.jsp");
                return;
            }else{
                String referer = request.getHeader("Referer");
                String serverName = request.getServerName();
                System.out.println(referer);
                if(referer != null && referer.contains(serverName) && !referer.contains("login")){
                    response.sendRedirect(referer);
                    //request.getRequestDispatcher("/user_center.jsp").forward(request, response);
                    return;
                }else{
                    //System.out.println("h11hh");
                    response.sendRedirect("/user_center.jsp");
                    //request.getRequestDispatcher("/user_center.jsp").forward(request, response);
                    return;
                }
            }

        }

    }
}
