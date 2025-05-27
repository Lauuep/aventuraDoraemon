package controlador;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Textos.Textos;
import arte.dibujo;
import dao.DaoAtaque;
import dao.DaoLogin;
import dao.DaoAtaque;
import dao.DaoPersonaje;
import modelo.Ataque;
import modelo.Batalla;
import modelo.Login;
import modelo.Personaje;

public class Partida {
	
	private static Scanner sc= new Scanner(System.in);
	private static Personaje protagonista;

	// *** MENÚ PRINCIPAL DEL JUEGO ***
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
                	iniciarSesion();
                	break;
                case 2:
                	registrarUsuario();
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
	
	//*********************************************************************************
	//************************** METODOS DE LOGIN *************************************
	//*********************************************************************************
	
	// metodo para iniciar sesión
    private static void iniciarSesion() {
        System.out.println("\n=== INICIAR SESIÓN ===");
        System.out.print("Introduce tu nombre de usuario: ");
        String nombreUsuario = sc.nextLine();

        System.out.print("Introduce tu contraseña: ");
        String contrasena = sc.nextLine();

        // Creamos el objeto con los datos
        Login datosUsuario = new Login(nombreUsuario, contrasena);
        
        try {
        	//llave para acceder a los metodos de DaoLogin
            DaoLogin daoLogin = DaoLogin.getInstance();
           
            if (daoLogin.verificarLogin(datosUsuario)==true) {
                System.out.println("✅ Sesión iniciada correctamente. ¡Bienvenido " + nombreUsuario + "!");
                iniciarJuego(); 
            } else {
                System.out.println("❌ Usuario o contraseña incorrectos.");
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error al iniciar sesión: " + e.getMessage());
        }
    }
    
 // metodo para registrar un nuevo usuario
    private static void registrarUsuario() {

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
            DaoLogin daoLogin = DaoLogin.getInstance();

            // Comprobamos si ese usuario ya está registrado
            boolean yaExiste = daoLogin.usuarioExiste(nombreUsuario);

            //lo mismo que poner esto: if(dao.usuarioExiste(nombreUsuario))
            if (yaExiste==true) {
                System.out.println("❌ Ese nombre de usuario ya está en uso. Intenta con otro diferente.");
            } else {
                // Intentamos insertar el nuevo usuario
                boolean registradoCorrectamente = daoLogin.insertarUsuario(nuevoUsuario);

                //if (dao.insertarUsuario(nuevoUsuario))
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

	//*********************************************************************************
    //*************************** OTROS METODOS ***************************************
	//*********************************************************************************

    // *** INICIAR JUEGO ***
    public static void iniciarJuego() throws SQLException {
        // Mostrar introducción
        dibujo.logo();
        System.out.println("🎮 Aquí empieza tu aventura en Doraemon POO...");
        dibujo.caraDoraemon();
        Textos.intro();

        // Elegir personaje principal
        seleccionarPersonaje();
        
        //llave de acceso a los metodos de Dao Personaje
        DaoPersonaje daoPersonaje = DaoPersonaje.getInstance();
        DaoAtaque daoAtaque = DaoAtaque.getInstance();

        // Buscar un enemigo según el personaje elegido
        Personaje enemigo = daoPersonaje.obtenerEnemigoPorLugarDePersonaje(protagonista.getId());

        if (enemigo == null) {
            System.out.println("❌ No se encontró enemigo para este lugar.");
            return;
        }

        // Cargar ataques del enemigo
        enemigo.setAtaques(daoAtaque.obtenerAtaquesDePersonaje(enemigo.getId()));

        // Mostrar enemigo y empezar la batalla
        System.out.println("⚔️ Te enfrentas a: " + enemigo.getNombre());
        Batalla batalla = new Batalla(protagonista, enemigo);

        boolean ganoBatalla = batalla.iniciarCombate(); // Este método debería devolver true o false

        // Resultado de la batalla
        if (ganoBatalla) {
            System.out.println("✅ ¡Ganaste! " + protagonista.getNombre() + " continúa la aventura.");
        } else {
            System.out.println("💀 Has sido derrotado. Fin del juego.");
        }
    }

    
    private static void seleccionarPersonaje() throws SQLException {
        Scanner sc = new Scanner(System.in);
        
        //llave de acceso a los metodos de DaoPersonaje y DaoAtaque
        DaoPersonaje daoPersonaje = DaoPersonaje.getInstance();
        DaoAtaque daoAtaque = DaoAtaque.getInstance();

        // Mostrar lista de personajes
        ArrayList<Personaje> listaPersonaje = (ArrayList<Personaje>) daoPersonaje.mostrarPersonajesPrincipales();

        System.out.println("\n📜 Personajes disponibles:");
        for (int i = 0; i < listaPersonaje.size(); i++) {
            Personaje p = listaPersonaje.get(i);
            System.out.println(p.getId() + " - " + p.getNombre());
        }

        // Elegir personaje
        System.out.print("🔍 Escribe el ID del personaje que quieres usar: ");
        int idElegido = sc.nextInt();

        //Almacenamos en protagonista la id del person
        protagonista = daoPersonaje.seleccionarPersonaje(idElegido);

        // Validar selección
        if (protagonista == null) {
            System.out.println("❌ ID inválido. No se seleccionó personaje.");
            return;
        }

        // Mostrar personaje y ataques
        System.out.println("✅ Elegiste a: " + protagonista.getNombre() + " (Vida: " + protagonista.getVida() + ")");
        
        ArrayList<Ataque> ataques = (ArrayList<Ataque>) daoAtaque.obtenerAtaquesDePersonaje(protagonista.getId());
        protagonista.setAtaques(ataques);

        System.out.println("\n⚔️ Ataques disponibles:");
        for (int i = 0; i < ataques.size(); i++) {
            Ataque a = ataques.get(i);
            System.out.println((i + 1) + ". " + a.getNombre() + ": " + a.getDescripcion() + " (Daño: " + a.getDano() + ")");
        }
    }


}
/*
 * 
 * danoExtra=random((5)+1);
 * danoExtratotal=
 * 
 * if(danoExtra>0){
 * vida
 * 
 * 
 * */
