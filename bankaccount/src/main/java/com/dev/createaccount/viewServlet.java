package com.dev.createaccount;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class viewServlet
 */
@WebServlet("/viewAccount")
public class viewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    
	    // create connection to database
	    String url = "jdbc:mysql://localhost:3306/BankAccounts";
	    String user = "root";
	    String password = "root";
	    Connection conn = null;
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      conn = DriverManager.getConnection(url, user, password);
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    String sql = "SELECT * FROM Accounts";
	    try {
	      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      // display records in table format
	      out.println("<html>");
	      out.println("<head><title>Bank Account Information</title><style>\r\n"
	      		+ "		table {\r\n"
	      		+ "			border-collapse: collapse;\r\n"
	      		+ "			width: 100%;\r\n"
	      		+ "		}\r\n"
	      		+ "\r\n"
	      		+ "		th, td {\r\n"
	      		+ "			text-align: left;\r\n"
	      		+ "			padding: 8px;\r\n"
	      		+ "			border-bottom: 1px solid #ddd;\r\n"
	      		+ "		}\r\n"
	      		+ "\r\n"
	      		+ "		tr:hover {\r\n"
	      		+ "			background-color: #f5f5f5;\r\n"
	      		+ "		}\r\n"
	      		+ "\r\n"
	      		+ "		th {\r\n"
	      		+ "			background-color: #4CAF50;\r\n"
	      		+ "			color: white;\r\n"
	      		+ "		}\r\n"
	      		+ "	</style></head>");
	      out.println("<body>");
	      out.println("<h1>Bank Account Information</h1>");
	      out.println("<table>");
	      out.println("<tr><th>Account ID</th><th>Account Type</th><th>Account Holder Name</th><th>Account Holder Contact</th><th>Account Balance</th><th>Account Setup Date</th><th>Edit</th><th>Delete</th></tr>");
	      while (rs.next()) {
	        int accountId = rs.getInt("AccountId");
	        String accountType = rs.getString("AccountType");
	        String accountHolderName = rs.getString("AccountHolderName");
	        String accountHolderContact = rs.getString("AccountHolderContact");
	        double accountBalance = rs.getDouble("AccountBalance");
	        String accountSetupDate = rs.getString("AccountSetupDate");
	        out.println("<tr><td>" + accountId + "</td><td>" + accountType + "</td><td>" + accountHolderName + "</td><td>" + accountHolderContact + "</td><td>" + accountBalance + "</td><td>" + accountSetupDate + "</td>");
	        out.println("<td><a href='editAccount?accountId=" + accountId + "'>Edit</a></td>");
	        out.println("<td><a href='deleteAccount?accountId=" + accountId + "'>Delete</a></td></tr>");
	      }
	      out.println("</table>");
	      out.println("<br><br><a href='addAccount.jsp'>Add Account</a>");
	      out.println("<br><br><a href='index.jsp'>Home</a>");
	      out.println("</body>");
	      out.println("</html>");
	      
	      // close resources
	      rs.close();
	      stmt.close();
	      conn.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	}

}
