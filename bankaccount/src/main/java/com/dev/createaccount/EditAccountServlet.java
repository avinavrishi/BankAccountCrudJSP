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
import java.sql.PreparedStatement;

/**
 * Servlet implementation class EditAccountServlet
 */
@WebServlet("/editAccount")
public class EditAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // get account number from request parameter
        String accountId = request.getParameter("accountId");
       

        // create connection to database
        String url = "jdbc:mysql://localhost:3306/BankAccounts";
        String user = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            // get account details from database
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts WHERE AccountID=" + accountId);
            
            
            // print account details
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Edit Account</title>");
            out.println("<style>/* Style for the form elements */\r\n"
            		+ "form label {\r\n"
            		+ "  display: block;\r\n"
            		+ "  margin-bottom: 10px;\r\n"
            		+ "}\r\n"
            		+ "\r\n"
            		+ "form input[type=\"text\"],\r\n"
            		+ "form input[type=\"number\"],\r\n"
            		+ "form input[type=\"date\"],\r\n"
            		+ "form select {\r\n"
            		+ "  padding: 8px;\r\n"
            		+ "  border: none;\r\n"
            		+ "  border-radius: 3px;\r\n"
            		+ "  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);\r\n"
            		+ "  margin-bottom: 10px;\r\n"
            		+ "  width: 100%;\r\n"
            		+ "}\r\n"
            		+ "\r\n"
            		+ "form input[type=\"submit\"] {\r\n"
            		+ "  padding: 10px;\r\n"
            		+ "  border: none;\r\n"
            		+ "  background-color: #2196F3;\r\n"
            		+ "  color: #fff;\r\n"
            		+ "  border-radius: 3px;\r\n"
            		+ "  cursor: pointer;\r\n"
            		+ "}\r\n"
            		+ "\r\n"
            		+ "form input[type=\"submit\"]:hover {\r\n"
            		+ "  background-color: #0c7cd5;\r\n"
            		+ "}\r\n"
            		+ "\r\n"
            		+ "/* Style for the page title */\r\n"
            		+ "h1 {\r\n"
            		+ "  font-size: 36px;\r\n"
            		+ "  text-align: center;\r\n"
            		+ "  margin-top: 50px;\r\n"
            		+ "  margin-bottom: 20px;\r\n"
            		+ "}\r\n"
            		+ "\r\n"
            		+ "/* Style for the container */\r\n"
            		+ ".container {\r\n"
            		+ "  max-width: 700px;\r\n"
            		+ "  margin: 0 auto;\r\n"
            		+ "  padding: 0 20px;\r\n"
            		+ "}\r\n"
            		+ "</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edit Account</h1>");
            out.println("<form method='POST'>");
            while(rs.next()) {
            	String accountType = rs.getString("AccountType");
                String accountHolderName = rs.getString("AccountHolderName");
                String accountHolderContact = rs.getString("AccountHolderContact");
                double accountBalance = rs.getDouble("AccountBalance");
                String accountSetupDate = rs.getString("AccountSetupDate");
                out.println("<input type=\"hidden\" name=\"accountId\" value=\"" + accountId + "\">");
                
                out.println("<label for=\"accountType\">Account Type:</label>");
                out.println("<select id=\"accountType\" name=\"accountType\">");
                out.println("<option value=\"Checking\" " + (accountType.equals("Checking") ? "selected" : "") + ">Checking</option>");
                out.println("<option value=\"Saving\" " + (accountType.equals("Saving") ? "selected" : "") + ">Saving</option>");
                out.println("</select><br><br>");
                out.println("<label for=\"accountHolderName\">Account Holder Name:</label>");
                out.println("<input type=\"text\" id=\"accountHolderName\" name=\"accountHolderName\" value=\"" + accountHolderName + "\" required><br><br>");
                
                out.println("<label for=\"accountHolderContact\">Account Holder Contact:</label>");
                out.println("<input type=\"text\" id=\"accountHolderContact\" name=\"accountHolderContact\" value=\"" + accountHolderContact + "\" required><br><br>");
                
                out.println("<label for=\"accountBalance\">Account Balance:</label>");
                out.println("<input type=\"number\" id=\"accountBalance\" name=\"accountBalance\" value=\"" + accountBalance + "\" required><br><br>");
                
                out.println("<label for=\"accountSetupDate\">Account Setup Date:</label>");
                out.println("<input type=\"date\" id=\"accountSetupDate\" name=\"accountSetupDate\" value=\"" + accountSetupDate + "\" required><br><br>");
                out.println("<input type=\"submit\" value=\"Update Account\">");
            }
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        

        // get account details from request parameters
        String accountId = request.getParameter("accountId");
        String accountType = request.getParameter("accountType");
        String accountHolderName = request.getParameter("accountHolderName");
        String accountHolderContact = request.getParameter("accountHolderContact");
        double accountBalance = Double.parseDouble(request.getParameter("accountBalance"));
        String accountSetupDate = request.getParameter("accountSetupDate");

        // create connection to database
        String url = "jdbc:mysql://localhost:3306/BankAccounts";
        String user = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            // update account details in database
            PreparedStatement stmt = conn.prepareStatement("UPDATE Accounts SET AccountType=?, AccountHolderName=?, AccountHolderContact=?, AccountBalance=?, AccountSetupDate=? WHERE AccountID=?");
            stmt.setString(1, accountType);
            stmt.setString(2, accountHolderName);
            stmt.setString(3, accountHolderContact);
            stmt.setDouble(4, accountBalance);
            stmt.setString(5, accountSetupDate);
            stmt.setString(6, accountId);
            stmt.executeUpdate();

            // redirect to account details page
            response.sendRedirect("viewAccount");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}