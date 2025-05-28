package modelo;

public class Mochila {
	private int id;
    private String nombre;
    private String descripcion;
    private int poder;
    private int cantidad;

    public Mochila () {
    	
    }

    public Mochila(String nombre, String descrpcion, int poder, int cantidad) {
        this.nombre = nombre;
        this.descripcion=descripcion;
        this.poder=poder;
        this.cantidad = cantidad;
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

	public int getPoder() {
		return poder;
	}

	public void setPoder(int poder) {
		this.poder = poder;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
    
    

}
