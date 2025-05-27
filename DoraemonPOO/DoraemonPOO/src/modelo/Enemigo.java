package modelo;

import java.util.ArrayList;

public class Enemigo {
	private int id;
	private String nombre;
	private int vida;
	private int vidaMaxima;
	private ArrayList<Ataque> ataques;
	
	
	public Enemigo () {
		
	}

	public Enemigo (String nombre, int vidaActual, int vidaMaxima, Ataque ataques) {
		this.nombre=nombre;
		this.vida=vida;
		this.vidaMaxima=vidaMaxima;
		this.ataques= new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	public ArrayList<Ataque> getAtaques() {
		return ataques;
	}

	public void setAtaques(ArrayList<Ataque> ataques) {
		this.ataques = ataques;
	}



}
