package dao;

import java.sql.*;
import java.util.ArrayList;

import modelo.Ataque;

public class DaoAtaque {
	
	private Connection conn = null;
	 private static DaoAtaque instance = null;

	public DaoAtaque() throws SQLException {
       conn = DbConnection.getConnection();
   }

   // patrón Singleton: si no existe la conexion la crea
   public static DaoAtaque getInstance() throws SQLException {
       if (instance == null) {
           instance = new DaoAtaque(); // Si no existe, la crea
       }
       return instance;
   }
   
   public ArrayList<Ataque> obtenerAtaquesDePersonaje(int personajeId) throws SQLException {
       ArrayList<Ataque> listaAtaques = new ArrayList<>();

       String sql = "SELECT * FROM ataques WHERE personaje_id = ?";
       PreparedStatement ps = conn.prepareStatement(sql);
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

}
