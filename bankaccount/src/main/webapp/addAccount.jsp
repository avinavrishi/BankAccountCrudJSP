<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Bank Account Information</title>
    <style>body {
  font-family: Arial, sans-serif;
  background-color: #f2f2f2;
}

h1 {
  text-align: center;
  color: #333;
}

form {
  max-width: 500px;
  margin: auto;
  background-color: #fff;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

label {
  display: block;
  margin-bottom: 10px;
  font-weight: bold;
}

input[type="text"],
input[type="number"],
select,
input[type="date"] {
  width: 100%;
  padding: 10px;
  border-radius: 3px;
  border: 1px solid #ccc;
  box-sizing: border-box;
  margin-bottom: 20px;
  font-size: 16px;
}

input[type="submit"] {
  background-color: #4CAF50;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  font-size: 16px;
}

input[type="submit"]:hover {
  background-color: #3e8e41;
}
    </style>
  </head>
  <body>
    <h1>Bank Account Information</h1>
 
    <form action="addAccountServlet" method="POST">
      <label for="accountType">Account Type:</label>
      <select id="accountType" name="accountType">
        <option value="Checking">Checking</option>
        <option value="Saving">Saving</option>
      </select><br><br>
      <label for="accountHolderName">Account Holder Name:</label>
      <input type="text" id="accountHolderName" name="accountHolderName" required><br><br>
      <label for="accountHolderContact">Account Holder Contact:</label>
      <input type="text" id="accountHolderContact" name="accountHolderContact" required><br><br>
      <label for="accountBalance">Account Balance:</label>
      <input type="number" id="accountBalance" name="accountBalance" required><br><br>
      <label for="accountSetupDate">Account Setup Date:</label>
      <input type="date" id="accountSetupDate" name="accountSetupDate" required><br><br>
      <input type="submit" value="Add Account">
    </form>
  </body>
</html>
