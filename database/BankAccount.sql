CREATE DATABASE BankAccounts;
USE BankAccounts;
CREATE TABLE Accounts (
  AccountID INT PRIMARY KEY AUTO_INCREMENT,
  AccountType VARCHAR(20),
  AccountHolderName VARCHAR(50),
  AccountHolderContact VARCHAR(20),
  AccountBalance DOUBLE,
  AccountSetupDate DATE
);
INSERT INTO Accounts (AccountType, AccountHolderName, AccountHolderContact, AccountBalance, AccountSetupDate) VALUES
  ('Checking', 'John Doe', '555-1234', 1000.00, '2022-01-01'),
  ('Saving', 'Jane Doe', '555-5678', 5000.00, '2022-02-01'),
  ('Checking', 'Bob Johnson', '555-9012', 2500.00, '2022-03-01'),
  ('Saving', 'Alice Lee', '555-3456', 7500.00, '2022-04-01'),
  ('Checking', 'Tom Brown', '555-7890', 500.00, '2022-05-01');
SELECT * FROM Accounts;
