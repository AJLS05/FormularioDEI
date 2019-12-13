package application;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import clases.Empleado;
import clases.Profesion;
import clases.Supervisor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ControladorFormulario implements Initializable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObservableList<Profesion> profesiones;
	private ObservableList<Supervisor> supervisores;
	private ObservableList<Empleado> empleados;
	@FXML private TextField nombre;
	@FXML private TextField apellido;
	@FXML private TextField edad;
	@FXML private ToggleGroup grupoGenero;
	@FXML private RadioButton rbtFemenino;
	@FXML private RadioButton rbtMasculino;
	@FXML private ComboBox<Profesion> cboProfesion;
	@FXML private ComboBox<Supervisor> cboSupervisor;
	@FXML private TextField salario;
	@FXML private ToggleGroup grupoEstatus;
	@FXML private RadioButton rbtContratado;
	@FXML private RadioButton rbtDespedido;

	@FXML private Button btnNuevo;
	@FXML private Button btnGuardar;
	@FXML private Button btnActualizar;

	@FXML private ListView<Empleado> lstEmpleados; 
	private ArrayList<String> errores;

	public ControladorFormulario() {
		System.out.println("Se ejecuto el constructor del controlador");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		profesiones = FXCollections.observableArrayList();
		supervisores = FXCollections.observableArrayList();
		empleados = FXCollections.observableArrayList();
		errores = new ArrayList<String>();

		profesiones.add(new Profesion("Secretario", 101));
		profesiones.add(new Profesion("Vicepresidente", 102));
		profesiones.add(new Profesion("Aseador", 103));
		profesiones.add(new Profesion("Recepcionista", 104));
		cboProfesion.setItems(profesiones);

		supervisores.add(new Supervisor("Marcos", "Martinez", 34, "Masculino", 5, 001, "Recursos Humanos"));
		supervisores.add(new Supervisor("Juan", "Gutierrez", 31, "Masculino", 9, 002, "Informatica"));
		supervisores.add(new Supervisor("Daniel", "Baquedano", 29, "Masculino", 8, 003, "Servicios Sanitarios"));
		supervisores.add(new Supervisor("Allison", "Flores", 34, "Femenino", 10, 001, "Seguridad"));
		cboSupervisor.setItems(supervisores);

		lstEmpleados.setItems(empleados);

		lstEmpleados.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Empleado>() {

					@Override
					public void changed(ObservableValue<? extends Empleado> arg0, Empleado valorAnterior,
							Empleado valorActual) {
						System.out.println("Valor actual: "+valorActual);
						if (valorActual!=null) {
							nombre.setText(valorActual.getNombre());
							apellido.setText(valorActual.getApellido());
							edad.setText(String.valueOf(valorActual.getEdad()));
							if (valorActual.getGenero().equals("Femenino"))
								rbtFemenino.setSelected(true);
							if (valorActual.getGenero().equals("Masculino"))
								rbtMasculino.setSelected(true);
							cboProfesion.getSelectionModel().select(valorActual.getProfesion());
							cboSupervisor.getSelectionModel().select(valorActual.getSupervisor());
							salario.setText(String.valueOf(valorActual.getSalario()));
							if (valorActual.getEstadoContractual().equals("Contratado"))
								rbtContratado.setSelected(true);
							if (valorActual.getEstadoContractual().equals("Despedido"))
								rbtDespedido.setSelected(true);
							btnGuardar.setDisable(true);
							btnActualizar.setDisable(false);
						}
					}
				}
				);
		leerInformacionArchivo();
	}

	public void leerInformacionArchivo(){
		try {
			ObjectInputStream archivo = new ObjectInputStream(new FileInputStream("empleados.data"));
			try {
				while (true) {
					Empleado e = (Empleado)archivo.readObject();
					empleados.add(e);
				}
			}catch(EOFException e) {
				System.out.println("Fin del archivo");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			archivo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void guardar() {
		validar();
		if (errores.size()>0) {
			//Copiado y pgado PROOOOOOO!!
			Alert mensaje = new Alert(AlertType.WARNING);
			String cadenaErrores = "";
			for (int i=0;i<errores.size();i++)
				cadenaErrores+=errores.get(i)+"\n";
			
			mensaje.setTitle("Error al guardar");
			mensaje.setContentText(cadenaErrores);
			mensaje.setHeaderText("Se encontraron los siguientes errores: ");
			mensaje.show();
			return; 
		}
		Empleado persona = new Empleado(
				nombre.getText(),
				apellido.getText(),
				Integer.parseInt(edad.getText()),
				((RadioButton)grupoGenero.getSelectedToggle()).getText(),
				cboSupervisor.getSelectionModel().getSelectedItem(),
				Integer.parseInt(salario.getText()),
				((RadioButton)grupoEstatus.getSelectedToggle()).getText(),
				cboProfesion.getSelectionModel().getSelectedItem()
				);
		empleados.add(persona);
		guardarObjetosArchivo();
	}
	
	public void guardarObjetosArchivo() {
		try {
			ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream("empleados.data"));
			for (int i=0;i<empleados.size();i++) {
				archivo.writeObject(empleados.get(i));			
			}
			archivo.flush();
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void validar() {
		errores.clear();
		//Palabras sin acento por que YOLO
		//Copiado y pegado prroooo!!
		if (!edad.getText().isEmpty()) {
			try {
				Integer.parseInt(edad.getText());
			}catch(NumberFormatException e) {
				errores.add("La edad debe ser numerico");
			}
		}	
		
		if (nombre.getText().isEmpty())
			errores.add("El campo nombre esta vacio");
		if (apellido.getText().isEmpty())
			errores.add("El campo apellido esta vacio");
		if (edad.getText().isEmpty())
			errores.add("El campo edad esta vacio");
		//System.out.println(grupoGenero.getSelectedToggle());
		if (grupoGenero.getSelectedToggle()==null)
			errores.add("El campo genero esta vacio");
		if (cboSupervisor.getSelectionModel().getSelectedItem() == null)
			errores.add("El campo supervisor esta vacio");
		if (salario.getText().isEmpty())
			errores.add("El campo salario esta vacio (mejor para nosotros :v)");
		if (grupoEstatus.getSelectedToggle()==null)
			errores.add("El campo estatus esta vacio");
		if (cboProfesion.getSelectionModel().getSelectedItem() == null)
			errores.add("El campo profesion esta vacio");
	}

	@FXML
	private void nuevo() {
		nombre.clear();
		apellido.clear();
		edad.clear();
		cboSupervisor.getSelectionModel().clearSelection();
		salario.clear();
		cboProfesion.getSelectionModel().clearSelection();
		btnActualizar.setDisable(true);
		btnGuardar.setDisable(false);
		rbtFemenino.setSelected(false);
		rbtMasculino.setSelected(false);
		rbtContratado.setSelected(false);
		rbtDespedido.setSelected(false);
		}

	@FXML
	public void actualizar() {
		empleados.set(lstEmpleados.getSelectionModel().getSelectedIndex(), 
				 new Empleado(
						nombre.getText(),
						apellido.getText(),
						Integer.parseInt(edad.getText()),
						((RadioButton)grupoGenero.getSelectedToggle()).getText(),
						cboSupervisor.getSelectionModel().getSelectedItem(),
						Integer.parseInt(salario.getText()),
						((RadioButton)grupoEstatus.getSelectedToggle()).getText(),
						cboProfesion.getSelectionModel().getSelectedItem()
						)
				);
	}

}
