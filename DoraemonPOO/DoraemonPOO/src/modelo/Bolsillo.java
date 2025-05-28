package modelo;

public class Bolsillo {
	private int id;
	private String nombre;
	private String descripcion;
	private int dano;
	private String frase;
	private String tipo;
	
	public Bolsillo () {
		
	}
	
	public Bolsillo (String nombre, String descripcion, int dano, String frase, String tipo) {
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.dano=dano;
		this.frase=frase;
		this.tipo=tipo;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
	
}
