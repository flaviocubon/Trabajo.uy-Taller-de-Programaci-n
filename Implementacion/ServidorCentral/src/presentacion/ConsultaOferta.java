package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import logica.DTHorario;
import logica.DTOfertaLaboral;
import logica.DTPostulacion;
import logica.IUsuario;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.ScrollPane;
import java.awt.Label;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ConsultaOferta extends JInternalFrame{
	private JComboBox<String> cbEmpresa;
	private JComboBox<String> cbOferta;
	private IUsuario ctrlUsuario;
	private JTextField tfNombre;
	private JTextField tfCiudad;
	private JTextField tfDepartamento;
	private JTextField tfHorario;
	private JTextField tfRemuneracion;
	private JTextField tfFecha;
	private JTextField tfCosto;
	private JTable table;
	private DefaultTableModel dtm;
	private final JList<String> lsKeywords;
	private JTextField tfTipo;
	private JTextArea textArea;

	public ConsultaOferta(IUsuario ctrlUsuario) {
		setClosable(true);
		this.ctrlUsuario = ctrlUsuario;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Consulta de oferta laboral");
		setBounds(400, 0, 420, 600);
		setMinimumSize(new Dimension(420, 600));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 25};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 74, 23, 74, 0, 25};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 5;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_tfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNombre.gridx = 1;
		gbc_tfNombre.gridy = 5;
		getContentPane().add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);
		
		Label label = new Label("Descripcion");
		label.setAlignment(Label.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 6;
		getContentPane().add(label, gbc_label);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridheight = 2;
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 6;
		getContentPane().add(scrollPane_2, gbc_scrollPane_2);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane_2.setViewportView(textArea);
		
		JLabel lblTipo = new JLabel("Tipo");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 14;
		getContentPane().add(lblTipo, gbc_lblTipo);
		
		tfTipo = new JTextField();
		tfTipo.setEditable(false);
		GridBagConstraints gbc_tfTipo = new GridBagConstraints();
		gbc_tfTipo.insets = new Insets(0, 0, 5, 0);
		gbc_tfTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTipo.gridx = 1;
		gbc_tfTipo.gridy = 14;
		getContentPane().add(tfTipo, gbc_tfTipo);
		tfTipo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 16;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowSelectionAllowed(false);
		dtm = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Postulante", "Fecha", "CV", "Descripción"
				}) ;
		table.setModel(dtm);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("            ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.anchor = GridBagConstraints.EAST;
		gbc_lblEmpresa.gridx = 0;
		gbc_lblEmpresa.gridy = 1;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);
		
		cbOferta = new JComboBox<String>();
		cbOferta.addItem("Seleccionar");
		cbEmpresa = new JComboBox<String>();
		
		GridBagConstraints gbc_cbEmpresa = new GridBagConstraints();
		gbc_cbEmpresa.insets = new Insets(0, 0, 5, 0);
		gbc_cbEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbEmpresa.gridx = 1;
		gbc_cbEmpresa.gridy = 1;
		getContentPane().add(cbEmpresa, gbc_cbEmpresa);
		
		JLabel lblNewLabel_1 = new JLabel("               ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblOferta = new JLabel("Oferta");
		GridBagConstraints gbc_lblOferta = new GridBagConstraints();
		gbc_lblOferta.anchor = GridBagConstraints.EAST;
		gbc_lblOferta.insets = new Insets(0, 0, 5, 5);
		gbc_lblOferta.gridx = 0;
		gbc_lblOferta.gridy = 3;
		getContentPane().add(lblOferta, gbc_lblOferta);
		
		GridBagConstraints gbc_cbOferta = new GridBagConstraints();
		gbc_cbOferta.insets = new Insets(0, 0, 5, 0);
		gbc_cbOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbOferta.gridx = 1;
		gbc_cbOferta.gridy = 3;
		getContentPane().add(cbOferta, gbc_cbOferta);
		
		JLabel lblNewLabel_2 = new JLabel("             ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 8;
		getContentPane().add(lblCiudad, gbc_lblCiudad);
		
		tfCiudad = new JTextField();
		tfCiudad.setEditable(false);
		GridBagConstraints gbc_tfCiudad = new GridBagConstraints();
		gbc_tfCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_tfCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCiudad.gridx = 1;
		gbc_tfCiudad.gridy = 8;
		getContentPane().add(tfCiudad, gbc_tfCiudad);
		tfCiudad.setColumns(10);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 0;
		gbc_lblDepartamento.gridy = 9;
		getContentPane().add(lblDepartamento, gbc_lblDepartamento);
		
		tfDepartamento = new JTextField();
		tfDepartamento.setEditable(false);
		GridBagConstraints gbc_tfDepartamento = new GridBagConstraints();
		gbc_tfDepartamento.insets = new Insets(0, 0, 5, 0);
		gbc_tfDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDepartamento.gridx = 1;
		gbc_tfDepartamento.gridy = 9;
		getContentPane().add(tfDepartamento, gbc_tfDepartamento);
		tfDepartamento.setColumns(10);
		
		JLabel lblHorario = new JLabel("Horario");
		GridBagConstraints gbc_lblHorario = new GridBagConstraints();
		gbc_lblHorario.anchor = GridBagConstraints.EAST;
		gbc_lblHorario.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorario.gridx = 0;
		gbc_lblHorario.gridy = 10;
		getContentPane().add(lblHorario, gbc_lblHorario);
		
		tfHorario = new JTextField();
		tfHorario.setEditable(false);
		GridBagConstraints gbc_tfHorario = new GridBagConstraints();
		gbc_tfHorario.insets = new Insets(0, 0, 5, 0);
		gbc_tfHorario.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfHorario.gridx = 1;
		gbc_tfHorario.gridy = 10;
		getContentPane().add(tfHorario, gbc_tfHorario);
		tfHorario.setColumns(10);
		
		JLabel lblRemuneracion = new JLabel("Remuneración");
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.EAST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 0;
		gbc_lblRemuneracion.gridy = 11;
		getContentPane().add(lblRemuneracion, gbc_lblRemuneracion);
		
		tfRemuneracion = new JTextField();
		tfRemuneracion.setEditable(false);
		GridBagConstraints gbc_tfRemuneracion = new GridBagConstraints();
		gbc_tfRemuneracion.insets = new Insets(0, 0, 5, 0);
		gbc_tfRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfRemuneracion.gridx = 1;
		gbc_tfRemuneracion.gridy = 11;
		getContentPane().add(tfRemuneracion, gbc_tfRemuneracion);
		tfRemuneracion.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 12;
		getContentPane().add(lblFecha, gbc_lblFecha);
		
		tfFecha = new JTextField();
		tfFecha.setEditable(false);
		GridBagConstraints gbc_tfFecha = new GridBagConstraints();
		gbc_tfFecha.insets = new Insets(0, 0, 5, 0);
		gbc_tfFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfFecha.gridx = 1;
		gbc_tfFecha.gridy = 12;
		getContentPane().add(tfFecha, gbc_tfFecha);
		tfFecha.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.gridx = 0;
		gbc_lblCosto.gridy = 13;
		getContentPane().add(lblCosto, gbc_lblCosto);
		
		tfCosto = new JTextField();
		tfCosto.setEditable(false);
		GridBagConstraints gbc_tfCosto = new GridBagConstraints();
		gbc_tfCosto.insets = new Insets(0, 0, 5, 0);
		gbc_tfCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCosto.gridx = 1;
		gbc_tfCosto.gridy = 13;
		getContentPane().add(tfCosto, gbc_tfCosto);
		tfCosto.setColumns(10);
		
		JLabel lblPostulaciones = new JLabel("Postulaciones");
		GridBagConstraints gbc_lblPostulaciones = new GridBagConstraints();
		gbc_lblPostulaciones.insets = new Insets(0, 0, 5, 0);
		gbc_lblPostulaciones.gridx = 1;
		gbc_lblPostulaciones.gridy = 15;
		getContentPane().add(lblPostulaciones, gbc_lblPostulaciones);
		
		JLabel lblKeywords = new JLabel("KeyWords");
		GridBagConstraints gbc_lblKeywords = new GridBagConstraints();
		gbc_lblKeywords.insets = new Insets(0, 0, 5, 0);
		gbc_lblKeywords.gridx = 1;
		gbc_lblKeywords.gridy = 17;
		getContentPane().add(lblKeywords, gbc_lblKeywords);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 18;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		lsKeywords = new JList<String>();
		scrollPane_1.setViewportView(lsKeywords);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				setVisible(false);
			}
		});
		
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.anchor = GridBagConstraints.EAST;
		gbc_btnCerrar.gridx = 1;
		gbc_btnCerrar.gridy = 19;
		getContentPane().add(btnCerrar, gbc_btnCerrar);
		
			cbEmpresa.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent itemEvent) {
					if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
						limpiarCampos();
						if(cbEmpresa.getSelectedIndex() != 0) {
							cbOferta.removeAllItems();
							String emp = (cbEmpresa.getSelectedItem() != null)?cbEmpresa.getSelectedItem().toString():"";
							Set<String> ofertas = ctrlUsuario.obtenerOfertasDeEmpresa(emp);
							  String[] ofertasCombo = new String[ofertas.size()+1];
							  ofertasCombo[0] = "Seleccionar";
							  int i = 1;
							  for(String of : ofertas) {
								  ofertasCombo[i] = of;
								  i++;
							  }
							  cbOferta.setModel(new DefaultComboBoxModel<String> (ofertasCombo));
						}else {
							cbOferta.removeAllItems();
							cbOferta.addItem("Seleccionar");
						}
					}
				}
			});
			cbOferta.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent itemEvent) {
					if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
						limpiarCampos();
						if(cbOferta.getSelectedIndex() != 0) {
							try {
								mostrarDatos(ctrlUsuario.seleccionarOfertaLaboral(cbOferta.getSelectedItem().toString()),dtm);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			});
	}
	public void ListarEmpresas() {
		cbEmpresa.setEnabled(true);
		cbOferta.setEnabled(true);
		cbOferta.removeAllItems();
		cbOferta.addItem("Seleccionar");
		Set<String> empresas = ctrlUsuario.listarEmpresas();
		String[] empresasCombo = new String[empresas.size()+1];
		empresasCombo[0] = "Seleccionar";
		int i = 1;
		for(String emp : empresas) {
			empresasCombo[i] = emp;
			i++;
		}
		cbEmpresa.setModel(new DefaultComboBoxModel<String> (empresasCombo));
	}
	private void mostrarDatos(DTOfertaLaboral datos, DefaultTableModel dtm) {
		if(datos != null) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
			tfNombre.setText(datos.getNombre());
			tfCiudad.setText(datos.getCiudad());
			tfDepartamento.setText(datos.getDepartamento());
			tfHorario.setText(datos.getHorario().getInicio().toString() + " - " + datos.getHorario().getFin().toString()); //Puede estar vacio el horario?
			tfRemuneracion.setText(Integer.toString(datos.getRemuneracion()));
			tfFecha.setText(formato.format(datos.getFecha()));
			tfCosto.setText(Double.toString(datos.getCosto()));
			tfTipo.setText(datos.getTipo());
			textArea.setText(datos.getDescripcion());
			Set<DTPostulacion> postulaciones = datos.getPostulaciones();
			String[] data = new String[4];
			for(DTPostulacion post : postulaciones) {
				data[0] = post.getPostulante();
				data[1] = formato.format(post.getFechaPostulacion());
				data[2] = post.getResumenCV();
				data[3] = post.getDescripcion();
				dtm.addRow(data);
				table.setModel(dtm);
			}
			final DefaultListModel<String> model = new DefaultListModel<String>();
			  for(String key : datos.getKeywords()) {
				  model.addElement(key);
			  }
			  lsKeywords.setModel(model);
		}
	}
	public void CargarDatosVisuales(String oferta) {
		if(cbEmpresa.getItemCount() > 0) {
			cbEmpresa.setSelectedIndex(0);
		}
		if(cbOferta.getItemCount() > 0) {
			cbOferta.setSelectedIndex(0);
		}
		cbEmpresa.setEnabled(false);
		cbOferta.setEnabled(false);
		try {
			mostrarDatos(ctrlUsuario.seleccionarOfertaLaboral(oferta),dtm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void limpiarCampos() {
		tfNombre.setText("");
		tfCiudad.setText("");
		tfDepartamento.setText("");
		tfHorario.setText("");
		tfRemuneracion.setText("");
		tfFecha.setText("");
		tfCosto.setText("");
		tfTipo.setText("");
		textArea.setText("");
		DefaultTableModel tb = (DefaultTableModel) table.getModel();
		tb.setRowCount(0);
		lsKeywords.setModel(new DefaultListModel<String>());
	};
}
