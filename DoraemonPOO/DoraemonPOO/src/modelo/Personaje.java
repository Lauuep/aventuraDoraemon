package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Personaje {
	private int id;
    private String nombre;
    private String tipo;
    private int vida;
    private int vidaMax;
    private ArrayList<Ataque> ataques;
    private ArrayList<Mochila> mochila;
    private int lugarId;
    private int puntos;

    public Personaje() {
    	
    }

    public Personaje(String nombre, int vida, int vidaMax, Ataque ataques, Mochila mochila) {
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMax = vidaMax;
        this.ataques = new ArrayList<>();
        this.mochila = new ArrayList<>();
    }
    
    public Personaje(String nombre, int vida) {
        this.nombre = nombre;
        this.vida = vida;
    }
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}


	public int getVidaMax() {
		return vidaMax;
	}

	public void setVidaMax(int vidaMax) {
		this.vidaMax = vidaMax;
	}

	public ArrayList<Ataque> getAtaques() { 
		return ataques; 
		}
	
	public void setAtaques(ArrayList<Ataque> ataques) { 
		this.ataques = ataques; 
		}
	

	public ArrayList<Mochila> getMochila() {
	    return mochila;
	}

	public void setMochila(ArrayList<Mochila> mochila) {
	    this.mochila = mochila;
	}
	
    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getLugarId() {
		return lugarId;
	}

	public void setLugarId(int lugarId) {
		this.lugarId = lugarId;
	}

	public void agregarObjeto(Mochila objeto) {
        mochila.add(objeto);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	  public void sumarPuntos(int extra) { 
	    	this.puntos += extra; 
	 }
	
	
    
}
