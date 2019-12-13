package clases;

import java.io.Serializable;

public class Profesion extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2618101052274394257L;
	private String nombreProfesion;
	private int codigoProfesion;
	
	public Profesion(String nombre, String apellido, int edad, String genero, String nombreProfesion,
			int codigoProfesion) {
		super(nombre, apellido, edad, genero);
		this.nombreProfesion = nombreProfesion;
		this.codigoProfesion = codigoProfesion;
	}
	public Profesion(String nombreProfesion, int codigoProfesion) {
		this.nombreProfesion = nombreProfesion;
		this.codigoProfesion = codigoProfesion;
	}
	
	public String getNombreProfesion() {
		return nombreProfesion;
	}
	public void setNombreProfesion(String nombreProfesion) {
		this.nombreProfesion = nombreProfesion;
	}
	public int getCodigoProfesion() {
		return codigoProfesion;
	}
	public void setCodigoProfesion(int codigoProfesion) {
		this.codigoProfesion = codigoProfesion;
	}
	
	@Override
	public String toString() {
		return nombreProfesion + ", Codigo de Profesion: " + codigoProfesion;
	}
	
	
}
