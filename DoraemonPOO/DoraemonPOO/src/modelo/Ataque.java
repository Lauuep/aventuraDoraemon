package modelo;

public class Ataque {
	private int id;
	private int personajeId;
    private String nombre;
    private String descripcion;
    private int dano;
    
	public Ataque() {
	}

    public Ataque(String nombre, String descripcion, int dano) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dano=dano;
    }


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonajeId() {
		return personajeId;
	}

	public void setPersonajeId(int personajeId) {
		this.personajeId = personajeId;
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

}
