package com.bankingapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.model.Admin_login;
import com.bankingapp.dao.Admin_loginDAO;

@WebServlet("/adminlogin")
public class Admin_login_servlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    private Admin_loginDAO loginDao;

    public void init() {
        loginDao = new Admin_loginDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//    	boolean is_authenticated=false;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin_login loginBean = new Admin_login();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {
//            	is_authenticated=true;
                HttpSession session = request.getSession();
                session.setAttribute("admin",username);
                response.sendRedirect("AdminDashboard.jsp");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                request.setAttribute("loginError","Incorrect password");
                response.sendRedirect("AdminLogin.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}