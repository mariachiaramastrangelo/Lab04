package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	static private final String jdbcUrl = "jdbc:mysql://localhost:3306/iscritticorsi?user=root&password=provaprova&serverTimezone=Europe/Rome";

	public static Connection getConnection() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(jdbcUrl);
			
			
		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException("Cannot get a connection " + jdbcUrl, e);
		}
		return connection;
	}

}
