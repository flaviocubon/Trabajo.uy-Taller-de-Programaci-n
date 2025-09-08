package presentacion;

import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import logica.DTEmpresa;
import logica.DTOfertaLaboral;
import logica.DTPostulante;
import logica.DTUsuario;
import logica.IUsuario;

public class ConsultaUsuario extends JInternalFrame {
	private IUsuario IU;
	private JComboBox lNicknames;
	private JTextArea cNombre;
	private JTextArea cApellido;
	private JTextArea cCorreo;
	private JTextArea cNacionalidad;
	private JTextArea cNacimiento;
	private JTextArea cEmpresa;
	private JTextArea cDescripcion;
	private JTextArea cLink;
	private JComboBox lOfertasPostulante;
	private JComboBox lOfertasEmpresa;
	private ConsultaOferta frameOferta;

	public ConsultaUsuario(IUsuario IUs, ConsultaOferta frmConsultaOferta) {
		setClosable(true);
		IU=IUs;
		frameOferta = frmConsultaOferta;
		setResizable(true);
		setTitle("Consulta de Usuario");
		setBounds(0, 0, 500, 500);
		setMinimumSize(new Dimension(500,500));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Nombre del Usuario:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		lNicknames = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 0;
		lNicknames.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameOferta.limpiarCampos();
				frameOferta.setVisible(false);
	            JComboBox<String> source = (JComboBox<String>) e.getSource();
	            String selectedOption = (String) source.getSelectedItem();
	            seleccionarNickname(selectedOption);
			}
		});
		getContentPane().add(lNicknames, gbc_comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		cNombre = new JTextArea();
		cNombre.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 3;
		gbc_textArea.gridy = 2;
		getContentPane().add(cNombre, gbc_textArea);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		cApellido = new JTextArea();
		cApellido.setEditable(false);
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 3;
		gbc_textArea_1.gridy = 3;
		getContentPane().add(cApellido, gbc_textArea_1);
		
		JLabel lblNewLabel_3 = new JLabel("Correo electronico:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		cCorreo = new JTextArea();
		cCorreo.setEditable(false);
		GridBagConstraints gbc_textArea_1_1 = new GridBagConstraints();
		gbc_textArea_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1_1.gridx = 3;
		gbc_textArea_1_1.gridy = 4;
		getContentPane().add(cCorreo, gbc_textArea_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Nacionalidad:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 5;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		cNacionalidad = new JTextArea();
		cNacionalidad.setEditable(false);
		GridBagConstraints gbc_textArea_1_2 = new GridBagConstraints();
		gbc_textArea_1_2.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1_2.fill = GridBagConstraints.BOTH;
		gbc_textArea_1_2.gridx = 3;
		gbc_textArea_1_2.gridy = 5;
		getContentPane().add(cNacionalidad, gbc_textArea_1_2);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 6;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		cNacimiento = new JTextArea();
		cNacimiento.setEditable(false);
		GridBagConstraints gbc_textArea_1_3 = new GridBagConstraints();
		gbc_textArea_1_3.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1_3.fill = GridBagConstraints.BOTH;
		gbc_textArea_1_3.gridx = 3;
		gbc_textArea_1_3.gridy = 6;
		getContentPane().add(cNacimiento, gbc_textArea_1_3);
		
		JLabel lblNewLabel_10 = new JLabel("Ofertas laborales a las que se postulo:");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 7;
		getContentPane().add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		lOfertasPostulante = new JComboBox();
		GridBagConstraints gbc_lPostulaciones_1 = new GridBagConstraints();
		gbc_lPostulaciones_1.insets = new Insets(0, 0, 5, 0);
		gbc_lPostulaciones_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lPostulaciones_1.gridx = 3;
		gbc_lPostulaciones_1.gridy = 7;
		lOfertasPostulante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameOferta.limpiarCampos();
				frameOferta.setVisible(false);
	            JComboBox<String> source = (JComboBox<String>) e.getSource();
	            String selectedOption = (String) source.getSelectedItem();
	            seleccionarOferta(selectedOption);
			}
		});
		getContentPane().add(lOfertasPostulante, gbc_lPostulaciones_1);
		
		JLabel lblNewLabel_7 = new JLabel("Descripcion");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 8;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		cDescripcion = new JTextArea();
		cDescripcion.setEditable(false);
		GridBagConstraints gbc_textArea_1_5 = new GridBagConstraints();
		gbc_textArea_1_5.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1_5.fill = GridBagConstraints.BOTH;
		gbc_textArea_1_5.gridx = 3;
		gbc_textArea_1_5.gridy = 8;
		getContentPane().add(cDescripcion, gbc_textArea_1_5);
		
		JLabel lblNewLabel_8 = new JLabel("Link:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 9;
		getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		cLink = new JTextArea();
		cLink.setEditable(false);
		GridBagConstraints gbc_textArea_1_6 = new GridBagConstraints();
		gbc_textArea_1_6.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1_6.fill = GridBagConstraints.BOTH;
		gbc_textArea_1_6.gridx = 3;
		gbc_textArea_1_6.gridy = 9;
		getContentPane().add(cLink, gbc_textArea_1_6);
		
				lOfertasEmpresa = new JComboBox();
				GridBagConstraints gbc_lPostulaciones_2 = new GridBagConstraints();
				gbc_lPostulaciones_2.insets = new Insets(0, 0, 5, 0);
				gbc_lPostulaciones_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_lPostulaciones_2.gridx = 3;
				gbc_lPostulaciones_2.gridy = 10;
				lOfertasEmpresa.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frameOferta.limpiarCampos();
						frameOferta.setVisible(false);
	            JComboBox<String> source = (JComboBox<String>) e.getSource();
	            String selectedOption = (String) source.getSelectedItem();
	            seleccionarOferta(selectedOption);
					}
				});
				
				JLabel lblNewLabel_11 = new JLabel("Ofertas Laborales de la empresa:");
				GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
				gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_11.gridx = 0;
				gbc_lblNewLabel_11.gridy = 10;
				getContentPane().add(lblNewLabel_11, gbc_lblNewLabel_11);
				getContentPane().add(lOfertasEmpresa, gbc_lPostulaciones_2);
		
		cEmpresa = new JTextArea();
		cEmpresa.setEditable(false);
		cEmpresa.setVisible(false);
		
		JLabel lblNewLabel_6 = new JLabel("Nombre de la empresa:");
		lblNewLabel_6.setVisible(false);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 12;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		GridBagConstraints gbc_textArea_1_4 = new GridBagConstraints();
		gbc_textArea_1_4.fill = GridBagConstraints.BOTH;
		gbc_textArea_1_4.gridx = 3;
		gbc_textArea_1_4.gridy = 12;
		getContentPane().add(cEmpresa, gbc_textArea_1_4);
	}
	public void update(){
		limpiarDatosUnicos();
		String [] nicknames= IU.listarUsuarios().toArray(new String[0]);
		lNicknames.setModel(new DefaultComboBoxModel<String> (nicknames));
	}
	public void seleccionarNickname(String nickname) {
		limpiarDatosUnicos();
		DTUsuario datosUsuario= IU.mostrarDatosUsuario(nickname);
		cNombre.setText(datosUsuario.getNombre());
		cApellido.setText(datosUsuario.getApellido());
		cCorreo.setText(datosUsuario.getMail());
		if(datosUsuario instanceof DTEmpresa) {
			DTEmpresa dataEmpresa= (DTEmpresa) datosUsuario;
			cEmpresa.setText(dataEmpresa.getNombreEmpresa());
			cDescripcion.setText(dataEmpresa.getDescripcion());
			cLink.setText(dataEmpresa.getLink());
			if (dataEmpresa.getNombreOfertas() != null) {
				String [] listaOfertasEmpresa= dataEmpresa.getNombreOfertas().toArray(new String[0]);
			lOfertasEmpresa.setModel(new DefaultComboBoxModel<String> (listaOfertasEmpresa));
			}
		}
		else {
			DTPostulante dataPostulante= (DTPostulante) datosUsuario;
			cNacionalidad.setText(dataPostulante.getNacionalidad());
			Date fechaNacimiento=dataPostulante.getFechaNacimiento();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			cNacimiento.setText(formato.format(fechaNacimiento));
			if (dataPostulante.getNombreOfertas() != null) {
				String [] listaOfertasPostulante = dataPostulante.getNombreOfertas().toArray(new String[0]);
				lOfertasPostulante.setModel(new DefaultComboBoxModel<String>(listaOfertasPostulante));
			}
		}
	}
	public void seleccionarOferta(String nombreOferta) {
		frameOferta.CargarDatosVisuales(nombreOferta);
		frameOferta.setVisible(true);
		//if(frameOferta!=null)frameOferta.setVisible(false);
		//frameOferta= new ConsultaOferta(IU);
	}
	public void limpiarDatosUnicos() {
		cNombre.setText("");
		cApellido.setText("");
		cCorreo.setText("");
		cNacionalidad.setText("");
		cNacimiento.setText("");
		cEmpresa.setText("");
		cDescripcion.setText("");
		cLink.setText("");
		lOfertasEmpresa.setModel(new DefaultComboBoxModel<String>());
		lOfertasPostulante.setModel(new DefaultComboBoxModel<String>());
		
	}
}
