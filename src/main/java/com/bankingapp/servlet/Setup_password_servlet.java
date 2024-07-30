package com.bankingapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankingapp.dao.Customer_loginDAO;

@WebServlet("/SetupPasswordServlet")
public class Setup_password_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountNo = Integer.parseInt(request.getParameter("accountNo"));
        String tempPassword = request.getParameter("tempPassword");
        String newPassword = request.getParameter("newPassword");
        
        Customer_loginDAO customerDAO = new Customer_loginDAO();
        boolean success = customerDAO.setupNewPassword(accountNo, tempPassword, newPassword);

        if (success) {
        	customerDAO.changeLoginStatus(accountNo);
            response.getWriter().println("Password setup successful!");
            
        } else {
            response.getWriter().println("Password setup failed!");
        }
    }
}
