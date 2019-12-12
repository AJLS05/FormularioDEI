 package clases;

public class Supervisor extends Persona{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cantidadSubordinados;
	private int codigoDepartamento;
	private String nombreDepartamento;
	
	public Supervisor(String nombre, String apellido, int edad, String genero, int cantidadSubordinados,
			int codigoDepartamento, String nombreDepartamento) {
		super(nombre, apellido, edad, genero);
		this.cantidadSubordinados = cantidadSubordinados;
		this.codigoDepartamento = codigoDepartamento;
		this.nombreDepartamento = nombreDepartamento;
	}

	public int getCantidadSubordinados() {
		return cantidadSubordinados;
	}

	public void setCantidadSubordinados(int cantidadSubordinados) {
		this.cantidadSubordinados = cantidadSubordinados;
	}

	public int getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(int codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	@Override
	public String toString() {
		return "Supervisor [cantidadSubordinados=" + cantidadSubordinados + ", codigoDepartamento=" + codigoDepartamento
				+ ", nombreDepartamento=" + nombreDepartamento + "]";
	}

}
