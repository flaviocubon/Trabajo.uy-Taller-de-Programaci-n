package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import webservices.wsTipos;
import webservices.wsUsuarios;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class iniciarDetenerWS extends JInternalFrame{
	
	public static wsTipos tipos = null;
	public static wsUsuarios usuarios = null;
	private String separador = FileSystems.getDefault().getSeparator();
    private String rutaPorDefecto = System.getProperty("user.home") + separador + "TrabajoUY_Utilidades" + separador + "config.properties";
	
	private JButton btnIniciar, btnDetener, btnElegirRuta;
	private JLabel lblEstadoWS, lblRutaActual;
	
	public iniciarDetenerWS() {
		setResizable(true);
	    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    setClosable(true);
	    setTitle("Iniciar/Detener WS");
	    setBounds(0, 0, 395, 400);
	    setMinimumSize(new Dimension(395,400));
	    setMaximumSize(new Dimension(395,400));
	    BorderLayout bLayout = new BorderLayout();
	    getContentPane().setLayout(bLayout);
	    
	    JPanel panelPrincipal = new JPanel();
	    panelPrincipal.setPreferredSize(new Dimension(400, 350));
	    GridBagLayout gbl_panelPrincipal = new GridBagLayout();
	    gbl_panelPrincipal.columnWidths = new int[]{384, 0};
	    gbl_panelPrincipal.rowHeights = new int[]{60, 35, 90, 90, 90, 0};
	    gbl_panelPrincipal.columnWeights = new double[]{0.0, Double.MIN_VALUE};
	    gbl_panelPrincipal.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    panelPrincipal.setLayout(gbl_panelPrincipal);
	    
	    lblEstadoWS = new JLabel(getEstadoWS());
	    lblEstadoWS.setForeground((getEstadoWS().equals("Detenido"))?new Color(255, 0, 0):new Color(0, 128, 0));
	    lblEstadoWS.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblEstadoWS.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblEstadoWS = new GridBagConstraints();
	    gbc_lblEstadoWS.fill = GridBagConstraints.BOTH;
	    gbc_lblEstadoWS.insets = new Insets(0, 0, 5, 0);
	    gbc_lblEstadoWS.gridx = 0;
	    gbc_lblEstadoWS.gridy = 0;
	    panelPrincipal.add(lblEstadoWS, gbc_lblEstadoWS);
	    
	    lblRutaActual = new JLabel(rutaPorDefecto);
	    lblRutaActual.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_lblRutaActual = new GridBagConstraints();
	    gbc_lblRutaActual.fill = GridBagConstraints.BOTH;
	    gbc_lblRutaActual.insets = new Insets(0, 0, 5, 0);
	    gbc_lblRutaActual.gridx = 0;
	    gbc_lblRutaActual.gridy = 1;
	    panelPrincipal.add(lblRutaActual, gbc_lblRutaActual);
	    
	    btnElegirRuta = new JButton("Elegir ruta");
	    btnElegirRuta.setPreferredSize(new Dimension(90, 30));
	    btnElegirRuta.setHorizontalAlignment(SwingConstants.CENTER);
	    btnElegirRuta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser  selectorArchivo = new JFileChooser();
				int resultadoEleccion = selectorArchivo.showDialog(panelPrincipal,"Seleccione archivo properties");
				if (resultadoEleccion == JFileChooser.APPROVE_OPTION){
					rutaPorDefecto = selectorArchivo.getSelectedFile().getAbsolutePath();
					lblRutaActual.setText(rutaPorDefecto);					
	            }
				
			}
		});
	    GridBagConstraints gbc_btnElegirRuta = new GridBagConstraints();
	    gbc_btnElegirRuta.fill = GridBagConstraints.BOTH;
	    gbc_btnElegirRuta.insets = new Insets(0, 0, 5, 0);
	    gbc_btnElegirRuta.gridx = 0;
	    gbc_btnElegirRuta.gridy = 2;
	    panelPrincipal.add(btnElegirRuta, gbc_btnElegirRuta);
	    
	    btnIniciar = new JButton("Iniciar");
	    btnIniciar.setPreferredSize(new Dimension(90, 30));
	    btnIniciar.setHorizontalAlignment(SwingConstants.CENTER);
	    btnIniciar.setEnabled(getEstadoWS().equals("Detenido"));
	    btnIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				publicar();
				lblEstadoWS.setForeground(new Color(0, 128, 0));
				lblEstadoWS.setText(getEstadoWS());
				btnIniciar.setEnabled(false);
				btnDetener.setEnabled(true);
				
			}
		});
	    GridBagConstraints gbc_btnIniciar = new GridBagConstraints();
	    gbc_btnIniciar.fill = GridBagConstraints.BOTH;
	    gbc_btnIniciar.insets = new Insets(0, 0, 5, 0);
	    gbc_btnIniciar.gridx = 0;
	    gbc_btnIniciar.gridy = 3;
	    panelPrincipal.add(btnIniciar, gbc_btnIniciar);	    
	    
	    
	    getContentPane().add(panelPrincipal);
	    
	    btnDetener = new JButton("Detener");
	    btnDetener.setPreferredSize(new Dimension(90, 30));
	    btnDetener.setHorizontalAlignment(SwingConstants.CENTER);
	    btnDetener.setEnabled(getEstadoWS().equals("Iniciado"));
	    btnDetener.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				desPublicar();
				lblEstadoWS.setForeground(new Color(255, 0, 0));
				lblEstadoWS.setText(getEstadoWS());
				btnIniciar.setEnabled(true);
				btnDetener.setEnabled(false);
				
			}
		});
	    GridBagConstraints gbc_btnDetener = new GridBagConstraints();
	    gbc_btnDetener.fill = GridBagConstraints.BOTH;
	    gbc_btnDetener.gridx = 0;
	    gbc_btnDetener.gridy = 4;
	    panelPrincipal.add(btnDetener, gbc_btnDetener);
	}
	
	public String getEstadoWS() {
		return (tipos == null && usuarios == null) ? "Detenido" : "Iniciado";
	}
	
	private void publicar() {
		tipos = new wsTipos();
		usuarios = new wsUsuarios();
        tipos.publicar(rutaPorDefecto);
        usuarios.publicar(rutaPorDefecto);
	}
	
	private void desPublicar() {
		tipos.detener();
		usuarios.detener();
		tipos = null;
		usuarios = null;
	}

}
