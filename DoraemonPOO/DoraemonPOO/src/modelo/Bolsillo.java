package modelo;

public class Bolsillo {
	private int id;
	private String nombre;
	private String descripcion;
	private int dano;
	private String frase;
	
	public Bolsillo () {
		
	}
	
	public Bolsillo (String nombre, String descripcion, int dano, String frase) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.dano=dano;
		this.frase=frase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}
	
	
	
	
	
	
}
