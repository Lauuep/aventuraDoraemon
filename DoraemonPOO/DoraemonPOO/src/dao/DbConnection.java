package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	
	public static Connection instance = null;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/doraemon";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Cada llamada devuelve una nueva conexión (segura para múltiples usos)
    public static Connection getConnection() throws SQLException {
    	if (instance==null) {
			Properties props = new Properties ();
			props.put("user","root");
			props.put("password", "");
			
			instance =  DriverManager.getConnection(DB_URL, props);

		}
		
		return instance;
		
	}
}

