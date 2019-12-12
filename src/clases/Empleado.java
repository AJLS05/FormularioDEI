package clases;

import java.io.Serializable;

public class Empleado extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Supervisor supervisor;
	private int salario;
	private String estadoContractual;
	private Profesion profesion;
	
	public Empleado(String nombre, String apellido, int edad, String genero, Supervisor supervisor, int salario,
			String estadoContractual, Profesion profesion) {
		super(nombre, apellido, edad, genero);
		this.supervisor = supervisor;
		this.salario = salario;
		this.estadoContractual = estadoContractual;
		this.profesion = profesion;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public String getEstadoContractual() {
		return estadoContractual;
	}

	public void setEstadoContractual(String estadoContractual) {
		this.estadoContractual = estadoContractual;
	}

	public Profesion getProfesion() {
		return profesion;
	}

	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}

	@Override
	public String toString() {
		return "Empleado [supervisor=" + supervisor + ", salario=" + salario + ", estadoContractual="
				+ estadoContractual + ", profesion=" + profesion + "]";
	}
	
}
