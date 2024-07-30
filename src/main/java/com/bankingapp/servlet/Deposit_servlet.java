package com.bankingapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankingapp.dao.Customer_loginDAO;

@WebServlet("/DepositServlet")
public class Deposit_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer accountNo = (Integer) session.getAttribute("accountNo");

        double amount = Double.parseDouble(request.getParameter("amount"));

        Customer_loginDAO customerDAO = new Customer_loginDAO();
        boolean success = customerDAO.deposit(accountNo, amount);

        if (success) {
            response.getWriter().println("Deposit successful!");
        } else {
            response.getWriter().println("Deposit failed!");
        }
    }
}
