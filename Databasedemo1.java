package com.example.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Databasedemo1 {

	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb1","root","root@123");
		    Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery("select * from employee1");
		    while(rs.next()) {
		    	String empid = rs.getString("empname");
		    	System.out.println("Employeeid is:"+empid);
		    }
		    
		    
		    
				    
					try {
						statement.addBatch("insert into employee1 values(9,Rebecca,'109','109')");
					} catch (Exception e) {
						
						con.rollback();
					}
			
		   //java.sql.BatchUpdateException: Unknown column 'Rebecca' in 'field list' Caused by: java.sql.SQLSyntaxErrorException: Unknown column 'Rebecca' in 'field list' at line 42			
				
				
			
				
			
		    
		    int[] cnt = statement.executeBatch();
			for (Integer i : cnt) {
				System.out.println(i);
			} 
		    
			PreparedStatement ps = con.prepareStatement("select * from employee1 where empname=?");
		    ps.setString(1, "Vidisha");
		    ResultSet rs1 = ps.executeQuery();
		    while(rs1.next()) {
		    	String empid = rs1.getString("empname");
		    	String salary = rs1.getString("salary");
		    	String deptid = rs1.getString("deptid");
		    	System.out.println("Employeerecord is:"+empid+"salary"+salary+"deptid"+deptid);
		    }
		    
		    
		    CallableStatement cs = con.prepareCall("call myproc1()");
		    ResultSet rs2 = cs.executeQuery();
		    while(rs2.next()) {
		    	String empid = rs2.getString("empname");
		    	System.out.println("Employeerecord is:"+empid);
		    }
		    
		    CallableStatement cs1 = con.prepareCall("call myproc_out(?)");
		    cs1.setString(1, "Chaitra");
		    ResultSet rs3 = cs1.executeQuery();
		    while(rs3.next()) {
		    	String empid = rs3.getString("salary");
		    	System.out.println("Employeerecord is:"+empid);
		    }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		
	}
}
