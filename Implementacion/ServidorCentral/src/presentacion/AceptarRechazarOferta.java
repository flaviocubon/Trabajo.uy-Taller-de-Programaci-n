package presentacion;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import logica.Estado;
import logica.Fabrica;
import logica.IUsuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class AceptarRechazarOferta extends JInternalFrame{
	private JComboBox comboBoxEmpresa;
	private JComboBox comboBoxOferta;
	private IUsuario ctrlUsuario;
	private Fabrica fab;
	private boolean dataCargada;
	
	public AceptarRechazarOferta() {
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Aceptar/Rechazar Oferta");
		fab = Fabrica.getInstance();
		ctrlUsuario = fab.getIUsuario();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{72, 91, 85, 97, 104, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_1.gridx = 0;
		gbc_rigidArea_1.gridy = 0;
		getContentPane().add(rigidArea_1, gbc_rigidArea_1);
		
		JLabel lblEmpresa = new JLabel("Seleccionar empresa:");
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.gridx = 1;
		gbc_lblEmpresa.gridy = 3;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);
		
		comboBoxEmpresa = new JComboBox();
		GridBagConstraints gbc_comboBoxEmpresa = new GridBagConstraints();
		gbc_comboBoxEmpresa.gridwidth = 2;
		gbc_comboBoxEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEmpresa.gridx = 2;
		gbc_comboBoxEmpresa.gridy = 3;
		getContentPane().add(comboBoxEmpresa, gbc_comboBoxEmpresa);

		
		JLabel lblOferta = new JLabel("Seleccionar oferta:");
		GridBagConstraints gbc_lblOferta = new GridBagConstraints();
		gbc_lblOferta.insets = new Insets(0, 0, 5, 5);
		gbc_lblOferta.gridx = 1;
		gbc_lblOferta.gridy = 5;
		getContentPane().add(lblOferta, gbc_lblOferta);
		
		comboBoxOferta = new JComboBox();
		GridBagConstraints gbc_comboBoxOferta = new GridBagConstraints();
		gbc_comboBoxOferta.gridwidth = 2;
		gbc_comboBoxOferta.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxOferta.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOferta.gridx = 2;
		gbc_comboBoxOferta.gridy = 5;
		getContentPane().add(comboBoxOferta, gbc_comboBoxOferta);
		comboBoxOferta.addItem("Seleccionar");
		
		comboBoxEmpresa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
        		if (e.getStateChange() == ItemEvent.SELECTED && dataCargada == true && comboBoxEmpresa.getSelectedIndex() != 0) {
        			cargarOfertas((String) comboBoxEmpresa.getSelectedItem());
        		}
        	}
		});
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 7;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JRadioButton radioConfirmar = new JRadioButton("Confirmar");
		panel.add(radioConfirmar);
		radioConfirmar.setSelected(true);
		
		JRadioButton radioRechazar = new JRadioButton("Rechazar");
		panel.add(radioRechazar);
		
		ButtonGroup G1 = new ButtonGroup();
		G1.add(radioRechazar);
		G1.add(radioConfirmar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkInput()) {
					if (radioConfirmar.isSelected()) {
						aceptarrechazar((String) comboBoxOferta.getSelectedItem(), "Aceptar");
						clearCombo();
						cargarEmpresas();
					} else if (radioRechazar.isSelected()) {
						aceptarrechazar((String) comboBoxOferta.getSelectedItem(), "Rechazar");
						clearCombo();
						cargarEmpresas();
					}
				}
			}
		});
		
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 10;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 10;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				clearCombo();
				setVisible(false);
			}
		});
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_2 = new GridBagConstraints();
		gbc_rigidArea_2.gridx = 4;
		gbc_rigidArea_2.gridy = 11;
		getContentPane().add(rigidArea_2, gbc_rigidArea_2);
	}
	
	public void cargarEmpresas() {
    	comboBoxEmpresa.removeAllItems();
    	comboBoxEmpresa.addItem("Seleccionar");
    	Set<String> empresas = ctrlUsuario.listarEmpresas();
    	for (String empresa : empresas) {
    		comboBoxEmpresa.addItem(empresa);
    	}
    	dataCargada = true;
    }
	
	public void cargarOfertas(String nickempresa) {
		comboBoxOferta.removeAllItems();
		comboBoxOferta.addItem("Seleccionar");
		Set<String> ofertas = ctrlUsuario.listarOfertasIngresadas(nickempresa);
		for (String oferta: ofertas) {
			comboBoxOferta.addItem(oferta);
		}
	}
	
	public void aceptarrechazar(String nombreOferta, String estado) {
		if (estado == "Aceptar") {
			ctrlUsuario.aceptarRechazarOferta(nombreOferta, Estado.Confirmado);
		} else {
			ctrlUsuario.aceptarRechazarOferta(nombreOferta, Estado.Rechazado);
		}
		JOptionPane.showMessageDialog(this, "El estado de la oferta fue cambiado con exito.", "Aceptar/Rechazar Oferta", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void clearCombo() {
		comboBoxEmpresa.removeAllItems();
		comboBoxOferta.removeAllItems();
		comboBoxEmpresa.addItem("Seleccionar");
		comboBoxOferta.addItem("Seleccionar");
	}
	
	public boolean checkInput() {
		if (comboBoxOferta.getSelectedIndex() == 0 || comboBoxEmpresa.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa y/o una oferta laboral", "Aceptar/Rechazar Oferta", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
