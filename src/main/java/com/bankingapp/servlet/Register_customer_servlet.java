package com.bankingapp.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.model.Customer_login;
import com.bankingapp.dao.Customer_loginDAO;

@WebServlet("/RegisterCustomerServlet")
public class Register_customer_servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");

        String tempPassword = generateTemporaryPassword();

        Customer_login customer = new Customer_login(fullName, address, mobileNo, emailId, accountType, initialBalance, dob, idProof, tempPassword);

        Customer_loginDAO customerDAO = new Customer_loginDAO();
        int accountNo = customerDAO.registerCustomer(customer);

        if (accountNo != -1) {

            response.getWriter().println("Customer registered successfully!");
            response.getWriter().println("Account No: " + accountNo);
            response.getWriter().println("Temporary Password: " + tempPassword);
        } else {
            response.getWriter().println("Customer registration failed!");
        }
    }

    private String generateTemporaryPassword() {
        Random random = new Random();
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder tempPassword = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            tempPassword.append(characters.charAt(index));
        }

        return tempPassword.toString();
    }
}
