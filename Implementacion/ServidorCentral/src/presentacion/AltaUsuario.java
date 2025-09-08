package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import logica.IUsuario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.geom.Dimension2D;
import java.awt.event.ItemEvent;

public class AltaUsuario extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IUsuario ctrlUsuario;
	private JTextField nicknameField;
	private JTextField emailField;
	private JTextField nombreField;
	private JTextField apellidoField;
	private JTextField nacionalidadField;
	private JTextField nombreEmpresaField;
	private JTextField descripcionField;
	private JTextField linkField;
	private JDateChooser fechaDeNacimientoChooser;
	private String[] listaTiposDeUsuarios = { "Postulante", "Empresa" };
	private JComboBox<String> tiposDeUsuarioCombo;
	private JTextField textFieldContrasenia;
	private JTextField textFieldConfirmacion;

	/**
	 * Create the frame.
	 */
	public AltaUsuario(IUsuario ctrlUsuario) {
		setResizable(true);
		this.ctrlUsuario = ctrlUsuario;
		setClosable(true);
		setTitle("Crear Usuario");
		setBounds(0, 0, 450, 450);
		setMinimumSize(new Dimension(450,450));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 120, 169, 30};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 0;
		getContentPane().add(rigidArea, gbc_rigidArea);
		
		JLabel nickname = new JLabel("Nickname");
		GridBagConstraints gbc_nickname = new GridBagConstraints();
		gbc_nickname.anchor = GridBagConstraints.EAST;
		gbc_nickname.insets = new Insets(0, 0, 5, 5);
		gbc_nickname.gridx = 1;
		gbc_nickname.gridy = 1;
		getContentPane().add(nickname, gbc_nickname);
		
		nicknameField = new JTextField();
		GridBagConstraints gbc_nicknameField = new GridBagConstraints();
		gbc_nicknameField.insets = new Insets(0, 0, 5, 5);
		gbc_nicknameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nicknameField.gridx = 2;
		gbc_nicknameField.gridy = 1;
		getContentPane().add(nicknameField, gbc_nicknameField);
		nicknameField.setColumns(10);
		
		JLabel email = new JLabel("E-mail");
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.anchor = GridBagConstraints.EAST;
		gbc_email.insets = new Insets(0, 0, 5, 5);
		gbc_email.gridx = 1;
		gbc_email.gridy = 2;
		getContentPane().add(email, gbc_email);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 2;
		gbc_emailField.gridy = 2;
		getContentPane().add(emailField, gbc_emailField);
		
		JLabel nombre = new JLabel("Nombre");
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_nombre.gridx = 1;
		gbc_nombre.gridy = 3;
		getContentPane().add(nombre, gbc_nombre);
		
		nombreField = new JTextField();
		GridBagConstraints gbc_nombreField = new GridBagConstraints();
		gbc_nombreField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreField.gridx = 2;
		gbc_nombreField.gridy = 3;
		getContentPane().add(nombreField, gbc_nombreField);
		nombreField.setColumns(10);
		
		JLabel apellido = new JLabel("Apellido");
		GridBagConstraints gbc_apellido = new GridBagConstraints();
		gbc_apellido.anchor = GridBagConstraints.EAST;
		gbc_apellido.insets = new Insets(0, 0, 5, 5);
		gbc_apellido.gridx = 1;
		gbc_apellido.gridy = 4;
		getContentPane().add(apellido, gbc_apellido);
		
		apellidoField = new JTextField();
		GridBagConstraints gbc_apellidoField = new GridBagConstraints();
		gbc_apellidoField.insets = new Insets(0, 0, 5, 5);
		gbc_apellidoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellidoField.gridx = 2;
		gbc_apellidoField.gridy = 4;
		getContentPane().add(apellidoField, gbc_apellidoField);
		apellidoField.setColumns(10);
		
		
		JButton submit = new JButton("Crear Usuario");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAltaUsuario(e);
			}
		});
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		GridBagConstraints gbc_lblContrasenia = new GridBagConstraints();
		gbc_lblContrasenia.anchor = GridBagConstraints.EAST;
		gbc_lblContrasenia.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasenia.gridx = 1;
		gbc_lblContrasenia.gridy = 5;
		getContentPane().add(lblContrasenia, gbc_lblContrasenia);
		
		textFieldContrasenia = new JTextField();
		GridBagConstraints gbc_textFieldContrasenia = new GridBagConstraints();
		gbc_textFieldContrasenia.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldContrasenia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldContrasenia.gridx = 2;
		gbc_textFieldContrasenia.gridy = 5;
		getContentPane().add(textFieldContrasenia, gbc_textFieldContrasenia);
		textFieldContrasenia.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Confirmacion");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 6;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textFieldConfirmacion = new JTextField();
		GridBagConstraints gbc_textFieldConfirmacion = new GridBagConstraints();
		gbc_textFieldConfirmacion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldConfirmacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldConfirmacion.gridx = 2;
		gbc_textFieldConfirmacion.gridy = 6;
		getContentPane().add(textFieldConfirmacion, gbc_textFieldConfirmacion);
		textFieldConfirmacion.setColumns(10);
		
		JLabel tipoUsuario = new JLabel("Tipo de usuario");
		GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
		gbc_tipoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_tipoUsuario.anchor = GridBagConstraints.EAST;
		gbc_tipoUsuario.gridx = 1;
		gbc_tipoUsuario.gridy = 7;
		getContentPane().add(tipoUsuario, gbc_tipoUsuario);
		tiposDeUsuarioCombo = new JComboBox(listaTiposDeUsuarios);
		tiposDeUsuarioCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cambiarTipoUsuario();
			}
				
		});
		tiposDeUsuarioCombo.setSelectedIndex(-1);
		GridBagConstraints gbc_tiposDeUsuarioCombo = new GridBagConstraints();
		gbc_tiposDeUsuarioCombo.insets = new Insets(0, 0, 5, 5);
		gbc_tiposDeUsuarioCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tiposDeUsuarioCombo.gridx = 2;
		gbc_tiposDeUsuarioCombo.gridy = 7;
		getContentPane().add(tiposDeUsuarioCombo, gbc_tiposDeUsuarioCombo);
		
		JLabel nacionalidad = new JLabel("Nacionalidad");
		nacionalidad.setEnabled(false);
		GridBagConstraints gbc_nacionalidad = new GridBagConstraints();
		gbc_nacionalidad.anchor = GridBagConstraints.EAST;
		gbc_nacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_nacionalidad.gridx = 1;
		gbc_nacionalidad.gridy = 8;
		getContentPane().add(nacionalidad, gbc_nacionalidad);
		
		nacionalidadField = new JTextField();
		nacionalidadField.setEnabled(false);
		GridBagConstraints gbc_nacionalidadField = new GridBagConstraints();
		gbc_nacionalidadField.insets = new Insets(0, 0, 5, 5);
		gbc_nacionalidadField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nacionalidadField.gridx = 2;
		gbc_nacionalidadField.gridy = 8;
		getContentPane().add(nacionalidadField, gbc_nacionalidadField);
		nacionalidadField.setColumns(10);
		
		JLabel fechaNacimiento = new JLabel("Fecha de nacimiento");
		fechaNacimiento.setEnabled(false);
		GridBagConstraints gbc_fechaNacimiento = new GridBagConstraints();
		gbc_fechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_fechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_fechaNacimiento.gridx = 1;
		gbc_fechaNacimiento.gridy = 9;
		getContentPane().add(fechaNacimiento, gbc_fechaNacimiento);
		
		fechaDeNacimientoChooser = new JDateChooser();
		GridBagConstraints gbc_fechaDeNacimientoChooser = new GridBagConstraints();
		fechaDeNacimientoChooser.setEnabled(false);
		JTextFieldDateEditor editorFecha = (JTextFieldDateEditor) fechaDeNacimientoChooser.getDateEditor();
		gbc_fechaDeNacimientoChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaDeNacimientoChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaDeNacimientoChooser.gridx = 2;
		gbc_fechaDeNacimientoChooser.gridy = 9;
		getContentPane().add(fechaDeNacimientoChooser, gbc_fechaDeNacimientoChooser);
		editorFecha.setEditable(false);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setEnabled(false);
		GridBagConstraints gbc_descripcion = new GridBagConstraints();
		gbc_descripcion.anchor = GridBagConstraints.EAST;
		gbc_descripcion.insets = new Insets(0, 0, 5, 5);
		gbc_descripcion.gridx = 1;
		gbc_descripcion.gridy = 10;
		getContentPane().add(descripcion, gbc_descripcion);
		
		descripcionField = new JTextField();
		descripcionField.setEnabled(false);
		GridBagConstraints gbc_descripcionField = new GridBagConstraints();
		gbc_descripcionField.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionField.fill = GridBagConstraints.HORIZONTAL;
		gbc_descripcionField.gridx = 2;
		gbc_descripcionField.gridy = 10;
		getContentPane().add(descripcionField, gbc_descripcionField);
		descripcionField.setColumns(10);
		
		JLabel link = new JLabel("Link");
		link.setEnabled(false);
		GridBagConstraints gbc_link = new GridBagConstraints();
		gbc_link.anchor = GridBagConstraints.EAST;
		gbc_link.insets = new Insets(0, 0, 5, 5);
		gbc_link.gridx = 1;
		gbc_link.gridy = 11;
		getContentPane().add(link, gbc_link);
		
		linkField = new JTextField();
		linkField.setEnabled(false);
		GridBagConstraints gbc_linkField = new GridBagConstraints();
		gbc_linkField.insets = new Insets(0, 0, 5, 5);
		gbc_linkField.fill = GridBagConstraints.HORIZONTAL;
		gbc_linkField.gridx = 2;
		gbc_linkField.gridy = 11;
		getContentPane().add(linkField, gbc_linkField);
		linkField.setColumns(10);
		GridBagConstraints gbc_submit = new GridBagConstraints();
		gbc_submit.anchor = GridBagConstraints.EAST;
		gbc_submit.insets = new Insets(0, 0, 5, 5);
		gbc_submit.gridx = 2;
		gbc_submit.gridy = 12;
		getContentPane().add(submit, gbc_submit);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.gridheight = 4;
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_1.gridx = 3;
		gbc_rigidArea_1.gridy = 11;
		getContentPane().add(rigidArea_1, gbc_rigidArea_1);
		
		JLabel nombreEmpresa = new JLabel("Nombre de la empresa");
		nombreEmpresa.setEnabled(false);
		nombreEmpresa.setVisible(false);
		GridBagConstraints gbc_nombreEmpresa = new GridBagConstraints();
		gbc_nombreEmpresa.anchor = GridBagConstraints.EAST;
		gbc_nombreEmpresa.insets = new Insets(0, 0, 0, 5);
		gbc_nombreEmpresa.gridx = 1;
		gbc_nombreEmpresa.gridy = 15;
		getContentPane().add(nombreEmpresa, gbc_nombreEmpresa);
		
		nombreEmpresaField = new JTextField();
		nombreEmpresaField.setEnabled(false);
		nombreEmpresaField.setColumns(10);
		nombreEmpresaField.setVisible(false);
		GridBagConstraints gbc_nombreEmpresaField = new GridBagConstraints();
		gbc_nombreEmpresaField.insets = new Insets(0, 0, 0, 5);
		gbc_nombreEmpresaField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreEmpresaField.gridx = 2;
		gbc_nombreEmpresaField.gridy = 15;
		getContentPane().add(nombreEmpresaField, gbc_nombreEmpresaField);

	}

	
	private void cmdAltaUsuario(ActionEvent e) {
		String tipoDeAltaUsuario = (String) (this.tiposDeUsuarioCombo.getSelectedItem());
		String nickname = this.nicknameField.getText();
		String nombre = this.nombreField.getText();
		String apellido = this.apellidoField.getText();
		String correoElectronico = this.emailField.getText();
		String contrasenia = this.textFieldContrasenia.getText();
		String confirmacion = this.textFieldConfirmacion.getText();
		ArrayList<String> error;
		String mensaje = "Revisar los siguientes campos:\n";
		int cantidadErrores = 0;
		try {
			if(tipoDeAltaUsuario == "Postulante") {
				// El Actor eligio crear un Postulante
				Date fechaNacimiento = this.fechaDeNacimientoChooser.getDate();
				String nacionalidad = this.nacionalidadField.getText();
				error = verificarCampos(true, nickname, correoElectronico, nombre, apellido, nacionalidad, fechaNacimiento, "", contrasenia, confirmacion);
				for(String cadena : error) {
					if(!cadena.isEmpty()) {
						cantidadErrores++;
						mensaje += "-" + cadena + "\n";
					}
				}
				if(cantidadErrores == 0) {
					ctrlUsuario.ingresarPostulante(nickname, nombre, apellido, correoElectronico, fechaNacimiento, nacionalidad, "default.png", contrasenia);
				JOptionPane.showMessageDialog(this, "El Postulante se ha creado con éxito", "Agregar Usuario",
	                    JOptionPane.INFORMATION_MESSAGE);
				}else {
					throw new Exception(mensaje);
				}	
			} else if (tipoDeAltaUsuario == "Empresa") {
				// El Actor eligio crear una Empresa
				//String nombreEmpresa = this.nombreEmpresaField.getText();
				String descripcion = this.descripcionField.getText();
				String link = this.linkField.getText();
				error = verificarCampos(false, nickname, correoElectronico, nombre, apellido, "", null, descripcion, contrasenia, confirmacion);
				for(String cadena : error) {
					if(!cadena.isEmpty()) {
						cantidadErrores++;
						mensaje += "-" + cadena + "\n";
					}
				}
				if(cantidadErrores == 0) {
					ctrlUsuario.ingresarEmpresa(nickname, nombre, apellido, correoElectronico, nickname, descripcion, link, "default.png", contrasenia);
				JOptionPane.showMessageDialog(this, "La Empresa se ha creado con éxito", "Agregar Usuario",
	                    JOptionPane.INFORMATION_MESSAGE);
				}else {
					throw new Exception(mensaje);
				}
			} else {
				throw new Exception("Ningun tipo de Usuario elegido");
			}
			limpiarAltaTipo();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, exc.getMessage(), "Agregar Usuario",
                    JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	private void setEnabledPostulanteFields(Boolean estado) {
		fechaDeNacimientoChooser.setEnabled(estado);
		nacionalidadField.setEnabled(estado);
	}
	
	private void setEnabledEmpresaFields(Boolean estado) {
		//nombreEmpresaField.setEnabled(estado);
		descripcionField.setEnabled(estado);
		linkField.setEnabled(estado);
	}

	// Cambia los campos habilitados acorde a la eleccion del Actor
	private void cambiarTipoUsuario() {
		String tipoDeUsuarioElegido = (String) (tiposDeUsuarioCombo.getSelectedItem());
		if(tipoDeUsuarioElegido == "Postulante") {
			setEnabledPostulanteFields(true);
			setEnabledEmpresaFields(false);
		} else if (tipoDeUsuarioElegido == "Empresa") {
			setEnabledPostulanteFields(false);
			setEnabledEmpresaFields(true);
		}
	}
	private ArrayList<String> verificarCampos(Boolean esPostulante, String nickname, String email, String nombre, String apellido, String nacionalidad, Date fecha, String descripcion, String contrasenia, String confirmacion){
		ArrayList<String> error = new ArrayList<String>();
		error.add((nickname.isEmpty())?"Ingresar nickname":"");
		error.add((email.isEmpty())?"Ingresar email":"");
		error.add((nombre.isEmpty())?"Ingresar nombre":"");
		error.add((apellido.isEmpty())?"Ingresar apellido":"");
		if(esPostulante) {
			error.add((nacionalidad.isEmpty())?"Ingresar nacionalidad":"");
			error.add((fecha == null)?"Ingresar una Fecha":"");	
		}else {
			error.add((descripcion.isEmpty())?"Ingresar descripción":"");
		}
		error.add((contrasenia.isEmpty()?"Ingresar contraseña":""));
		error.add((contrasenia.isEmpty()?"Ingresar confirmacion":""));
		error.add(!(contrasenia.equals(confirmacion))?"La contraseña y la confirmacion deben ser iguales":"");
		return error;
	}

	private void limpiarAltaTipo() {
		tiposDeUsuarioCombo.setSelectedIndex(-1);
		setEnabledPostulanteFields(false);
		setEnabledEmpresaFields(false);
		// Campos de Usuario
		nicknameField.setText("");
		emailField.setText("");
		nombreField.setText("");
		apellidoField.setText("");
		// Campos de Postulante
		nacionalidadField.setText("");
		fechaDeNacimientoChooser.setDate(null);
		// Campos de Empresa
		descripcionField.setText("");
		//nombreEmpresaField.setText("");
		linkField.setText("");
		textFieldConfirmacion.setText("");
		textFieldContrasenia.setText("");
		
	};
}
