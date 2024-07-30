package com.bankingapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.model.Customer_login;
import com.bankingapp.dao.Customer_loginDAO;

@WebServlet("/LoginServlet")
public class Login_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountNo = Integer.parseInt(request.getParameter("accountNo"));
        String password = request.getParameter("password");

        Customer_loginDAO customerDAO = new Customer_loginDAO();
        Customer_login customer = customerDAO.authenticateCustomer(accountNo, password);

        if (customer != null) {
        	HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            session.setAttribute("accountNo", accountNo);
            
            if (customerDAO.checkfirstLogin(accountNo)) {
                response.sendRedirect("CustomerSetupPassword.jsp");
            } else {
            	
                response.sendRedirect("CustomerDashboard.jsp");
            }
        } else {
            response.getWriter().println("Login failed!");
        }
    }
}
