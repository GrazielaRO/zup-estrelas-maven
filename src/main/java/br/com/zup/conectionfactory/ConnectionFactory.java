package br.com.zup.conectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/estacionamento?user=root&password=root&useTimezone=true&serverTimezone=UTC");

		} catch (SQLException e) {
			
			throw new RuntimeException(e);
			
		}
	}
}
