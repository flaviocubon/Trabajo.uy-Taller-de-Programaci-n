package presentacion;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextArea;
import javax.swing.ListModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import excepciones.PostulanteRepetido;
import logica.IUsuario;
import logica.DTOfertaLaboral;
import logica.Estado;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class PostulacionOferta extends JInternalFrame {
	private JComboBox<String> cbEmpresa;
	private JComboBox<String> cbOferta;
	private JTextArea taCV;
	private JTextArea taMotivacion;
	private JDateChooser chooserFecha;
	private final JList<String> lsPostulantes;
	private IUsuario ctrlUsuario;
	
	public PostulacionOferta(IUsuario ctrlUsuario, ConsultaOferta frmConsultaOferta) {
		setClosable(true);
		this.ctrlUsuario = ctrlUsuario;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Postulación a oferta laboral");
		setBounds(0, 0, 400, 480);
		setMinimumSize(new Dimension(400, 480));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {30, 0, 30, 0, 30, 0, 0, 30, 30, 30, 30, 30, 30, 30, 30, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.gridx = 0;
		gbc_lblEmpresa.gridy = 1;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);
		
		cbOferta = new JComboBox<String>();
		cbOferta.addItem("Seleccionar");
		cbEmpresa = new JComboBox<String>();

		cbEmpresa.addItemListener(new ItemListener() {
			  public void itemStateChanged(ItemEvent itemEvent) {
				  if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					  frmConsultaOferta.limpiarCampos();
					  frmConsultaOferta.setVisible(false);
					  if(cbEmpresa.getSelectedIndex() != 0) {
						  cbOferta.removeAllItems();
						  String empresa = (cbEmpresa.getSelectedItem() != null)?cbEmpresa.getSelectedItem().toString():"";
						  Set<String> ofertas = ctrlUsuario.obtenerOfertasDeEmpresa(empresa);
						  Iterator<String> itOfertas = ofertas.iterator();
						  while(itOfertas.hasNext()) {
							  try {
								DTOfertaLaboral ofertaSel = ctrlUsuario.seleccionarOfertaLaboral(itOfertas.next());
								if (ofertaSel.getEstado() != Estado.Confirmado || ofertaSel.estaVencida()) {
									itOfertas.remove();
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						  }
						  String[] ofertasCombo = new String[ofertas.size()+1];
						  ofertasCombo[0] = "Seleccionar";
						  int i = 1;
						  DTOfertaLaboral dataOferta;
						  for(String of : ofertas) {
							  try {
								dataOferta = ctrlUsuario.seleccionarOfertaLaboral(of);
									  ofertasCombo[i] = of;
									  i++;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						  }
						  cbOferta.setModel(new DefaultComboBoxModel<String> (ofertasCombo));
					  }else {
						  cbOferta.removeAllItems();
						  cbOferta.addItem("Seleccionar");
					  }
				  }
			  }
		});	
		lsPostulantes = new JList<String>();
		lsPostulantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cbOferta.addItemListener(new ItemListener() {
			  public void itemStateChanged(ItemEvent itemEvent) {
				  if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					  limpiarCamposPostulacion();
					  frmConsultaOferta.limpiarCampos();
					  frmConsultaOferta.setVisible(false);
					  if(cbOferta.getSelectedIndex() != 0) {
						  frmConsultaOferta.CargarDatosVisuales(cbOferta.getSelectedItem().toString());
						  frmConsultaOferta.setVisible(true); 
						  final DefaultListModel<String> model = new DefaultListModel<String>();
						  for(String post : ctrlUsuario.listarPostulantes()) {
							  model.addElement(post);
						  }
						  lsPostulantes.setModel(model);
					  }
				  } 
			  }
		});
		lsPostulantes.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				if (!lsPostulantes.isSelectionEmpty()) {
					
				}
			}
		});
		GridBagConstraints gbc_cbEmpresa_1 = new GridBagConstraints();
		gbc_cbEmpresa_1.gridwidth = 2;
		gbc_cbEmpresa_1.insets = new Insets(0, 0, 5, 0);
		gbc_cbEmpresa_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbEmpresa_1.gridx = 1;
		gbc_cbEmpresa_1.gridy = 1;
		getContentPane().add(cbEmpresa, gbc_cbEmpresa_1);
		
		JLabel lblOferta = new JLabel("Oferta");
		GridBagConstraints gbc_lblOferta = new GridBagConstraints();
		gbc_lblOferta.insets = new Insets(0, 0, 5, 5);
		gbc_lblOferta.gridx = 0;
		gbc_lblOferta.gridy = 3;
		getContentPane().add(lblOferta, gbc_lblOferta);
		
		GridBagConstraints gbc_cbOferta_1 = new GridBagConstraints();
		gbc_cbOferta_1.gridwidth = 2;
		gbc_cbOferta_1.insets = new Insets(0, 0, 5, 0);
		gbc_cbOferta_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbOferta_1.gridx = 1;
		gbc_cbOferta_1.gridy = 3;
		getContentPane().add(cbOferta, gbc_cbOferta_1);
		
		JLabel lblPostulantes = new JLabel("Postulantes");
		GridBagConstraints gbc_lblPostulantes = new GridBagConstraints();
		gbc_lblPostulantes.gridwidth = 2;
		gbc_lblPostulantes.insets = new Insets(0, 0, 5, 0);
		gbc_lblPostulantes.gridx = 1;
		gbc_lblPostulantes.gridy = 5;
		getContentPane().add(lblPostulantes, gbc_lblPostulantes);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.gridheight = 5;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 6;
		getContentPane().add(scrollPane_2, gbc_scrollPane_2);
		

		scrollPane_2.setViewportView(lsPostulantes);
		
		JLabel lblCV = new JLabel("CV Reducido");
		GridBagConstraints gbc_lblCV = new GridBagConstraints();
		gbc_lblCV.insets = new Insets(0, 0, 5, 5);
		gbc_lblCV.gridx = 0;
		gbc_lblCV.gridy = 11;
		getContentPane().add(lblCV, gbc_lblCV);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 11;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		taCV = new JTextArea();
		scrollPane.setViewportView(taCV);
		taCV.setLineWrap(true);
		
		JLabel lblMotivacion = new JLabel("Motivación");
		GridBagConstraints gbc_lblMotivacion = new GridBagConstraints();
		gbc_lblMotivacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivacion.gridx = 0;
		gbc_lblMotivacion.gridy = 12;
		getContentPane().add(lblMotivacion, gbc_lblMotivacion);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 12;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		taMotivacion = new JTextArea();
		scrollPane_1.setViewportView(taMotivacion);
		taMotivacion.setLineWrap(true);
		
		chooserFecha = new JDateChooser();
		JTextFieldDateEditor editorFecha = (JTextFieldDateEditor) chooserFecha.getDateEditor();
		editorFecha.setEditable(false);
		editorFecha.setEnabled(true);
		
		JLabel lblFecha = new JLabel("Fecha");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 13;
		getContentPane().add(lblFecha, gbc_lblFecha);
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 2;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 13;
		getContentPane().add(chooserFecha, gbc_dateChooser);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdPostulacionAOferta(e, frmConsultaOferta);
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.anchor = GridBagConstraints.EAST;
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 1;
		gbc_btnGuardar.gridy = 15;
		getContentPane().add(btnGuardar, gbc_btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConsultaOferta.limpiarCampos();
				frmConsultaOferta.setVisible(false);
				limpiarCamposPostulacion();
				cbOferta.removeAllItems();
				cbOferta.addItem("Seleccionar");
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 15;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}
	public void ListarEmpresas() {
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
	private void cmdPostulacionAOferta(ActionEvent e, ConsultaOferta frmConsultaOferta) {
		String empresa = this.cbEmpresa.getSelectedItem().toString();
		String oferta = this.cbOferta.getSelectedItem().toString();
		String postulante = (lsPostulantes.getSelectedValue() == null)?"":lsPostulantes.getSelectedValue();
		String cv = taCV.getText();
		String motivacion = taMotivacion.getText();
		Date fecha = this.chooserFecha.getDate();
		ArrayList<String> error;
		String mensaje = "Revisar los siguientes campos:\n";
		int cantidadErrores = 0;
		try {
			error = verificarCampos(empresa, oferta, postulante, cv, motivacion, fecha);
			for(String cadena : error) {
				if(!cadena.isEmpty()) {
					cantidadErrores++;
					mensaje += "-" + cadena + "\n";
				}
			}
			if(cantidadErrores == 0) {
				ctrlUsuario.ingresarDatosPostulacion(postulante, cv, motivacion, oferta, fecha,"");
				JOptionPane.showMessageDialog(this, "Se ha agregado la postulación con éxito", "Postulación a oferta laboral", JOptionPane.INFORMATION_MESSAGE);
				limpiarCamposPostulacion();
				frmConsultaOferta.limpiarCampos();
				frmConsultaOferta.setVisible(false);
				cbOferta.removeAllItems();
				cbOferta.addItem("Seleccionar");
				cbEmpresa.setSelectedIndex(0);
			}else {
				throw new Exception(mensaje);
			}
		} catch (PostulanteRepetido exc) {
			JOptionPane.showMessageDialog(this, exc.getMessage(), "Postulación a oferta laboral",
                    JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Postulación a oferta laboral",
                    JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private ArrayList<String> verificarCampos(String empresa, String oferta, String postulante, String cv, String motivacion, Date fecha){
		ArrayList<String> error = new ArrayList<String>();
		error.add((empresa.equals("Seleccionar"))?"Seleccionar una empresa":"");
		error.add((oferta.equals("Seleccionar"))?"Seleccionar una oferta":"");
		error.add((postulante.isEmpty())?"Seleccionar un postulante":"");
		error.add((cv.isEmpty())?"Ingresar un CV resumido":"");
		error.add((motivacion.isEmpty())?"Ingresar su motivacion":"");
		error.add((fecha == null)?"Ingresar una Fecha":"");
	
		return error;
	}
	private void limpiarCamposPostulacion() {
		taCV.selectAll();
		taCV.replaceSelection("");
		taMotivacion.selectAll();
		taMotivacion.replaceSelection("");
		chooserFecha.setDate(null);
		lsPostulantes.setModel(new DefaultListModel<String>());
	};
	
}
