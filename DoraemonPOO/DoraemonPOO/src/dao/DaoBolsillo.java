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
        String sql = "SELECT * FROM bolsillo ORDER BY RAND() LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                Bolsillo obj = new Bolsillo();
                obj.setId(rs.getInt("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setDano(rs.getInt("dano"));
                obj.setFrase(rs.getString("frase"));
                return obj;
            }

        } catch (SQLException e) {
            System.out.println("Error accediendo al bolsillo mágico: " + e.getMessage());
        }

        return null;
    }
}
