package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Ataque;
import modelo.Bolsillo;
import modelo.Lugares;
import modelo.Personaje;

public class DaoLugares {
	private Connection conn;
    private static DaoLugares instance;

    public DaoLugares() throws SQLException {
        conn = DbConnection.getConnection();
    }

    public static DaoLugares getInstance() throws SQLException {
        if (instance == null) {
            instance = new DaoLugares();
        }
        return instance;
    }

    public ArrayList<Lugares> mostrarLugares() throws SQLException {
        // Creamos un ArrayList
        ArrayList<Lugares> listaLugares = new ArrayList<>();

        // Consulta SQL: selecciona los personajes que son principales
        String consulta = "SELECT * FROM lugares";

        // Creamos el objeto para ejecutar la consulta
        Statement stmt = conn.createStatement();
        // Ejecutamos la consulta y guardamos el resultado
        ResultSet resultado = stmt.executeQuery(consulta);

        // Recorremos el resultado fila por fila
        while (resultado.next()) {
        	// Creamos un personaje vacío
            Lugares lugar = new Lugares();

            // Rellenamos los datos del personaje
            lugar.setId(resultado.getInt("id"));
            lugar.setNombre(resultado.getString("nombre"));
            lugar.setRecompensa (resultado.getString("recompensa"));

            // Añadimos el personaje a la lista
            listaLugares.add(lugar);
        }

        // Devolvemos la lista completa
        return listaLugares;
    }
    
    
    public Lugares obtenerLugarAleatorioMortal() {
    	
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lugares WHERE categoria='mortal' ORDER BY RAND() LIMIT 1");
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                Lugares lugar = new Lugares();
                lugar.setId(rs.getInt("id"));
                lugar.setNombre(rs.getString("nombre"));
                lugar.setRecompensa(rs.getString("descripcion"));
                
                
                return lugar;
            }

        } catch (SQLException e) {
            System.out.println("Error accediendo al lugares: " + e.getMessage());
        }

        return null;
    }
    
    public Lugares obtenerLugarAleatorioNoMortal() {
    	
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lugares WHERE categoria='no_mortal' ORDER BY RAND() LIMIT 1");
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                Lugares lugar = new Lugares();
                lugar.setId(rs.getInt("id"));
                lugar.setNombre(rs.getString("nombre"));
                lugar.setRecompensa(rs.getString("descripcion"));
                
                
                return lugar;
            }

        } catch (SQLException e) {
            System.out.println("Error accediendo al lugares: " + e.getMessage());
        }

        return null;
    }
 


}
