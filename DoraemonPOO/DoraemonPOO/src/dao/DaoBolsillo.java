package dao;

import modelo.Bolsillo;
import java.sql.*;

public class DaoBolsillo {
    private Connection conn;
    private static DaoBolsillo instance;

    public DaoBolsillo() throws SQLException {
        conn = DbConnection.getConnection();
    }

    public static DaoBolsillo getInstance() throws SQLException {
        if (instance == null) {
            instance = new DaoBolsillo();
        }
        return instance;
    }

    public Bolsillo obtenerObjetoAleatorio() {
    	
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bolsillo ORDER BY RAND() LIMIT 1");
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                Bolsillo objetoBolsillo = new Bolsillo();
                objetoBolsillo.setId(rs.getInt("id"));
                objetoBolsillo.setNombre(rs.getString("nombre"));
                objetoBolsillo.setDescripcion(rs.getString("descripcion"));
                objetoBolsillo.setDano(rs.getInt("dano"));
                objetoBolsillo.setFrase(rs.getString("frase"));
                objetoBolsillo.setTipo(rs.getString("tipo"));
                
                return  objetoBolsillo;
            }

        } catch (SQLException e) {
            System.out.println("Error accediendo al bolsillo mágico: " + e.getMessage());
        }

        return null;
    }
}
