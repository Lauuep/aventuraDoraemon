
package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import dao.DoaMochila;
import dao.DaoBolsillo;
import dao.DaoLogin;

public class Batalla {
    private Personaje jugador;
    private Personaje enemigo;
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private Clima climaActual;
    private MomentoDia momentoActual;
    

 // Constructor: se crea la batalla con el jugador y el enemigo
    public Batalla(Personaje jugador, Personaje enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.climaActual = generarClimaAleatorio();
        this.momentoActual = generarMomentoAleatorio();
        
    }
     
 // Generar clima aleatorio
    private Clima generarClimaAleatorio() {
    	int numero = random.nextInt(4); // 0, 1, 2 o 3
        Clima climaAleatorio;

        if (numero == 0) {
            climaAleatorio = Clima.DESPEJADO;
        } else if (numero == 1) {
            climaAleatorio = Clima.LLUVIA;
        } else if (numero == 2) {
            climaAleatorio = Clima.NUBLADO;
        } else {
            climaAleatorio = Clima.NIEVE;
        }

        return climaAleatorio;
    }

 // Generar momento del día aleatorio
    private MomentoDia generarMomentoAleatorio() {
        int numero = random.nextInt(3); // 0, 1 o 2
        MomentoDia momento;

        if (numero == 0) {
            momento = MomentoDia.MANANA;
        } else if (numero == 1) {
            momento = MomentoDia.TARDE;
        } else {
            momento = MomentoDia.NOCHE;
        }

        return momento;
    }

 // Empieza el combate, devuelve true si gana el jugador
    public boolean iniciarCombate() {
    	
    	boolean resultadoBatalla=false;
        System.out.println("⚔️ ¡Batalla entre " + jugador.getNombre() + " y " + enemigo.getNombre() + "!");
        System.out.println("🌦️ Clima actual: " + climaActual);
        System.out.println("🕒 Momento del día: " + momentoActual);
        boolean turnoJugador = true;

        while (jugador.getVida() > 0 && enemigo.getVida() > 0) {
            if (turnoJugador==true) {
                turnoDelJugador();
            } else {
                turnoDelEnemigo();
            }
            turnoJugador = !turnoJugador;
        }

        if (jugador.getVida() <= 0) {
            System.out.println("\n💀 Has sido derrotado...");
            resultadoBatalla=false;
        } else {
            System.out.println("\n🏆 ¡Has ganado!");
            resultadoBatalla=true;

        }
        return resultadoBatalla;
    }

    private void turnoDelJugador() {
        System.out.println("\n\ud83c\udfae Tu turno. Elige una opción:");
        System.out.println("1. Atacar");
        System.out.println("2. Usar bolsillo mágico");
        System.out.println("3. Usar objeto de la mochila");

        int opcion = sc.nextInt();

        if (jugador.getVida() > 0) {
            switch (opcion) {
                case 1:
                    realizarAtaque(jugador, enemigo);
                    break;
                case 2:
                    usarBolsilloMagico();
                    break;
                case 3:
                    usarObjetoDeMochila();
                    break;
                default:
                    System.out.println("\u274c Opción no válida.");
            }
        } else {
            System.out.println("\u26a0\ufe0f No puedes hacer nada, estás sin vida.");
        }
    }
    
    private void turnoDelEnemigo() {
        if (enemigo.getVida() > 0) {
            System.out.println("\n\ud83e\udd16 Turno de " + enemigo.getNombre());
            realizarAtaque(enemigo, jugador);
        } else {
            System.out.println("\u274c El enemigo ya no puede atacar.");
        }
    }

    private void realizarAtaque(Personaje atacante, Personaje receptor) {
        ArrayList<Ataque> ataques = atacante.getAtaques();

        // Solo hacemos el ataque si tiene ataques disponibles
        if (ataques != null && !ataques.isEmpty()) {

        Ataque ataque;
        //Si el que ataca es el jugador, se deja elegir el ataque.
        if (atacante == jugador) {
            ataque = seleccionarAtaqueJugador(ataques);
        } else {
        	//Si es el enemigo, se elige uno al azar de su lista.
            ataque = ataques.get(random.nextInt(ataques.size()));
        }
        
        
        // Guardamos el daño base del ataque
        int baseDano = ataque.getDano();
        int danoFinal = baseDano;

        // Comprobamos si hay "viento raro" que ignora condiciones
        // Hay un 20% de probabilidad de que aparezca, siendo solo viento raro el numero 0
        int numeroAleatorio = random.nextInt(5); // Número entre 0 y 4
        boolean hayVientoRaro=false;
        if (numeroAleatorio==0) {
        	hayVientoRaro=true;
        } else {
        	hayVientoRaro=false;
        }
       
        
        // Si no hay viento raro, aplicamos modificadores por clima y momento del día
        if (hayVientoRaro==false) {
            // Clima afecta el daño
            if (climaActual == Clima.LLUVIA) {
                danoFinal -= 1;
                System.out.println("🌧️ La lluvia reduce el daño (-1).");
            } else if (climaActual == Clima.NIEVE) {
                danoFinal -= 2;
                System.out.println("❄️ La nieve reduce mucho el daño (-2).");
            } else {
                System.out.println("🌤️ El clima no afecta.");
            }

            // Si es de noche, se aumenta el daño
            if (momentoActual == MomentoDia.NOCHE) {
                danoFinal += 2;
                System.out.println("🌙 La noche potencia el ataque (+2).");
            }
        } else {
            // Si hay viento raro, las condiciones no se aplican
            System.out.println("🌪️ Viento raro: las condiciones se ignoran.");
        }
        

        // Impacto del golpe (azar): 0 = fallo, 1 = leve, 2 = normal, 3 = crítico
        int impacto = random.nextInt(4);
        if (impacto == 0) {
            danoFinal = 0;
            System.out.println("❌ ¡Ataque fallido! No hizo daño.");
        } else if (impacto == 1) {
            danoFinal /= 2;
            System.out.println("🩹 El ataque solo te roza. Daño reducido.");
        } else if (impacto == 2) {
            System.out.println("💥 Te da de lleno. Daño normal.");
        } else {
            danoFinal *= 2;
            System.out.println("🔥 ¡Golpe crítico! Doble daño.");
        }

     // Aplicamos el daño final al personaje que recibe el ataque
        aplicarDanyo(receptor, danoFinal);

        // Mensaje con información del ataque
        System.out.println(atacante.getNombre() + " usó " + ataque.getNombre() + " e hizo " + danoFinal + " de daño.");
        System.out.println("❤️ Vida de " + jugador.getNombre() + ": " + jugador.getVida());
        System.out.println("💀 Vida de " + enemigo.getNombre() + ": " + enemigo.getVida());
        } else {
        // Si no tiene ataques, mostramos un mensaje
        System.out.println(atacante.getNombre() + " no tiene ataques disponibles.");
        }
        
    }

    //seleccionar ataque al juagador
    private Ataque seleccionarAtaqueJugador(ArrayList<Ataque> ataques) {
        System.out.println("Elige tu ataque:");
        
     // Mostramos la lista de ataques
        for (int i = 0; i < ataques.size(); i++) {
            Ataque a = ataques.get(i);
            System.out.println((i + 1) + ". " + a.getNombre() + " (Daño: " + a.getDano() + ")");
        }

        // El jugador elige el ataque
        int seleccion = sc.nextInt() - 1;

        // Si se pasa de rango, se elige el primero
        if (seleccion < 0 || seleccion >= ataques.size()) {
            System.out.println("❌ Selección inválida. Se usará el primer ataque.");
            seleccion = 0;
        }

        return ataques.get(seleccion);
    }

 // Usa un objeto aleatorio del bolsillo mágico
    private void usarBolsilloMagico() {
        try {
            // Sacamos un objeto aleatorio
            Bolsillo objeto = DaoBolsillo.getInstance().obtenerObjetoAleatorio();

            // Si hay un objeto, lo usamos
            if (objeto != null) {
                System.out.println("✨ Has sacado: " + objeto.getNombre());
                System.out.println("📄 " + objeto.getDescripcion());
                System.out.println("🗣️ " + objeto.getFrase());

                // Si hace daño, se lo restamos al enemigo
                if (objeto.getDano() > 0) {
                    int nuevaVida = Math.max(0, enemigo.getVida() - objeto.getDano());
                    enemigo.setVida(nuevaVida);
                    System.out.println("💥 El objeto causó " + objeto.getDano() + " de daño al enemigo.");
                }

            } else {
                // Si no hay objeto
                System.out.println("🔮 El bolsillo está vacío.");
            }

        } catch (Exception e) {
            System.out.println("Error al usar el bolsillo mágico: " + e.getMessage());
        }
    }

 // El jugador usa un objeto de su mochila para curarse
    private void usarObjetoDeMochila() {
        try {
            // Cargamos los objetos de la mochila
            ArrayList<Objeto> mochila = DoaMochila.getInstance().obtenerObjetosDeMochila(jugador.getId());

            // Si hay objetos en la mochila
            if (mochila != null && !mochila.isEmpty()) {
                System.out.println("📦 Objetos disponibles:");

                // Mostramos los objetos
                for (int i = 0; i < mochila.size(); i++) {
                    Objeto o = mochila.get(i);
                    System.out.println((i + 1) + ". " + o.getNombre() + " - " + o.getDescripcion() + " (Poder: " + o.getPoder() + ")");
                }

                // Pedimos la selección
                System.out.print("Elige un objeto: ");
                int seleccion = sc.nextInt() - 1;

                // Si es válida, se usa el objeto
                if (seleccion >= 0 && seleccion < mochila.size()) {
                    Objeto objeto = mochila.get(seleccion);
                    int vidaActual = jugador.getVida();
                    int vidaRecuperada = Math.min(vidaActual + objeto.getPoder(), jugador.getVidaMax());
                    jugador.setVida(vidaRecuperada);
                    System.out.println("❤️ Recuperas " + (vidaRecuperada - vidaActual) + " de vida.");
                } else {
                    System.out.println("❌ Selección inválida.");
                }

            } else {
                System.out.println("👜 Tu mochila está vacía.");
            }

        } catch (Exception e) {
            System.out.println("Error al acceder a la mochila: " + e.getMessage());
        }
    }

 // Aplica daño a un personaje, sin que la vida baje de 0
    private void aplicarDanyo(Personaje objetivo, int dano) {
        int nuevaVida = objetivo.getVida() - dano;

        // Si la nueva vida es menor que 0, se deja en 0
        if (nuevaVida < 0) {
            nuevaVida = 0;
        }

        objetivo.setVida(nuevaVida);
    }

}
