package presentacion;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;

import logica.DTOfertaLaboral;
import logica.DTPostulacion;
import logica.IUsuario;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ConsultaVisitadas extends JInternalFrame{
	private IUsuario IU;
	private DefaultTableModel dtm;
	private JTable table;
	public ConsultaVisitadas(IUsuario IU) {
		setResizable(true);
        setIconifiable(true);
        setClosable(true);
        setVisible(false);
		this.IU=IU;
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setTitle("Ofertas más Visitadas");
		setBounds(0, 0, 530, 440);
		setMinimumSize(new Dimension(530,440));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {38, 79, 325, 85, 50};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Ofertas más Visitadas");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
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
					"#", "Oferta Laboral", "Empresa", "Tipo de Publicación", "Cantidad de Visitas"
				}) ;
		scrollPane.setViewportView(table);
		
		addInternalFrameListener(new InternalFrameAdapter(){
	        public void internalFrameClosing(InternalFrameEvent e) {
	            limpiarFormulario();
	        }
	    });
	}
	
	
	public void update() {
		ArrayList<DTOfertaLaboral> ofertas =IU.obtenerMasVisitadas(); 
		String[] data = new String[5];
		int i =1;
		for(DTOfertaLaboral ofer:ofertas) {
			data[0]=Integer.toString(i);
			data[1]= ofer.getNombre();
			data[2]=ofer.getNombreEmpresa();
			data[3]=ofer.getTipo();
			data[4]=Integer.toString(ofer.getVisitas());
			i++;
			dtm.addRow(data);
		}
		
			table.setModel(dtm);
		}
	public void limpiarFormulario() {
		dtm.setRowCount(0);
	}
	
}
