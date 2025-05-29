package dao;

import modelo.Mochila;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoaMochila {
    private Connection conn;
    private static DoaMochila instance;

    public DoaMochila() throws SQLException {
        conn = DbConnection.getConnection();
    }

    public static DoaMochila getInstance() throws SQLException {
        if (instance == null) {
            instance = new DoaMochila();
        }
        return instance;
    }
    
    

    // Obtener todos los objetos en la mochila de un personaje
    public ArrayList<Mochila> obtenerObjetosDeMochila(int personajeId) {
        ArrayList<Mochila> lista = new ArrayList<>();
        String sql = "SELECT * FROM mochila WHERE personaje_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, personajeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mochila obj = new Mochila();
                obj.setId(rs.getInt("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setPoder(rs.getInt("poder"));
                obj.setCantidad(rs.getInt("cantidad"));
                lista.add(obj);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener objetos de la mochila: " + e.getMessage());
        }

        return lista;
    }
    
    public void restarCantidadObjeto(int personajeId, int objetoId) throws SQLException {
        String sql = "UPDATE mochila SET cantidad = cantidad - 1 WHERE id_personaje = ? AND id_objeto = ? AND cantidad > 0";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, personajeId);
        ps.setInt(2, objetoId);
        ps.executeUpdate();
    }
    
}
