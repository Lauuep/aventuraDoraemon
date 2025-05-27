package dao;

import modelo.Personaje;
import modelo.Ataque;
import modelo.Enemigo;
import modelo.Objeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPersonaje {
	
	 private Connection conn = null;
	 private static DaoPersonaje instance = null;

	public DaoPersonaje() throws SQLException {
        conn = DbConnection.getConnection();
    }

    // patrón Singleton: si no existe la conexion la crea
    public static DaoPersonaje getInstance() throws SQLException {
        if (instance == null) {
            instance = new DaoPersonaje(); // Si no existe, la crea
        }
        return instance;
    }
    
 // Actualizar personaje por nombre
    public boolean actualizarPersonaje(Personaje personaje) {
        String sql = "UPDATE personaje SET vida = ? WHERE nombre = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, personaje.getVida());
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar personaje: " + e.getMessage());
            return false;
        }
    }
     
    public ArrayList<Personaje> mostrarPersonajesPrincipales() throws SQLException {
        ArrayList<Personaje> personajes = new ArrayList<>();
        String query = "SELECT id, nombre FROM personajes WHERE tipo = 'principal'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Personaje p = new Personaje();
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            personajes.add(p);
        }

        return personajes;
    }
        
    
    
 // Obtener un personaje por su ID
    public Personaje seleccionarPersonaje(int id) throws SQLException {
        Personaje personaje = null;
   
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM personajes WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                personaje = new Personaje();
                personaje.setId(rs.getInt("id"));
                personaje.setNombre(rs.getString("nombre"));
                personaje.setVida(rs.getInt("vida"));
            }

        return personaje;
    }
    
    // Obtener un personaje por su tipo
    public Personaje seleccionarEnemigo(int id) throws SQLException {
        Personaje malo = null;
   
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM personajes WHERE tipo = 'NPC_Enemigo'");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                malo = new Personaje();
                malo.setId(rs.getInt("id"));
                malo.setNombre(rs.getString("nombre"));
                malo.setVida(rs.getInt("vida"));
            }

        return malo;
    }
   
    
    public ArrayList<Ataque> obtenerAtaquesDePersonaje(int personajeId) throws SQLException {
        ArrayList<Ataque> listaAtaques = new ArrayList<>();
      
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ataques WHERE personaje_id = ?");
            ps.setInt(1, personajeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ataque a = new Ataque();
                a.setNombre(rs.getString("nombre"));
                a.setDescripcion(rs.getString("descripcion"));
                a.setDano(rs.getInt("dano"));
                listaAtaques.add(a);
            }
        return listaAtaques;
    }
   
    
    public Personaje obtenerEnemigoPorLugarDePersonaje(int personajeId) throws SQLException {
        String sql = """
            SELECT p2.*
            FROM personajes p1
            JOIN personajes p2 ON p1.lugar_id = p2.lugar_id
            WHERE p1.id = ? AND p2.tipo = 'NPC_Enemigo'
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, personajeId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Personaje enemigo = new Personaje();
            enemigo.setId(rs.getInt("id"));
            enemigo.setNombre(rs.getString("nombre"));
            enemigo.setVida(rs.getInt("vida"));
            enemigo.setVidaMax(rs.getInt("vida_max"));
            enemigo.setTipo(rs.getString("tipo"));
            enemigo.setLugarId(rs.getInt("lugar_id"));
            return enemigo;
        }

        return null;
    }
    
    
}
