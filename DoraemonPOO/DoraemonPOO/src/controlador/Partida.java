package controlador;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Textos.Textos;
import arte.dibujo;
import dao.DaoLogin;
import dao.DaoPersonaje;
import modelo.Ataque;
import modelo.Batalla;
import modelo.Login;
import modelo.Personaje;

public class Partida {
	
	Scanner sc = new Scanner(System.in);
	private static Personaje elegido;

    //menu juego
	public static void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n=== MENÚ PRINCIPAL === ");
            System.out.println("1. Iniciar sesión ✨");
            System.out.println("2. Registrarse 🖊️ ");
            System.out.println("3. Consultar Ranking 🔍");
            System.out.println("4. Salir 👋🏻");
            System.out.print("Selecciona una opción (1-4): ");

            int opcion = sc.nextInt();
            sc.nextLine(); // limpia el salto de línea

            switch (opcion) {
                case 1:
                	iniciarSesion(sc);
                	break;
                case 2:
                	registrarUsuario(sc);
                case 3:
                	System.out.println("RANKING.");
                	break;
                case 4:
                	salir=true;
                	break;
                default:
                	System.out.println("Error: Opción no valida.");
            }
        }

        sc.close();
    }
    
	//metodo de login

 // Método para iniciar sesión
    private static void iniciarSesion(Scanner sc) {
        System.out.println("\n=== INICIAR SESIÓN ===");
        System.out.print("Introduce tu nombre de usuario: ");
        String nombreUsuario = sc.nextLine();

        System.out.print("Introduce tu contraseña: ");
        String contrasena = sc.nextLine();

        // Creamos el objeto con los datos
        Login datosUsuario = new Login(nombreUsuario, contrasena);
        
        try {
        	//llave para acceder a los metodos de DaoLogin
            DaoLogin accesoBD = DaoLogin.getInstance();
           
            if (accesoBD.verificarLogin(datosUsuario)) {
                System.out.println("✅ Sesión iniciada correctamente. ¡Bienvenido " + nombreUsuario + "!");
                iniciarJuego(); // Aquí se llamaría al juego
            } else {
                System.out.println("❌ Usuario o contraseña incorrectos.");
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error al iniciar sesión: " + e.getMessage());
        }
    }
    
 // Método para registrar un nuevo usuario
    private static void registrarUsuario(Scanner sc) {

        System.out.println("\n=== REGISTRO DE USUARIO NUEVO ===");

        // Pedimos el nombre de usuario
        System.out.print("👉 Escribe un nombre de usuario: ");
        String nombreUsuario =sc.nextLine();

        // Pedimos la contraseña
        System.out.print("🔒 Escribe una contraseña: ");
        String contrasena = sc.nextLine();

        // Creamos un objeto Login con los datos introducidos
        Login nuevoUsuario = new Login(nombreUsuario, contrasena);

        try {
            //llave para acceder a lo metodos de DaoLogin
            DaoLogin accesoBD = DaoLogin.getInstance();

            // Comprobamos si ese usuario ya está registrado
            boolean yaExiste = accesoBD.usuarioExiste(nombreUsuario);

            if (yaExiste==true) {
                System.out.println("❌ Ese nombre de usuario ya está en uso. Intenta con otro diferente.");
            } else {
                // Intentamos insertar el nuevo usuario
                boolean registradoCorrectamente = accesoBD.insertarUsuario(nuevoUsuario);

                if (registradoCorrectamente==true) {
                    System.out.println("✅ Usuario registrado con éxito. ¡Ahora puedes iniciar sesión!");
                } else {
                    System.out.println("❌ Hubo un problema al registrar al usuario.");
                }
            }

        } catch (Exception error) {
            // Mostramos el error si algo falla
            System.out.println("⚠️ Ocurrió un error al registrar: " + error.getMessage());
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    

   
    




    public static void iniciarJuego() throws SQLException {
    	
    	boolean resultadoBatalla=false;
    	
    	dibujo.logo();
        System.out.println("Aquí comienza tu aventura en Doraemon POO...");
        dibujo.caraDoraemon();
        Textos.intro();
        seleccionarPersonaje();

        DaoPersonaje dao = DaoPersonaje.getInstance(); 
        Personaje enemigo = dao.obtenerEnemigoPorLugarDePersonaje(elegido.getId());

        if (enemigo == null) {
            System.out.println("❌ No se encontró un enemigo para este lugar.");
            return;
        }

        //Cargar ataques del enemigo 
        ArrayList<Ataque> ataquesEnemigo = dao.obtenerAtaquesDePersonaje(enemigo.getId());
        enemigo.setAtaques(ataquesEnemigo);

        System.out.println("Te enfrentas a: " + enemigo.getNombre());
        Batalla batalla = new Batalla(elegido, enemigo);
        batalla.iniciarCombate();
        if (resultadoBatalla==true) {
        	System.out.println("Enhorabuena: " + elegido.getNombre() + " continuas la aventura");
        }
        else {
        	System.out.println("Has muerto, que pringado");
        }
        
    }
    
    private static void seleccionarPersonaje() throws SQLException {
        Scanner sc = new Scanner(System.in);
        DaoPersonaje dao = DaoPersonaje.getInstance(); 

        ArrayList<Personaje> personajes = (ArrayList<Personaje>) dao.mostrarPersonajesPrincipales();

        System.out.println("\n📜 Lista de personajes disponibles:");
        for (int i = 0; i < personajes.size(); i++) {
            Personaje p = personajes.get(i);
            System.out.println(p.getId() + " - " + p.getNombre());
        }

        System.out.print("Selecciona un personaje por ID: ");
        int idSeleccionado = sc.nextInt();

        elegido = dao.seleccionarPersonaje(idSeleccionado);  // ✅ corregido aquí
        if (elegido == null) {
            System.out.println("❌ ID no válido. No se seleccionó ningún personaje.");
        } else {
            System.out.println("✅ Has elegido a: " + elegido.getNombre() + " (Vida: " + elegido.getVida() + ")");

            ArrayList<Ataque> ataques = (ArrayList<Ataque>) dao.obtenerAtaquesDePersonaje(elegido.getId());
            System.out.println("\n⚔️ Ataques disponibles:");
            for (int i = 0; i < ataques.size(); i++) {
                Ataque a = ataques.get(i);
                System.out.println((i + 1) + ". " + a.getNombre() + " - " + a.getDescripcion() + " (Daño: " + a.getDano() + ")");
            }
            elegido.setAtaques(ataques);
        }
    }

}
