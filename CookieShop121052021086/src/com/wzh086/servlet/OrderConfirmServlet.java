package com.wzh086.servlet;

import com.wzh086.model.Order;
import com.wzh086.model.User;
import com.wzh086.service.OrderService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@WebServlet("/order_confirm")
public class OrderConfirmServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            try {
                BeanUtils.copyProperties(user, request.getParameterMap());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            request.setAttribute("user", user);
            if(!"".equals(user.getPhone()) && !user.getPhone().matches("^\\d{7,12}$")){
                request.setAttribute("failMsg", "电话只能是数字，且长度在7-12");
                request.getRequestDispatcher("/order_submit.jsp").forward(request, response);
                return;
            }
            if(user.getName().matches("^\\d+$")){
                request.setAttribute("failMsg", "收件人不能是数字");
                request.getRequestDispatcher("/order_submit.jsp").forward(request, response);
                return;
            }
            if(user.getAddress().matches("^\\d+$")){
                request.setAttribute("failMsg", "地址不能全是数字");
                request.getRequestDispatcher("/order_submit.jsp").forward(request, response);
                return;
            }
            int paytype = 0;
            if(request.getParameter("paytype") != null){
                paytype = Integer.valueOf(request.getParameter("paytype"));
            }
            if(request.getSession().getAttribute("order") != null){
                Order order = (Order)request.getSession().getAttribute("order");
                order.setName(user.getName());
                order.setAddress(user.getAddress());
                order.setDatetime(new Date());
                order.setPaytype(paytype);
                order.setPhone(user.getPhone());
                order.setStatus(2);
                order.setUser_id(user.getId());
                if(!oService.addOrder(order)){
                    request.setAttribute("failMsg", "有商品数量大于库存，无法提交！");
                    request.getRequestDispatcher("/order_success.jsp").forward(request, response);
                    return;
                }else{
                    request.getSession().removeAttribute("order");
                    request.setAttribute("msg", "成功");
                    request.getRequestDispatcher("/order_success.jsp").forward(request, response);
                }

            }
        }
    }
}
