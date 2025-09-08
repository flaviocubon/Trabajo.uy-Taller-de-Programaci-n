package presentacion;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import logica.CtrlUsuario;
import logica.DTEmpresa;
import logica.DTPostulante;
import logica.DTUsuario;
import logica.IUsuario;

public class ModificarUsuario extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IUsuario IUs;
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
	private JComboBox lNicknames;

	public ModificarUsuario(IUsuario IUs) {
		this.IUs = IUs;
		setClosable(true);
		setTitle("Modificar Usuario");
		setBounds(0, 0, 400, 400);
        setMinimumSize(new Dimension(400,400));
        setResizable(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 120, 169, 30};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
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
		
		lNicknames = new JComboBox();
		GridBagConstraints gbc_lNicknames = new GridBagConstraints();
		gbc_lNicknames.insets = new Insets(0, 0, 5, 5);
		gbc_lNicknames.fill = GridBagConstraints.HORIZONTAL;
		gbc_lNicknames.gridx = 2;
		gbc_lNicknames.gridy = 1;
		getContentPane().add(lNicknames, gbc_lNicknames);
		lNicknames.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (e.getStateChange() == ItemEvent.SELECTED && lNicknames.getSelectedIndex() != 0) {
        			cargarInfoUser((String) lNicknames.getSelectedItem());
        		}else if(lNicknames.getSelectedIndex() == 0) {
        			limpiarMod();
        		}
        	}
        });
		
		JLabel email = new JLabel("E-mail");
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.anchor = GridBagConstraints.EAST;
		gbc_email.insets = new Insets(0, 0, 5, 5);
		gbc_email.gridx = 1;
		gbc_email.gridy = 2;
		getContentPane().add(email, gbc_email);
		
		emailField = new JTextField();
		emailField.setEditable(false);
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
		
		JLabel tipoUsuario = new JLabel("Tipo de usuario");
		GridBagConstraints gbc_tipoUsuario = new GridBagConstraints();
		gbc_tipoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_tipoUsuario.anchor = GridBagConstraints.EAST;
		gbc_tipoUsuario.gridx = 1;
		gbc_tipoUsuario.gridy = 5;
		getContentPane().add(tipoUsuario, gbc_tipoUsuario);
		tiposDeUsuarioCombo = new JComboBox(listaTiposDeUsuarios);
		tiposDeUsuarioCombo.setEnabled(false);
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
		gbc_tiposDeUsuarioCombo.gridy = 5;
		getContentPane().add(tiposDeUsuarioCombo, gbc_tiposDeUsuarioCombo);
		
		JLabel nacionalidad = new JLabel("Nacionalidad");
		nacionalidad.setEnabled(false);
		GridBagConstraints gbc_nacionalidad = new GridBagConstraints();
		gbc_nacionalidad.anchor = GridBagConstraints.EAST;
		gbc_nacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_nacionalidad.gridx = 1;
		gbc_nacionalidad.gridy = 6;
		getContentPane().add(nacionalidad, gbc_nacionalidad);
		
		nacionalidadField = new JTextField();
		nacionalidadField.setEnabled(false);
		GridBagConstraints gbc_nacionalidadField = new GridBagConstraints();
		gbc_nacionalidadField.insets = new Insets(0, 0, 5, 5);
		gbc_nacionalidadField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nacionalidadField.gridx = 2;
		gbc_nacionalidadField.gridy = 6;
		getContentPane().add(nacionalidadField, gbc_nacionalidadField);
		nacionalidadField.setColumns(10);
		
		JLabel fechaNacimiento = new JLabel("Fecha de nacimiento");
		fechaNacimiento.setEnabled(false);
		GridBagConstraints gbc_fechaNacimiento = new GridBagConstraints();
		gbc_fechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_fechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_fechaNacimiento.gridx = 1;
		gbc_fechaNacimiento.gridy = 7;
		getContentPane().add(fechaNacimiento, gbc_fechaNacimiento);
		
		fechaDeNacimientoChooser = new JDateChooser();
		GridBagConstraints gbc_fechaDeNacimientoChooser = new GridBagConstraints();
		fechaDeNacimientoChooser.setEnabled(false);
		JTextFieldDateEditor editorFecha = (JTextFieldDateEditor) fechaDeNacimientoChooser.getDateEditor();
		editorFecha.setEditable(false);
		gbc_fechaDeNacimientoChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaDeNacimientoChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaDeNacimientoChooser.gridx = 2;
		gbc_fechaDeNacimientoChooser.gridy = 7;
		getContentPane().add(fechaDeNacimientoChooser, gbc_fechaDeNacimientoChooser);
		
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setEnabled(false);
		GridBagConstraints gbc_descripcion = new GridBagConstraints();
		gbc_descripcion.anchor = GridBagConstraints.EAST;
		gbc_descripcion.insets = new Insets(0, 0, 5, 5);
		gbc_descripcion.gridx = 1;
		gbc_descripcion.gridy = 8;
		getContentPane().add(descripcion, gbc_descripcion);
		
		descripcionField = new JTextField();
		descripcionField.setEnabled(false);
		GridBagConstraints gbc_descripcionField = new GridBagConstraints();
		gbc_descripcionField.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionField.fill = GridBagConstraints.HORIZONTAL;
		gbc_descripcionField.gridx = 2;
		gbc_descripcionField.gridy = 8;
		getContentPane().add(descripcionField, gbc_descripcionField);
		descripcionField.setColumns(10);
		
		JLabel link = new JLabel("Link");
		link.setEnabled(false);
		GridBagConstraints gbc_link = new GridBagConstraints();
		gbc_link.anchor = GridBagConstraints.EAST;
		gbc_link.insets = new Insets(0, 0, 5, 5);
		gbc_link.gridx = 1;
		gbc_link.gridy = 9;
		getContentPane().add(link, gbc_link);
		
		JButton submit = new JButton("Guardar");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdModificarDatos(e);
			}
		});
		
		linkField = new JTextField();
		linkField.setEnabled(false);
		GridBagConstraints gbc_linkField = new GridBagConstraints();
		gbc_linkField.insets = new Insets(0, 0, 5, 5);
		gbc_linkField.fill = GridBagConstraints.HORIZONTAL;
		gbc_linkField.gridx = 2;
		gbc_linkField.gridy = 9;
		getContentPane().add(linkField, gbc_linkField);
		linkField.setColumns(10);
		GridBagConstraints gbc_submit = new GridBagConstraints();
		gbc_submit.anchor = GridBagConstraints.EAST;
		gbc_submit.insets = new Insets(0, 0, 5, 5);
		gbc_submit.gridx = 2;
		gbc_submit.gridy = 11;
		getContentPane().add(submit, gbc_submit);
		
		JLabel nombreEmpresa = new JLabel("Nombre de la empresa");
		nombreEmpresa.setEnabled(false);
		nombreEmpresa.setVisible(false);
		GridBagConstraints gbc_nombreEmpresa = new GridBagConstraints();
		gbc_nombreEmpresa.anchor = GridBagConstraints.EAST;
		gbc_nombreEmpresa.insets = new Insets(0, 0, 0, 5);
		gbc_nombreEmpresa.gridx = 1;
		gbc_nombreEmpresa.gridy = 12;
		getContentPane().add(nombreEmpresa, gbc_nombreEmpresa);
		
		nombreEmpresaField = new JTextField();
		nombreEmpresaField.setEnabled(false);
		nombreEmpresaField.setColumns(10);
		nombreEmpresaField.setVisible(false);
		GridBagConstraints gbc_nombreEmpresaField = new GridBagConstraints();
		gbc_nombreEmpresaField.insets = new Insets(0, 0, 0, 5);
		gbc_nombreEmpresaField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreEmpresaField.gridx = 2;
		gbc_nombreEmpresaField.gridy = 12;
		getContentPane().add(nombreEmpresaField, gbc_nombreEmpresaField);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.gridx = 3;
		gbc_rigidArea_1.gridy = 12;
		getContentPane().add(rigidArea_1, gbc_rigidArea_1);

	}

	
	private void cmdModificarDatos(ActionEvent e) {
		try {
			if(lNicknames.getSelectedIndex() != 0) {
				boolean esPostulante = (tiposDeUsuarioCombo.getSelectedItem() == "Postulante");
				String tipoDeAltaUsuario = (String) (this.tiposDeUsuarioCombo.getSelectedItem());
				String nickname = (String) lNicknames.getSelectedItem();
				String email = emailField.getText();
				String nombre = nombreField.getText();
				String apellido = apellidoField.getText();
				Date fecha = fechaDeNacimientoChooser.getDate();
				String descripcion = descripcionField.getText();
				String link = linkField.getText();
				Date fechaNacimiento = this.fechaDeNacimientoChooser.getDate();
				String nacionalidad = this.nacionalidadField.getText();
				ArrayList<String> error;
				int cantidadErrores = 0;
				String mensaje = "Revisar los siguientes campos:\n";
		
				if(tipoDeAltaUsuario == "Postulante") {
					// El Actor eligio crear un Postulante
					error = verificarCampos(true, nickname, email, nombre, apellido, nacionalidad, fechaNacimiento, "");
					for(String cadena : error) {
						if(!cadena.isEmpty()) {
							cantidadErrores++;
							mensaje += "-" + cadena + "\n";
						}
					}
					if(cantidadErrores == 0) {
						IUs.editarDatosBasicos(nickname, email, nombre, apellido, nacionalidad, fecha, descripcion, link);
						JOptionPane.showMessageDialog(this, "Se han cambiado los datos con exito", "Modificar Datos de Usuario",
	                    JOptionPane.INFORMATION_MESSAGE);
					}else {
						throw new Exception(mensaje);
					}	
				} else if (tipoDeAltaUsuario == "Empresa") {
					// El Actor eligio crear una Empresa
					//String nombreEmpresa = this.nombreEmpresaField.getText();
					error = verificarCampos(false, nickname, email, nombre, apellido, "", null, descripcion);
					for(String cadena : error) {
						if(!cadena.isEmpty()) {
							cantidadErrores++;
							mensaje += "-" + cadena + "\n";
						}
					}
					if(cantidadErrores == 0) {
						IUs.editarDatosBasicos(nickname, email, nombre, apellido, nacionalidad, fecha, descripcion, link);
						JOptionPane.showMessageDialog(this, "Se han cambiado los datos con exito", "Modificar Datos de Usuario",
	                    JOptionPane.INFORMATION_MESSAGE);
					}else {
						throw new Exception(mensaje);
					}
				} else {
					throw new Exception("Ningun tipo de Usuario elegido");
				}
				limpiarMod();
				lNicknames.setSelectedIndex(0);
			}else {
				throw new Exception("Debe seleccionar un usuario para modificar.");
			}
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
		nombreEmpresaField.setEnabled(estado);
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
	
	public void update(){
		limpiarMod();
		ArrayList<String> listaNombres = (ArrayList<String>) IUs.listarUsuarios();
		listaNombres.add(0, "Seleccionar");
		String [] nicknames= listaNombres.toArray(new String[0]);
		lNicknames.setModel(new DefaultComboBoxModel<String> (nicknames));
		lNicknames.setSelectedIndex(0);
	}
	
	@SuppressWarnings("deprecation")
	public void cargarInfoUser(String nombre) {
		DTUsuario dataUser = IUs.mostrarDatosUsuario(nombre);
		emailField.setText(dataUser.getMail());
		nombreField.setText(dataUser.getNombre());
		apellidoField.setText(dataUser.getApellido());
		if (IUs.listarEmpresas().contains((String) dataUser.getNickname())){
			tiposDeUsuarioCombo.setSelectedItem((String)"Empresa");
			DTEmpresa dataEmpresa = IUs.getDataEmpresa(nombre);
			nombreEmpresaField.setText(dataEmpresa.getNombreEmpresa());
			descripcionField.setText(dataEmpresa.getDescripcion());
			linkField.setText(dataEmpresa.getLink());
			nacionalidadField.setText("");
			fechaDeNacimientoChooser.setDate(null);
		} else {
			tiposDeUsuarioCombo.setSelectedItem((String) "Postulante");
			DTPostulante dataPostulante = IUs.getDataPostulante(nombre);
			nacionalidadField.setText(dataPostulante.getNacionalidad());
			Date fechaNacimiento = dataPostulante.getFechaNacimiento();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			fechaDeNacimientoChooser.setDate(fechaNacimiento);
			nombreEmpresaField.setText("");
			descripcionField.setText("");
			linkField.setText("");
		}
	}
	
	private ArrayList<String> verificarCampos(Boolean esPostulante, String nickname, String email, String nombre, String apellido, String nacionalidad, Date fecha, String descripcion){
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
		return error;
	}

	
	public void limpiarMod() {
		tiposDeUsuarioCombo.setSelectedIndex(-1);
		setEnabledPostulanteFields(false);
		setEnabledEmpresaFields(false);
		// Campos de Usuario
		emailField.setText("");
		nombreField.setText("");
		apellidoField.setText("");
		// Campos de Postulante
		nacionalidadField.setText("");
		fechaDeNacimientoChooser.setDate(null);
		// Campos de Empresa
		descripcionField.setText("");
		nombreEmpresaField.setText("");
		linkField.setText("");
	};
}
