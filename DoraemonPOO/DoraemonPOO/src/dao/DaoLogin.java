package dao;

import java.sql.*;
import java.util.ArrayList;

import modelo.Login;

public class DaoLogin {
	
	//conexión a la base de datos
    private Connection conn = null;
    private static DaoLogin instance = null;

    public DaoLogin() throws SQLException {
        conn = DbConnection.getConnection();
    }

    // patrón Singleton: si no existe la conexion la crea
    public static DaoLogin getInstance() throws SQLException {
        if (instance == null) {
            instance = new DaoLogin(); // Si no existe, la crea
        }
        return instance;

    }
    
    
    public boolean usuarioExiste(String username) throws SQLException {
    	//sentencia sql
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        //parámetro de la consulta
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }
    

    // Método que inserta un nuevo usuario en la base de datos
    public boolean insertarUsuario(Login login) throws SQLException {
    	 boolean registroCorrecto = false;
    	//sentencia sql
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
     	//Asigna el username
        stmt.setString(1, login.getUsername()); 
     	// Asigna el password
        stmt.setString(2, login.getPassword()); 
        // Ejecuta la inserción
        int recordsInserted = stmt.executeUpdate(); 
        
        // comprueba si se hizo correctamente, es decir si se añadio alguna fila
        if (recordsInserted > 0) {
            System.out.println("Usuario registrado con éxito 👌");
            registroCorrecto=true;
        } else {
            System.out.println("Error. Usuario no registrado 🤦");
            registroCorrecto=false;
        }
        return registroCorrecto;
    }

    //verifica si existen credenciales válidas en la base de datos
    public boolean verificarLogin(Login login) throws SQLException {
    	//sentencia sql
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        //comprueba que el nombre de usuario y la contraseña sean correctas
        stmt.setString(1, login.getUsername());
        stmt.setString(2, login.getPassword());
        ResultSet rs = stmt.executeQuery();
        
     //Devuelve true si hay al menos un resultado (es decir, encontró un usuario con esos datos).
     //Devuelve false si no encontró ninguno (usuario o contraseña incorrectos).
        return rs.next(); 
    }
   
    
}
