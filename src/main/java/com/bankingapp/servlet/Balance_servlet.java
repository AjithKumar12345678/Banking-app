package com.bankingapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankingapp.dao.Customer_loginDAO;

@WebServlet("/BalanceServlet")

public class Balance_servlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Integer accountno = (Integer) session.getAttribute("accountNo");
		
		Customer_loginDAO customer = new Customer_loginDAO();
		double balance = customer.balance(accountno);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
        out.print("{\"balance\":" + balance + "}");
        out.flush();
	}
}