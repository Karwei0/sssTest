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

@WebServlet("/user_changepwd")
public class UserChangePwdServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pwd = request.getParameter("password");
        String newPwd = request.getParameter("newPassword");
        User user = (User)request.getSession().getAttribute("user");
        if(!newPwd.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,15}$")){
            request.setAttribute("failMsg", "修改失败，新密码强度过低");
        }else if(user.getPassword().equals(pwd)){
            user.setPassword(newPwd);
            try {
                uService.updateUserPwd(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("msg", "修改成功！");
        }else{
            request.setAttribute("failMsg", "修改失败，原密码不正确");
        }
        request.getRequestDispatcher("/user_center.jsp").forward(request, response);
    }
}
