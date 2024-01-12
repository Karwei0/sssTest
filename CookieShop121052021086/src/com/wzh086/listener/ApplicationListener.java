package com.wzh086.listener;

import com.wzh086.model.Type;
import com.wzh086.service.TypeService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private TypeService tService = new TypeService();
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        List<Type> list = null;
        try {
            list = tService.getTypeList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        servletContextEvent.getServletContext().setAttribute("typeList", list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver = null;
        while(drivers.hasMoreElements()){
            driver = drivers.nextElement();

            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
