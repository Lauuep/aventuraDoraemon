
package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import dao.DoaMochila;
import dao.DaoBolsillo;

public class Batalla {
    private Personaje jugador;
    private Personaje enemigo;
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private Clima climaActual;
    private MomentoDia momentoActual;
    

    public Batalla(Personaje jugador, Personaje enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.climaActual = generarClimaAleatorio();
        this.momentoActual = generarMomentoAleatorio();
        
    }
    
    private Clima generarClimaAleatorio() {
        Clima[] climas = Clima.values();
        return climas[random.nextInt(climas.length)];
    }

    private MomentoDia generarMomentoAleatorio() {
        MomentoDia[] momentos = MomentoDia.values();
        return momentos[random.nextInt(momentos.length)];
    }



    public void iniciarCombate() {
        System.out.println("⚔️ ¡Batalla entre " + jugador.getNombre() + " y " + enemigo.getNombre() + "!");
        System.out.println("🌦️ Clima actual: " + climaActual);
        System.out.println("🕒 Momento del día: " + momentoActual);
        boolean turnoJugador = true;

        while (jugador.getVida() > 0 && enemigo.getVida() > 0) {
            if (turnoJugador) {
                turnoDelJugador();
            } else {
                turnoDelEnemigo();
            }
            turnoJugador = !turnoJugador;
        }

        if (jugador.getVida() <= 0) {
            System.out.println("\n💀 Has sido derrotado...");
        } else {
            System.out.println("\n🏆 ¡Has ganado!");
        }
    }

    private void turnoDelJugador() {
        if (jugador.getVida() <= 0) {
            System.out.println("⚠️ No puedes hacer nada, estás sin vida.");
            return;
        }

        System.out.println("\n🎮 Tu turno. Elige una opción:");
        System.out.println("1. Atacar");
        System.out.println("2. Usar bolsillo mágico");
        System.out.println("3. Usar objeto de la mochila");

        int opcion = sc.nextInt();

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
                System.out.println("❌ Opción no válida.");
        }
    }

    private void turnoDelEnemigo() {
        if (enemigo.getVida() <= 0) {
            System.out.println("❌ El enemigo ya no puede atacar.");
        } else {
            System.out.println("\n🤖 Turno de " + enemigo.getNombre());
            realizarAtaque(enemigo, jugador);
        }
    }

    private void realizarAtaque(Personaje atacante, Personaje receptor) {
        ArrayList<Ataque> ataques = atacante.getAtaques();

        if (ataques == null || ataques.isEmpty()) {
            System.out.println(atacante.getNombre() + " no tiene ataques.");
            return;
        }

        Ataque ataque;
        if (atacante == jugador) {
            ataque = seleccionarAtaqueJugador(ataques);
        } else {
            ataque = ataques.get(random.nextInt(ataques.size()));
        }

        int baseDano = ataque.getDano();
        int impacto = random.nextInt(4);
        int danoFinal;

        if (impacto == 0) {
            danoFinal = 0;
            System.out.println("❌ ¡Ataque fallido! No hizo daño.");
        } else if (impacto == 1) {
            danoFinal = baseDano / 2;
            System.out.println("🩹 El ataque solo te roza. Daño reducido.");
        } else if (impacto == 2) {
            danoFinal = baseDano;
            System.out.println("💥 Te da de lleno. Daño normal.");
        } else {
            danoFinal = baseDano * 2;
            System.out.println("🔥 ¡Golpe crítico! Doble daño.");
        }

        // Modificadores por clima
        if (climaActual == Clima.LLUVIA) {
            danoFinal = danoFinal - 1;
            System.out.println("🌧️ La lluvia reduce el daño del ataque (-1).");
        } else if (climaActual == Clima.NIEVE) {
            danoFinal =- 2;
            System.out.println("❄️ La nieve reduce mucho el daño del ataque (-2).");
        }else if (climaActual == Clima.DESPEJADO && climaActual == Clima.NUBOSO) {
        	System.out.println("La climatologia no afecta al ataque.");
        }

        // Modificadores por momento del día
        if (momentoActual == MomentoDia.NOCHE) {
            danoFinal += 2;
            System.out.println("🌙 La noche potencia el ataque (+2).");
        }

        aplicarDanyo(receptor, danoFinal);

        System.out.println(atacante.getNombre() + " usó " + ataque.getNombre() + " e hizo " + danoFinal + " de daño.");
        System.out.println("❤️ Vida de " + jugador.getNombre() + ": " + jugador.getVida() + "/" + jugador.getVidaMax());
        System.out.println("💀 Vida de " + enemigo.getNombre() + ": " + enemigo.getVida() + "/" + enemigo.getVidaMax());
    }

    private Ataque seleccionarAtaqueJugador(ArrayList<Ataque> ataques) {
        System.out.println("Elige tu ataque:");
        for (int i = 0; i < ataques.size(); i++) {
            Ataque a = ataques.get(i);
            System.out.println((i + 1) + ". " + a.getNombre() + " (Daño: " + a.getDano() + ")");
        }

        int seleccion = sc.nextInt() - 1;
        if (seleccion < 0 || seleccion >= ataques.size()) {
            System.out.println("❌ Selección inválida. Se usará el primer ataque.");
            return ataques.get(0);
        }

        return ataques.get(seleccion);
    }

    private void usarBolsilloMagico() {
        try {
            Bolsillo obj = DaoBolsillo.getInstance().obtenerObjetoAleatorio();
            if (obj == null) {
                System.out.println("🔮 El bolsillo está vacío.");
                return;
            }

            System.out.println("✨ Has sacado: " + obj.getNombre());
            System.out.println("📝 " + obj.getDescripcion());
            System.out.println("💬 " + obj.getFrase());

            if (obj.getDano() > 0) {
                enemigo.setVida(Math.max(0, enemigo.getVida() - obj.getDano()));
                System.out.println("💥 El objeto causó " + obj.getDano() + " de daño al enemigo.");
            }

        } catch (Exception e) {
            System.out.println("Error al usar el bolsillo mágico: " + e.getMessage());
        }
    }

    private void usarObjetoDeMochila() {
        try {
            List<Objeto> mochila = DoaMochila.getInstance().obtenerObjetosDeMochila(jugador.getId());
            if (mochila == null || mochila.isEmpty()) {
                System.out.println("👜 Tu mochila está vacía.");
                return;
            }

            System.out.println("📦 Objetos disponibles:");
            for (int i = 0; i < mochila.size(); i++) {
                Objeto o = mochila.get(i);
                System.out.println((i + 1) + ". " + o.getNombre() + " - " + o.getDescripcion() + " (Poder: " + o.getPoder() + ")");
            }

            System.out.print("Elige un objeto: ");
            int seleccion = sc.nextInt() - 1;

            if (seleccion < 0 || seleccion >= mochila.size()) {
                System.out.println("❌ Selección inválida.");
                return;
            }

            Objeto objeto = mochila.get(seleccion);
            int nuevaVida = Math.min(jugador.getVida() + objeto.getPoder(), jugador.getVidaMax());
            System.out.println("❤️ Recuperas " + (nuevaVida - jugador.getVida()) + " puntos de vida.");
            jugador.setVida(nuevaVida);
        } catch (Exception e) {
            System.out.println("Error al acceder a la mochila: " + e.getMessage());
        }
    }

    private void aplicarDanyo(Personaje objetivo, int dano) {
        int nuevaVida = Math.max(0, objetivo.getVida() - dano);
        objetivo.setVida(nuevaVida);
    }

}
