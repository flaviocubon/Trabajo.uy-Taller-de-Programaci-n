package presentacion;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import logica.DTHorario;
import logica.IUsuario;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
@SuppressWarnings("serial")
public class AltaOferta extends JInternalFrame{
	private IUsuario IU;
	private JTextField textFieldNombre;
	private JTextField textFieldRemuneracion;
	private JTextField textFieldCiudad;
	private JComboBox<String> comboBoxEmpresa;
	private JComboBox<String> comboBoxTipo;
	private JList<String> list;
	private JTextField textFieldEn;
	private JTextField textFieldSal;
	private JTextArea textArea;
	private JDateChooser dateChooser;
	JComboBox<String> comboBoxDepartamento;
	public AltaOferta(IUsuario IUs) {
		setResizable(true);
        setIconifiable(true);
        setClosable(true);
        setVisible(false);
		IU=IUs;
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setTitle("Alta de Oferta Laboral");
		setBounds(0, 0, 530, 440);
		setMinimumSize(new Dimension(530,440));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {142, 79, 184, 85, 50};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel LabelNombre = new JLabel("Nombre de la Oferta:");
		GridBagConstraints gbc_LabelNombre = new GridBagConstraints();
		gbc_LabelNombre.anchor = GridBagConstraints.NORTH;
		gbc_LabelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_LabelNombre.gridx = 0;
		gbc_LabelNombre.gridy = 1;
		getContentPane().add(LabelNombre, gbc_LabelNombre);
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.anchor = GridBagConstraints.NORTH;
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 1;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel LabelDesc = new JLabel("Descripción de la Oferta: ");
		GridBagConstraints gbc_LabelDesc = new GridBagConstraints();
		gbc_LabelDesc.insets = new Insets(0, 0, 5, 5);
		gbc_LabelDesc.gridx = 0;
		gbc_LabelDesc.gridy = 2;
		getContentPane().add(LabelDesc, gbc_LabelDesc);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JLabel LabelRemuneracion = new JLabel("Remuneración: ");
		GridBagConstraints gbc_LabelRemuneracion = new GridBagConstraints();
		gbc_LabelRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_LabelRemuneracion.gridx = 0;
		gbc_LabelRemuneracion.gridy = 3;
		getContentPane().add(LabelRemuneracion, gbc_LabelRemuneracion);
		
		textFieldRemuneracion = new JTextField();
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 2;
		gbc_textFieldRemuneracion.gridy = 3;
		getContentPane().add(textFieldRemuneracion, gbc_textFieldRemuneracion);
		textFieldRemuneracion.setColumns(10);
		
		JLabel LabelEmpresa = new JLabel("Empresa: ");
		GridBagConstraints gbc_LabelEmpresa = new GridBagConstraints();
		gbc_LabelEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_LabelEmpresa.gridx = 0;
		gbc_LabelEmpresa.gridy = 4;
		getContentPane().add(LabelEmpresa, gbc_LabelEmpresa);
		
		comboBoxEmpresa = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxEmpresa = new GridBagConstraints();
		gbc_comboBoxEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEmpresa.gridx = 2;
		gbc_comboBoxEmpresa.gridy = 4;
		getContentPane().add(comboBoxEmpresa, gbc_comboBoxEmpresa);
		
		JLabel LabelTipo = new JLabel("Tipo de publicación");
		GridBagConstraints gbc_LabelTipo = new GridBagConstraints();
		gbc_LabelTipo.insets = new Insets(0, 0, 5, 5);
		gbc_LabelTipo.gridx = 0;
		gbc_LabelTipo.gridy = 5;
		getContentPane().add(LabelTipo, gbc_LabelTipo);
		
		comboBoxTipo = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxTipo = new GridBagConstraints();
		gbc_comboBoxTipo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipo.gridx = 2;
		gbc_comboBoxTipo.gridy = 5;
		getContentPane().add(comboBoxTipo, gbc_comboBoxTipo);
		
		JLabel LabelFecha = new JLabel("Fecha: ");
		GridBagConstraints gbc_LabelFecha = new GridBagConstraints();
		gbc_LabelFecha.insets = new Insets(0, 0, 5, 5);
		gbc_LabelFecha.gridx = 0;
		gbc_LabelFecha.gridy = 6;
		getContentPane().add(LabelFecha, gbc_LabelFecha);
		
		dateChooser = new JDateChooser();
		JTextFieldDateEditor editorFechaAlta = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editorFechaAlta.setEditable(false);
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 6;
		getContentPane().add(dateChooser, gbc_dateChooser);
		
		JLabel LabelHorario = new JLabel("Horario: ");
		GridBagConstraints gbc_LabelHorario = new GridBagConstraints();
		gbc_LabelHorario.insets = new Insets(0, 0, 5, 5);
		gbc_LabelHorario.gridx = 0;
		gbc_LabelHorario.gridy = 7;
		getContentPane().add(LabelHorario, gbc_LabelHorario);
		
		textFieldEn = new JTextField();
		GridBagConstraints gbc_textFieldEn = new GridBagConstraints();
		gbc_textFieldEn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEn.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEn.gridx = 1;
		gbc_textFieldEn.gridy = 7;
		getContentPane().add(textFieldEn, gbc_textFieldEn);
		textFieldEn.setColumns(10);
		
		textFieldSal = new JTextField();
		GridBagConstraints gbc_textFieldSal = new GridBagConstraints();
		gbc_textFieldSal.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSal.gridx = 2;
		gbc_textFieldSal.gridy = 7;
		getContentPane().add(textFieldSal, gbc_textFieldSal);
		textFieldSal.setColumns(10);
		
		JLabel lblFormato = new JLabel(" (hh:mm)");
		GridBagConstraints gbc_lblFormato = new GridBagConstraints();
		gbc_lblFormato.anchor = GridBagConstraints.WEST;
		gbc_lblFormato.insets = new Insets(0, 0, 5, 0);
		gbc_lblFormato.gridx = 3;
		gbc_lblFormato.gridy = 7;
		getContentPane().add(lblFormato, gbc_lblFormato);
		
		JLabel LabelDepartamento = new JLabel("Departamento: ");
		GridBagConstraints gbc_LabelDepartamento = new GridBagConstraints();
		gbc_LabelDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_LabelDepartamento.gridx = 0;
		gbc_LabelDepartamento.gridy = 8;
		getContentPane().add(LabelDepartamento, gbc_LabelDepartamento);
		
		comboBoxDepartamento = new JComboBox<String>(new String[] {"Seleccionar","Montevideo", "Artigas", "Canelones", "CerroLargo", "Colonia", "Durazno", "Flores", "Florida", "Lavalleja", "Maldonado", "Paysandú", "Rivera", "Rocha", "RíoNegro", "Salto", "SanJosé", "Soriano", "Tacuarembó", "TreintaYTres"});
		GridBagConstraints gbc_comboBoxDepartamento = new GridBagConstraints();
		gbc_comboBoxDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDepartamento.gridx = 2;
		gbc_comboBoxDepartamento.gridy = 8;
		getContentPane().add(comboBoxDepartamento, gbc_comboBoxDepartamento);
		
		JLabel LabelCiudad = new JLabel("Ciudad: ");
		GridBagConstraints gbc_LabelCiudad = new GridBagConstraints();
		gbc_LabelCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_LabelCiudad.gridx = 0;
		gbc_LabelCiudad.gridy = 9;
		getContentPane().add(LabelCiudad, gbc_LabelCiudad);
		
		textFieldCiudad = new JTextField();
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 2;
		gbc_textFieldCiudad.gridy = 9;
		getContentPane().add(textFieldCiudad, gbc_textFieldCiudad);
		textFieldCiudad.setColumns(10);
		
		addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
                limpiarFormulario();
            }
        });
		
		JLabel LabelKeywords = new JLabel("Keywords");
		GridBagConstraints gbc_LabelKeywords = new GridBagConstraints();
		gbc_LabelKeywords.insets = new Insets(0, 0, 5, 5);
		gbc_LabelKeywords.gridx = 0;
		gbc_LabelKeywords.gridy = 10;
		getContentPane().add(LabelKeywords, gbc_LabelKeywords);
		
		list = new JList<String>();
		list.setVisibleRowCount(5);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 2;
		gbc_list.gridy = 10;
		getContentPane().add(list, gbc_list);
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresarOferta(e);
			}
		});
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.anchor = GridBagConstraints.EAST;
		gbc_btnConfirmar.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirmar.gridx = 1;
		gbc_btnConfirmar.gridy = 14;
		getContentPane().add(btnConfirmar, gbc_btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				limpiarFormulario();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 14;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	} 

	private boolean checkFormulario() {
        String nombreU = this.textFieldNombre.getText();
        String descU = this.textArea.getText();
        String remU = this.textFieldRemuneracion.getText();
        String ciudadU = this.textFieldCiudad.getText();
        String entU = this.textFieldEn.getText();
        String salU = this.textFieldSal.getText();
        Date fechaU = this.dateChooser.getDate();
        String nomTipo = (String)comboBoxTipo.getSelectedItem();
		String nickname = (String)comboBoxEmpresa.getSelectedItem();
		String departamento = (String)comboBoxDepartamento.getSelectedItem();

        if (nombreU.isEmpty() || descU.isEmpty() || remU.isEmpty()||ciudadU.isEmpty()||entU.isEmpty()||salU.isEmpty()||fechaU==null||nomTipo=="Seleccionar"||nickname=="Seleccionar"||departamento=="Seleccionar") {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Integer.parseInt(remU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La remuneración debe ser un numero", "Alta de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            LocalTime.parse(entU);
            LocalTime.parse(salU);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un horario válido", "Alta de Oferta Laboral",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
	private void limpiarFormulario() {
		textFieldNombre.setText("");
        textArea.setText("");
        textFieldRemuneracion.setText("");
        textFieldCiudad.setText("");
        textFieldEn.setText("");
        textFieldSal.setText("");
        dateChooser.setCalendar(null);
        comboBoxEmpresa.setSelectedIndex(0);
		comboBoxTipo.setSelectedIndex(0);
		comboBoxDepartamento.setSelectedIndex(0);
		list.clearSelection();
		
	}
	
	private void ingresarOferta(ActionEvent e) {
		if(checkFormulario()) {
			String nombre = textFieldNombre.getText();
			String descripcion = textArea.getText();
			int remuneracion = Integer.parseInt(textFieldRemuneracion.getText());
			String ciudad = textFieldCiudad.getText();
			String departamento = (String)comboBoxDepartamento.getSelectedItem();
			String nomTipo = (String)comboBoxTipo.getSelectedItem();
			String nickname = (String)comboBoxEmpresa.getSelectedItem();
			Set<String> keywords = new HashSet<String>(list.getSelectedValuesList());
			Date fecha = dateChooser.getDate();
			DTHorario horario = new DTHorario(textFieldEn.getText(),textFieldSal.getText());
			boolean ingreso;
			try {
				ingreso = IU.ingresarOferta(nickname,nomTipo,nombre,descripcion,horario,remuneracion,fecha,ciudad,departamento,keywords, "");
				JOptionPane.showMessageDialog(this, "La oferta se registró con éxito", "Alta de Oferta Laboral",
	                    JOptionPane.PLAIN_MESSAGE);
				limpiarFormulario();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "La oferta ya se encuentra en el sistema", "Alta de Oferta Laboral",
	                    JOptionPane.ERROR_MESSAGE);
			}
		}
           
	}
	
	public void actualizar(String[] listaEmpresas, String[] listaTipos,String[] listaKeywords) {
		String[] empresas = Arrays.copyOf(listaEmpresas, listaEmpresas.length+1);
		empresas[0]="Seleccionar";
		System.arraycopy(listaEmpresas, 0, empresas, 1, listaEmpresas.length);
		String[] tipos = Arrays.copyOf(listaTipos, listaTipos.length+1);
		tipos[0]="Seleccionar";
		System.arraycopy(listaTipos, 0,tipos, 1, listaTipos.length);
		comboBoxEmpresa.setModel(new DefaultComboBoxModel<String> (empresas));
		comboBoxTipo.setModel(new DefaultComboBoxModel<String> (tipos));
		comboBoxEmpresa.setSelectedIndex(0);
		comboBoxTipo.setSelectedIndex(0);
		comboBoxDepartamento.setSelectedIndex(0);
		
		
		DefaultListModel<String> modeloKey=new DefaultListModel<String>();
		for(int i=0;i<listaKeywords.length;i++) {
			modeloKey.addElement(listaKeywords[i]);
		}
		list.setModel(modeloKey);
		
	}
	}