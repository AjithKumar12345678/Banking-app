package com.bankingapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banking.model.Admin_login;
import com.banking.model.Customer_login;

public class Admin_loginDAO {
	
	private static String jdbcURL = "jdbc:mysql://localhost:3306/db1?allowPublicKeyRetrieval=true&useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "ajith123";
    
    private static final String SELECT_CUSTOMER_SQL = "SELECT * FROM Customer";
    private static final String EDIT_CUSTOMER_SQL = "SELECT * FROM Customer WHERE acccount_no = ?";
    
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean validate(Admin_login loginBean) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = getConnection();

            PreparedStatement preparedStatement = connection
            .prepareStatement("select * from admin_login where user_name = ? and password_admin = ? ")) {
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

   
    
    public List<Customer_login> getCustomerDetails(){
    	List<Customer_login> customers = new ArrayList<>();
    	try(Connection connection = getConnection();
    			PreparedStatement CustomerStmt = connection.prepareStatement(SELECT_CUSTOMER_SQL)){
    		ResultSet rs = CustomerStmt.executeQuery();
    		
    		while(rs.next()) {
    			int account_no = rs.getInt("account_no");
    			String fullName = rs.getString("full_name");
    			String address = rs.getString("address");
    			String mobileNo = rs.getString("mobile_no");
    			String email_id = rs.getString("email_id");
    			String account_type = rs.getString("account_type");
    			String id_proof = rs.getString("id_proof");
    			
    			Customer_login customer = new Customer_login(account_no,fullName,address,mobileNo,email_id,account_type,id_proof);
                customers.add(customer);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return customers;
    }
    
    public List<Customer_login> getCustomerDetails(int accountNo){
    	List<Customer_login> customers = new ArrayList<>();
    	
    	try(Connection connection = getConnection();
    			PreparedStatement EditStmt = connection.prepareStatement(EDIT_CUSTOMER_SQL)){
    		EditStmt.setInt(1, accountNo);
    		ResultSet rs = EditStmt.executeQuery();
    		
    		if(rs.next()) {
    			String fullName = rs.getString("full_name");
    			String address = rs.getString("address");
    			String mobileNo = rs.getString("mobile_no");
    			String email_id = rs.getString("email_id");
    			String account_type = rs.getString("account_type");
    			String id_proof = rs.getString("id_proof");
    			
    			Customer_login customer = new Customer_login(fullName,address,mobileNo,email_id,account_type,id_proof);
    			customers.add(customer);
    		}
    	
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
		return customers;
    	
    }
}