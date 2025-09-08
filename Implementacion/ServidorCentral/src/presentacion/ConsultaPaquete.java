package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.toedter.calendar.JDateChooser;

import logica.DTAgrupa;
import logica.DTPaquete;
import logica.DTPostulacion;
import logica.DTTipoPublicacion;
import logica.ITipos;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConsultaPaquete extends JInternalFrame{

	private ITipos ctrlTipos;
	private JTextField textFieldValidez;
	private JTextField textFieldDescuento;
	private JComboBox<String> comboBoxPaquete;
	private JTable tableTipos;
	private JTextArea textAreaDescripcion;
	private JDateChooser dateChooserAlta;
	private boolean dataCargada;
	private JScrollPane scrollPane_1;
	private JTable table;
	private DefaultTableModel dtm;
	private JTextField textFieldCosto;
	
	public ConsultaPaquete(ITipos ctrlTipos, AltaTipoPublicacion frmAltaTipo) {
		this.ctrlTipos = ctrlTipos;
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta de Paquete de Tipos de Publicacion de Ofertas Laborales");
        dataCargada = false;
        setBounds(300, 0, 500, 400);
        setMinimumSize(new Dimension(500,400));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 257, 0, 0};
        gridBagLayout.rowHeights = new int[]{68, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        addInternalFrameListener(new InternalFrameAdapter() {
        	public void internalFrameClosing(InternalFrameEvent e) {
        		dataCargada = false;
        	}
        });
        
        Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
        GridBagConstraints gbc_rigidArea = new GridBagConstraints();
        gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
        gbc_rigidArea.gridx = 0;
        gbc_rigidArea.gridy = 0;
        getContentPane().add(rigidArea, gbc_rigidArea);
        
        JLabel lblPaquete = new JLabel("Paquete:");
        GridBagConstraints gbc_lblPaquete = new GridBagConstraints();
        gbc_lblPaquete.anchor = GridBagConstraints.EAST;
        gbc_lblPaquete.insets = new Insets(0, 0, 5, 5);
        gbc_lblPaquete.gridx = 0;
        gbc_lblPaquete.gridy = 1;
        getContentPane().add(lblPaquete, gbc_lblPaquete);
        
        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 2;
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 1;
        getContentPane().add(panel, gbc_panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        comboBoxPaquete = new JComboBox<String>();
        panel.add(comboBoxPaquete);
        comboBoxPaquete.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if (e.getStateChange() == ItemEvent.SELECTED && dataCargada == true && comboBoxPaquete.getSelectedIndex() != 0) {
        			cargarInfoPaquete((String) comboBoxPaquete.getSelectedItem());
        		}
        	}
        });
        
        JLabel lblDescripcion = new JLabel("Descripcion:");
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.anchor = GridBagConstraints.NORTHEAST;
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 2;
        getContentPane().add(lblDescripcion, gbc_lblDescripcion);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setMinimumSize(new Dimension(2, 60));
        scrollPane.setPreferredSize(new Dimension(2, 60));
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridheight = 2;
        gbc_scrollPane.gridwidth = 2;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 2;
        getContentPane().add(scrollPane, gbc_scrollPane);
        
        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setEditable(false);
        scrollPane.setViewportView(textAreaDescripcion);
        
        JLabel lblValidez = new JLabel("Validez:");
        GridBagConstraints gbc_lblValidez = new GridBagConstraints();
        gbc_lblValidez.anchor = GridBagConstraints.EAST;
        gbc_lblValidez.insets = new Insets(0, 0, 5, 5);
        gbc_lblValidez.gridx = 0;
        gbc_lblValidez.gridy = 4;
        getContentPane().add(lblValidez, gbc_lblValidez);
        
        textFieldValidez = new JTextField();
        textFieldValidez.setEditable(false);
        GridBagConstraints gbc_textFieldValidez = new GridBagConstraints();
        gbc_textFieldValidez.gridwidth = 2;
        gbc_textFieldValidez.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldValidez.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldValidez.gridx = 1;
        gbc_textFieldValidez.gridy = 4;
        getContentPane().add(textFieldValidez, gbc_textFieldValidez);
        textFieldValidez.setColumns(10);
        
        JLabel lblDescuento = new JLabel("Descuento:");
        GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
        gbc_lblDescuento.anchor = GridBagConstraints.EAST;
        gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescuento.gridx = 0;
        gbc_lblDescuento.gridy = 5;
        getContentPane().add(lblDescuento, gbc_lblDescuento);
        
        textFieldDescuento = new JTextField();
        textFieldDescuento.setEditable(false);
        GridBagConstraints gbc_textFieldDescuento = new GridBagConstraints();
        gbc_textFieldDescuento.gridwidth = 2;
        gbc_textFieldDescuento.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldDescuento.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldDescuento.gridx = 1;
        gbc_textFieldDescuento.gridy = 5;
        getContentPane().add(textFieldDescuento, gbc_textFieldDescuento);
        textFieldDescuento.setColumns(10);
        
        JLabel lblFecha = new JLabel("Fecha Alta:");
        GridBagConstraints gbc_lblFecha = new GridBagConstraints();
        gbc_lblFecha.anchor = GridBagConstraints.EAST;
        gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
        gbc_lblFecha.gridx = 0;
        gbc_lblFecha.gridy = 6;
        getContentPane().add(lblFecha, gbc_lblFecha);
        
        dateChooserAlta = new JDateChooser();
        dateChooserAlta.setEnabled(false);
        GridBagConstraints gbc_dateChooserAlta = new GridBagConstraints();
        gbc_dateChooserAlta.gridwidth = 2;
        gbc_dateChooserAlta.insets = new Insets(0, 0, 5, 5);
        gbc_dateChooserAlta.fill = GridBagConstraints.BOTH;
        gbc_dateChooserAlta.gridx = 1;
        gbc_dateChooserAlta.gridy = 6;
        getContentPane().add(dateChooserAlta, gbc_dateChooserAlta);
        
        JLabel lblCosto = new JLabel("Costo:");
        GridBagConstraints gbc_lblCosto = new GridBagConstraints();
        gbc_lblCosto.anchor = GridBagConstraints.EAST;
        gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
        gbc_lblCosto.gridx = 0;
        gbc_lblCosto.gridy = 7;
        getContentPane().add(lblCosto, gbc_lblCosto);
        
        textFieldCosto = new JTextField();
        textFieldCosto.setEditable(false);
        GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
        gbc_textFieldCosto.gridwidth = 2;
        gbc_textFieldCosto.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldCosto.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldCosto.gridx = 1;
        gbc_textFieldCosto.gridy = 7;
        getContentPane().add(textFieldCosto, gbc_textFieldCosto);
        textFieldCosto.setColumns(10);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setPreferredSize(new Dimension(2, 23));
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.gridwidth = 2;
        gbc_scrollPane_1.gridheight = 4;
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 1;
        gbc_scrollPane_1.gridy = 8;
        getContentPane().add(scrollPane_1, gbc_scrollPane_1);
        
        table = new JTable();
        dtm = new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Nombre", "Cantidad"
        	});
        
        table.setModel(dtm);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.setDefaultEditor(Object.class, null);
        scrollPane_1.setViewportView(table);
        table.getTableHeader().setReorderingAllowed(false);
        
        table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					String nombreTipo = (String) table.getValueAt(row, 0);
	            	frmAltaTipo.mostrarDatosTipo(nombreTipo);
				}
			}
        });
        
        JLabel lblNewLabel = new JLabel("Tipos de Publicacion:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 8;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
        GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
        gbc_rigidArea_1.gridheight = 3;
        gbc_rigidArea_1.gridx = 3;
        gbc_rigidArea_1.gridy = 10;
        getContentPane().add(rigidArea_1, gbc_rigidArea_1);
        
        addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
                textAreaDescripcion.selectAll();
                textAreaDescripcion.replaceSelection("");
                textFieldDescuento.setText("");
                textFieldValidez.setText("");
                dateChooserAlta.setDate(null);
                textFieldCosto.setText("");
                dtm.setRowCount(0);
            }
        });
        
	}
	
	public void cargarInfoPaquete(String nombreP) {
		Map<String, DTPaquete> dataPaquetes = ctrlTipos.obtenerDataPaquetes();
		if (!dataPaquetes.isEmpty()) {
			DTPaquete dataPaquete = dataPaquetes.get(nombreP);
			textFieldDescuento.setText(Double.toString(dataPaquete.getDescuento()));
			textFieldValidez.setText(Integer.toString(dataPaquete.getPeriodoValidez()));
			textAreaDescripcion.setText(dataPaquete.getDescripcion());
			textFieldCosto.setText(Double.toString(dataPaquete.getCosto()));
			dateChooserAlta.setDate(dataPaquete.getFecha());
			dataPaquete.getTipos();
			dtm.setNumRows(0);
			Map<String, DTAgrupa> dataTipos = dataPaquete.getTipos();
			String[] data = new String[2];
			for(String keyTipo : dataTipos.keySet()) {
				data[0] = dataTipos.get(keyTipo).getNombreTipo();
				data[1] = Integer.toString(dataTipos.get(keyTipo).getCant());
				dtm.addRow(data);
				table.setModel(dtm);
		}
	}
}
	
    public void cargarPaquetes() {
    	comboBoxPaquete.removeAllItems();
    	comboBoxPaquete.addItem("Seleccionar");
    	Set<String> paquetesTipo = ctrlTipos.listarPaquetes();
    	for (String paquete : paquetesTipo) {
    		comboBoxPaquete.addItem(paquete);
    	}
    	dataCargada = true;
    }
    
    public void FalseDataCargada() {
    	dataCargada = false;
    }
}