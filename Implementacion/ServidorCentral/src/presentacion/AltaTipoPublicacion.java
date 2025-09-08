package presentacion;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import excepciones.nombreTipoPublicacionRepetido;
import logica.DTTipoPublicacion;
import logica.ITipos;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class AltaTipoPublicacion extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldExposicion;
	private JTextField textFieldDuracion;
	private JTextArea textAreaDescripcion;
	private JDateChooser chooserFechaAlta;
	private ITipos ctrlTipos;
	private JTextField textFieldCosto;	
	private JButton btnCancelar;
	private JButton btnAceptar;
	
	public AltaTipoPublicacion(ITipos ctrlTipos) {
		this.ctrlTipos = ctrlTipos;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Tipo de Publicacion de Oferta Laboral");
        setBounds(0, 0, 400, 400);
        setMinimumSize(new Dimension(400,400));

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {30, 30, 0, 0, 0, 30, 0, 30, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 1;
		getContentPane().add(rigidArea, gbc_rigidArea);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 2;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.gridx = 3;
		gbc_textFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 2;
		gbc_lblDescripcion.gridy = 3;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 80));
		panel.setMinimumSize(new Dimension(10, 60));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.gridheight = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 3;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(50, 80));
		panel.add(scrollPane);
		
		textAreaDescripcion = new JTextArea();
		scrollPane.setViewportView(textAreaDescripcion);
		textAreaDescripcion.setRows(WIDTH);
		textAreaDescripcion.setLineWrap(true);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setMinimumSize(new Dimension(0, 40));
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.gridheight = 2;
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 2;
		gbc_verticalStrut.gridy = 4;
		getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblExposicion = new JLabel("Exposicion:");
		lblExposicion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblExposicion = new GridBagConstraints();
		gbc_lblExposicion.anchor = GridBagConstraints.EAST;
		gbc_lblExposicion.insets = new Insets(0, 0, 5, 5);
		gbc_lblExposicion.gridx = 2;
		gbc_lblExposicion.gridy = 6;
		getContentPane().add(lblExposicion, gbc_lblExposicion);
		
		textFieldExposicion = new JTextField();
		GridBagConstraints gbc_textFieldExposicion = new GridBagConstraints();
		gbc_textFieldExposicion.gridwidth = 2;
		gbc_textFieldExposicion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldExposicion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldExposicion.gridx = 3;
		gbc_textFieldExposicion.gridy = 6;
		getContentPane().add(textFieldExposicion, gbc_textFieldExposicion);
		textFieldExposicion.setColumns(10);
		
		JLabel lblDuracion = new JLabel("Duracion:");
		lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.anchor = GridBagConstraints.EAST;
		gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracion.gridx = 2;
		gbc_lblDuracion.gridy = 7;
		getContentPane().add(lblDuracion, gbc_lblDuracion);
		
		textFieldDuracion = new JTextField();
		GridBagConstraints gbc_textFieldDuracion = new GridBagConstraints();
		gbc_textFieldDuracion.gridwidth = 2;
		gbc_textFieldDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDuracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDuracion.gridx = 3;
		gbc_textFieldDuracion.gridy = 7;
		getContentPane().add(textFieldDuracion, gbc_textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.gridx = 2;
		gbc_lblCosto.gridy = 8;
		getContentPane().add(lblCosto, gbc_lblCosto);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
		gbc_textFieldCosto.gridwidth = 2;
		gbc_textFieldCosto.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCosto.fill = GridBagConstraints.BOTH;
		gbc_textFieldCosto.gridx = 3;
		gbc_textFieldCosto.gridy = 8;
		getContentPane().add(textFieldCosto, gbc_textFieldCosto);
		textFieldCosto.setColumns(10);
		
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 2;
		gbc_lblFecha.gridy = 9;
		getContentPane().add(lblFecha, gbc_lblFecha);
		
		//Codigo para desactivar la edicion del campo de texto fecha en el JDateChooser
		chooserFechaAlta = new JDateChooser();
		JTextFieldDateEditor editorFechaAlta = (JTextFieldDateEditor) chooserFechaAlta.getDateEditor();
		editorFechaAlta.setEditable(false);
		
		GridBagConstraints gbc_chooserFechaAlta = new GridBagConstraints();
		gbc_chooserFechaAlta.gridwidth = 2;
		gbc_chooserFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_chooserFechaAlta.fill = GridBagConstraints.BOTH;
		gbc_chooserFechaAlta.gridx = 3;
		gbc_chooserFechaAlta.gridy = 9;
		getContentPane().add(chooserFechaAlta, gbc_chooserFechaAlta);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				limpiarAltaTipo();
			}
		});
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAltaTipoPublicacion(e);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 3;
		gbc_btnAceptar.gridy = 10;
		getContentPane().add(btnAceptar, gbc_btnAceptar);		
		
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 10;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_1.gridx = 7;
		gbc_rigidArea_1.gridy = 12;
		getContentPane().add(rigidArea_1, gbc_rigidArea_1);
		
	}
			
	private void cmdAltaTipoPublicacion(ActionEvent e) {
		if (checkFormulario()) {
			String nombreT = this.textFieldNombre.getText();
			String descripcionT = this.textAreaDescripcion.getText();
			int exposicionT = Integer.parseInt(textFieldExposicion.getText());
			Date fechaT = this.chooserFechaAlta.getDate();
			double costoT = Integer.parseInt(textFieldCosto.getText());
			int duracionT = Integer.parseInt(textFieldDuracion.getText());
		
			try {
				ctrlTipos.ingresarDatosTipoPublicacion(nombreT, descripcionT, exposicionT, fechaT, costoT, duracionT);
				JOptionPane.showMessageDialog(this, "El Tipo de Publicacion se ha creado con éxito", "Agregar Tipo de Publicacion",
	                    JOptionPane.INFORMATION_MESSAGE);
				limpiarAltaTipo();
			} catch (nombreTipoPublicacionRepetido exc) {
				JOptionPane.showMessageDialog(this, exc.getMessage(), "Agregar Tipo de Publicacion",
	                    JOptionPane.INFORMATION_MESSAGE);
			}
		}		
	}
	
	private boolean checkFormulario() {
		String nombreT = textFieldNombre.getText();
		String descripcionT = textAreaDescripcion.getText();
		String exposicionT = textFieldExposicion.getText();
		String duracionT = textFieldDuracion.getText();
		String costoT = textFieldCosto.getText();
		Date fechaT = this.chooserFechaAlta.getDate();
		
		if (nombreT.isEmpty() || descripcionT.isEmpty() || fechaT == null || exposicionT.isEmpty() ||
				duracionT.isEmpty() || costoT.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Alta Tipo de Publicacion", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			double cos =Double.parseDouble(costoT);
			double dur =Double.parseDouble(duracionT);
			int exp=Integer.parseInt(exposicionT);
			if(exp<=0 || dur<=0 || cos<=0) {
				JOptionPane.showMessageDialog(this, "Los campos numericos deben tener valores positivos", "Alta Tipo de Publicacion", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Debe ingresar un numero", "Alta Tipo de Publicacion", JOptionPane.ERROR_MESSAGE);
			return false;
		}
			
		return true;
	}
	
	public void limpiarAltaTipo() {
		setTitle("Alta de Tipo de Publicacion de Oferta Laboral");
		textFieldNombre.setText("");
		btnAceptar.setEnabled(true);
		textFieldNombre.setEditable(true);
		textAreaDescripcion.setEditable(true);
		textFieldCosto.setEditable(true);
		textFieldDuracion.setEditable(true);
		textFieldExposicion.setEditable(true);
		chooserFechaAlta.setEnabled(true);
		textAreaDescripcion.selectAll();
		textAreaDescripcion.replaceSelection("");
		textFieldExposicion.setText("");
		chooserFechaAlta.setDate(null);
		textFieldDuracion.setText("");
		textFieldCosto.setText("");
		
	};
	
	public void mostrarDatosTipo(String nombreTipo) {
		setTitle("Informacion Tipo de Publicacion");
		textFieldNombre.setEditable(false);
		textAreaDescripcion.setEditable(false);
		textFieldCosto.setEditable(false);
		textFieldDuracion.setEditable(false);
		textFieldExposicion.setEditable(false);
		chooserFechaAlta.setEnabled(false);
		btnAceptar.setEnabled(false);
		Map<String, DTTipoPublicacion> dataTipos = ctrlTipos.obtenerDataTipos();
		DTTipoPublicacion dataTipo = dataTipos.get(nombreTipo);
		textFieldNombre.setText(nombreTipo);
		textAreaDescripcion.setText(dataTipo.getDescripcion());
		textFieldCosto.setText(Double.toString(dataTipo.getCosto()));
		textFieldDuracion.setText(Double.toString(dataTipo.getDuracion()));
		textFieldExposicion.setText(Integer.toString(dataTipo.getExposicion()));
		chooserFechaAlta.setDate(dataTipo.getFecha());
		setVisible(true);
	}
}
