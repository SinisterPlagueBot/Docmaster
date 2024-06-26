package com.jee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDataSource implements DataSource {

	
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl1"; 
        String user = "c##mesos1";
        String password = "mehdi";
        try {
           
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Oracle database.");
            connection.setAutoCommit(false);
            
           return  connection;
         } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC driver not found.");
            e.printStackTrace();
            
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
           
        }
        return null;
	}

}
