package modelo;

public class Lugares {
	private int id;
	private String nombre;
	private String recompensa;
	
	public Lugares() {
		
	}
	
	public Lugares(int id, String nombre, String recompensa) {
		this.id=id;
		this.nombre=nombre;
		this.recompensa=recompensa;
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

	public String getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(String recompensa) {
		this.recompensa = recompensa;
	}
	
	

}
