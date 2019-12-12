package clases;

import java.io.Serializable;

public abstract class Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private int edad;
	private String genero;
	
	public Persona(String nombre, String apellido, int edad, String genero) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
	}
	
	public Persona() {
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", genero=" + genero + "]";
	}

	public Profesion getCarrera() {
		
		return null;
	}
	
}
