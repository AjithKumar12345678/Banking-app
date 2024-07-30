package com.bankingapp.servlet;

import com.bankingapp.dao.Customer_loginDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WithdrawServlet")

public class Withdraw_servlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
		HttpSession session = request.getSession();
        Integer accountNo = (Integer) session.getAttribute("accountNo");
        
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		
		Customer_loginDAO customerDAO = new Customer_loginDAO();
		boolean sucsess = customerDAO.withdraw(accountNo,amount);
		
		if(sucsess) {
			response.getWriter().println("Withdrawl Sucessfull");
		}
		else {
			response.getWriter().println("Withdrawl Failed!");
		}
	}
	
}
