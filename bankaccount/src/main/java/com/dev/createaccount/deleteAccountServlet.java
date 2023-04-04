package com.dev.createaccount;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;

/**
 * Servlet implementation class viewServlet
 */
@WebServlet("/deleteAccount")
public class deleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
       
		int accountId = Integer.parseInt(request.getParameter("accountId"));
	
		
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
		String sql = "DELETE FROM Accounts WHERE AccountId=" + accountId;
		try {
			Statement stmt = conn.createStatement();
			int rowsDeleted = stmt.executeUpdate(sql);
			
			if (rowsDeleted > 0) {
				response.sendRedirect("viewAccount");
			} else {
				response.getWriter().println("<h1>Error deleting account</h1>");
			}
			
			// close resources
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
