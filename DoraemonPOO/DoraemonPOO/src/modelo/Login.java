package modelo;

import java.sql.SQLException;
import dao.DaoLogin;

public class Login {
	
    // Atributos
    private String username;  
    private String password;  
    private int puntos;

    
    public Login() {
    	
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;

    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
    

	// Metodo para registrar un nuevo usuario en la base de datos
    public boolean registrarUsuario() throws SQLException {
    	// Obtener instancia del DAO
        DaoLogin dao = DaoLogin.getInstance();  

        // Verifica si el usuario ya existe
        if (dao.usuarioExiste(this.username)) {
            System.out.println("El usuario ya existe.");  
            return false;
        }

        // Si no existe, intenta registrarlo
        boolean resultado = dao.insertarUsuario(this);

        if (resultado) {
            System.out.println("Usuario registrado correctamente. ");
        }
     // Devuelve true si se insertó correctamente
        return resultado;  
    }

    // metodo para iniciar sesión
    public boolean iniciarSesion() throws SQLException {
    	// Obtener instancia del DAO
        DaoLogin dao = DaoLogin.getInstance();  
     // Devuelve true si las credenciales son válidas
        return dao.verificarLogin(this);  
    }
    
    public void sumarPuntos(int extra) { 
    	this.puntos += extra; 
    	}


    // toString
    @Override
    public String toString() {
        return "Login [username=" + username + "]";
    }
}
