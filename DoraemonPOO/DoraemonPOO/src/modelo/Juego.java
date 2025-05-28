package modelo;

import java.util.Random;
import java.util.Scanner;

public class Juego {

	boolean resultado = false;
	
	public Juego() {
		
	}
	
	public int jugarBarquitos() {
		Random randomBarco = new Random();
		Scanner sc = new Scanner(System.in);
		int filas = 3;
		int columnas = 3;
		int[][] tablero = new int[filas][columnas];
		// variables para colocar los barquitos en modo random
		int fila;
		int columna;
		int barcos = 2;

		int disparos = 0;
		int fallos = 0;
		
		// Colocar 3 barcos en posiciones aleatorias
				for (int i = 0; i < barcos; i++) {
					do {
						// generamos las coordenadas random para colocar los barquitos. Y el bucle este
						// lo va a repetir 3 veces, ya que colocaremos 3 barcos de 1
						fila = randomBarco.nextInt(filas); // filas es igual a 5, por lo que nos generara un aleatorio entre 0 y
															// 5, q son las posiciones de array del tablero
						columna = randomBarco.nextInt(columnas);
					} while (tablero[fila][columna] == 1); // Asegurarse de no colocar dos barcos en el mismo lugar

					tablero[fila][columna] = 1; // 1 representa un barco
				}


				// se repite hasta que todos los barcos se hayan hundido o falles 4 veces
				while (barcos > 0 && fallos < 4) {
					// Dibuja el tablero para ver el progreso
					System.out.println("");
					System.out.println("Tablero actual:");
					for (int i = 0; i < filas; i++) {
						for (int j = 0; j < columnas; j++) {
							// -1 = x disparo fallido
							// 2 = O barco hundido
							// lo demas = #
							if (tablero[i][j] == -1) {
								System.out.print("X "); // Disparo fallido
							} else if (tablero[i][j] == 2) {
								System.out.print("O "); // Barco hundido
							} else {
								System.out.print("# "); // Agua sin descubrir
							}
						}
						System.out.println();
					}

					// Pedir al jugador que introduzca las coordenadas
					System.out.print("\nIntroduce la fila (1 a 3): ");
					int filaDisparo = sc.nextInt();
					filaDisparo = filaDisparo - 1;
					System.out.print("Introduce la columna (1 a 3): ");
					int columnaDisparo = sc.nextInt();
					columnaDisparo = columnaDisparo - 1;

					// Verificar el resultado del disparo
					if (tablero[filaDisparo][columnaDisparo] == 1) {
						System.out.println("¡Impacto! Hundiste un barco.");
						tablero[filaDisparo][columnaDisparo] = 2; // Marcar el barco como hundido
						barcos--; // Reducir el número de barcos restantes
					} else if (tablero[filaDisparo][columnaDisparo] == 0) {
						System.out.println("Agua. No hay barco en esta posición.");
						tablero[filaDisparo][columnaDisparo] = -1; // Marcar como agua
						fallos++;
					} else {
						System.out.println("Ya disparaste en esta posición. Inténtalo en otro lugar.");
					}

					disparos++; // Contar el número de disparos realizados
				}

				// Final del juego
				// System.out.println("\n¡Felicidades! Hundiste todos los barcos en " + disparos
				// + " disparos.");

				// Mostrar tablero final
				System.out.println("Tablero final:");
				for (int i = 0; i < filas; i++) {
					for (int j = 0; j < columnas; j++) {
						if (tablero[i][j] == -1) {
							System.out.print("X "); // Disparo fallido
						} else if (tablero[i][j] == 2) {
							System.out.print("O "); // Barco hundido
						} else if (tablero[i][j] == 1) {
							System.out.print("B "); // Barco que quedó sin descubrir
						} else {
							System.out.print("~ "); // Agua sin descubrir
						}
					}
					System.out.println();
				}
				return fallos;
				/*if (fallos >= 4) {
					System.out.println("La KARPA KOI te ha vencido.");
					System.out.println("Chao pescao");
					return false;
				} else {
					System.out.println("\n¡Felicidades! Hundiste todos los barcos en " + disparos + " disparos.");
					System.out.println("Recibes el CRISTAL DE LAS AGUAS SERENAS");
					System.out.println("La función de este cristal es limpiar pequeñas áreas de agua contaminada y restaurar el ecosistema.\n"
									+ "Es decir, hace que las aguas vuelvan a ser transparentes y seguras para los peces koi. ");
					
					if (opcionPersonaje==1) {
						recompensas[3]="CRISTAL AGUAS SERENAS";
					}else if (opcionPersonaje==2) {
						recompensas[2]="CRISTAL AGUAS SERENAS";
					}else if (opcionPersonaje==3) {
						recompensas[2]="CRISTAL AGUAS SERENAS";
					}else if (opcionPersonaje==4) {
						recompensas[1]="CRISTAL AGUAS SERENAS";
					}
				return true;
				}*/
			}// fin termas
	public String ifoFoko() {
		return "Llegas a lo que parece ser un lago termal, o por lo menos lo fue en el pasado.\n"
				+ "Los alrededores del lago están en ruinas.\n"
				+ "El agua que alguna vez fue cristalina y hogar de innumerables carpas koi, ahora es una masa de agua turbia y opaca.\n"
				+ "Las carpas, en su mayoría, flotan en la superficie, muertas, y algunas pocas nadan débilmente, cubiertas de lodo espeso.\n"
				+ "\nPara continuar, debes cruzar sobre los fragiles nenuzares que quedan,evitando caer al agua (no parece muy salubre)\n"
				+ "Debes mantener la calma y evitar las carpas muertas que flotan en la superficie."
				+ "\n Si logras cruzar, obtendrás el Cristal de las Aguas Serenas.\n"
				+ "Este cristal es capaz de limpiar pequeñas áreas de agua y restaurar el ecosistema.\n"
				+ "Es decir, hace que las aguas vuelvan  a ser transparentes y seguras para los peces koi.";
	}
	public void irfukuota(int fallos) {
		ifoFoko();
		
		jugarBarquitos();
		
		
		if(fallos>5) {
			
		}
		
		//batalla pequeña
	}
}
