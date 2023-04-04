package com.dev.createaccount;

//import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


@WebServlet("/addAccountServlet")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public CreateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }
    private Connection conn;
    
    public void init() {
      String url = "jdbc:mysql://localhost:3306/BankAccounts";
      String user = "root";
      String password = "root";
      try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
      }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String accountType = request.getParameter("accountType");
	    String accountHolderName = request.getParameter("accountHolderName");
	    String accountHolderContact = request.getParameter("accountHolderContact");
	    double accountBalance = Double.parseDouble(request.getParameter("accountBalance"));
	    String accountSetupDate = request.getParameter("accountSetupDate");
	    
	    try {
	      String sql = "INSERT INTO Accounts (AccountType, AccountHolderName, AccountHolderContact, AccountBalance, AccountSetupDate) VALUES (?, ?, ?, ?, ?)";
	      PreparedStatement statement = conn.prepareStatement(sql);
	      statement.setString(1, accountType);
	      statement.setString(2, accountHolderName);
	      statement.setString(3, accountHolderContact);
	      statement.setDouble(4, accountBalance);
	      statement.setString(5, accountSetupDate);
	      int rowsInserted = statement.executeUpdate();
	      if (rowsInserted > 0) {
	        System.out.println("A new account was inserted successfully!");
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    response.sendRedirect("viewAccount");
				
	}

}
