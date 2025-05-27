package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Enemigo;
import modelo.Personaje;

public class DaoEnemigo {
	
	 private Connection conn = null;
	 private static DaoEnemigo instance = null;

	public DaoEnemigo() throws SQLException {
        conn = DbConnection.getConnection();
    }

    // patrón Singleton: si no existe la conexion la crea
    public static DaoEnemigo getInstance() throws SQLException {
        if (instance == null) {
            instance = new DaoEnemigo(); // Si no existe, la crea
        }
        return instance;
    }
    
    // Obtener un personaje por su tipo
    public Enemigo seleccionarEnemigo(int id) throws SQLException {
        Enemigo malo = null;
         PreparedStatement ps = conn.prepareStatement("SELECT * FROM enemigos WHERE tipo = 'NPC_Enemigo'");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                malo = new Enemigo();
                malo.setId(rs.getInt("id"));
                malo.setNombre(rs.getString("nombre"));
                malo.setVida(rs.getInt("vida"));
            }

        return malo;
    }
    
    
    
    
    
    
}
