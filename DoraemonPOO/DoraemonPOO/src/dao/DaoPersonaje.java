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
    
     
    public ArrayList<Personaje> mostrarPersonajesPrincipales() throws SQLException {
        // Creamos un ArrayList
        ArrayList<Personaje> listaPersonajes = new ArrayList<>();

        // Consulta SQL: selecciona los personajes que son principales
        String consulta = "SELECT id, nombre FROM personajes WHERE tipo = 'principal'";

        // Creamos el objeto para ejecutar la consulta
        Statement stmt = conn.createStatement();
        // Ejecutamos la consulta y guardamos el resultado
        ResultSet resultado = stmt.executeQuery(consulta);

        // Recorremos el resultado fila por fila
        while (resultado.next()) {
        	// Creamos un personaje vacío
            Personaje personaje = new Personaje();

            // Rellenamos los datos del personaje
            personaje.setId(resultado.getInt("id"));
            personaje.setNombre(resultado.getString("nombre"));

            // Añadimos el personaje a la lista
            listaPersonajes.add(personaje);
        }

        // Devolvemos la lista completa
        return listaPersonajes;
    }
  

    
 // Método que busca y devuelve un personaje por su ID
    public Personaje seleccionarPersonaje(int idBuscado) throws SQLException {
        // Creamos una variable vacía para guardar el personaje si lo encontramos
        Personaje personajeSeleccionado = null;

        // Creamos la consulta SQL
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM personajes WHERE id = ?");

        // Reemplazamos el "?" con el ID recibido como parámetro
        stmt.setInt(1, idBuscado);

        // Ejecutamos la consulta
        ResultSet rs = stmt.executeQuery();

        // Si se encontró un personaje con ese ID
        if (rs.next()) {
            personajeSeleccionado = new Personaje();

            // Rellenamos los datos del personaje
            personajeSeleccionado.setId(rs.getInt("id"));
            personajeSeleccionado.setNombre(rs.getString("nombre"));
            personajeSeleccionado.setVida(rs.getInt("vida"));
        }

        // Devolvemos el personaje (o null si no se encontró)
        return personajeSeleccionado;
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
