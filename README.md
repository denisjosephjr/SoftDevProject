# SoftDevProject
A minimal working UX for a basic employee management system for the company "2".
The DBeaver-MySQL integration will be done with the JDBC Library.

This program will allow the user to generate 3 types of reports:
1. Full-time employee information with pay statement history
2. Total pay for month by job title
3. Total pay for month by Division

Features:
1. Change employee table; add column SSN (no dashes)
2. Search for an employee using name, SSN, empid to show their information
3. Update an employee's data
4. Update employee's salary for an increate of a particular percentage only for a salary amount range.
   E.G. 3.2% for salary greater than, equal to 58K but less than 105K

NOTE: There isn't a logon/ authorization functionality.

For Groupmates: 

When you clone the repo locally, you must run 2 commands to link the project with DBeaver.
First:

javac -cp lib/mysql-connector-9.6.0.jar Main.java

Then this one:
For Windows: java -cp ".;lib/mysql-connector-9.6.0.jar" Main

For iOS: java -cp ".:lib/mysql-connector-9.6.0.jar" Main
