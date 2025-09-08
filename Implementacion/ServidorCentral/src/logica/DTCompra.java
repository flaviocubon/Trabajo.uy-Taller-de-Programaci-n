package logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompra {
	private String comprador;
	private String producto;
	private LocalDate fechaCompra;
	private Map<String, DTAgrupa> tiposNoCanjeados;
	private double costo;
	@XmlElement
	@XmlJavaTypeAdapter(LocalDateSerializer.class)
	private LocalDate fechaVencimiento;
	private String imagenPaquete;
	
	public DTCompra() {
		
	};
	
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public void setTiposNoCanjeados(Map<String, DTAgrupa> tiposNoCanjeados) {
		this.tiposNoCanjeados = tiposNoCanjeados;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public void setImagenPaquete(String imagenPaquete) {
		this.imagenPaquete = imagenPaquete;
	}

	public DTCompra(String comprador, String paquete, String imagenPaquete, LocalDate fecha, double costo, LocalDate fechaVencimiento, Map<String, Agrupa> tipos){
		this.producto = paquete;
		this.comprador = comprador;
		fechaCompra = fecha;
		this.costo = costo;
		this.fechaVencimiento = fechaVencimiento;
		this.tiposNoCanjeados = new HashMap<String, DTAgrupa>();
		for (Entry<String, Agrupa> agrupaTipo : tipos.entrySet()) {
			DTAgrupa dataAgrupa = new DTAgrupa(agrupaTipo.getValue().getCant(), agrupaTipo.getValue().getTipo());
			this.tiposNoCanjeados.put(dataAgrupa.getNombreTipo(), dataAgrupa);
		}
		this.imagenPaquete = imagenPaquete;
	}

	public String getComprador() {
		return comprador;
	}

	public String getProducto() {
		return producto;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public Map<String, DTAgrupa> getTiposNoCanjeados() {
		return tiposNoCanjeados;
	}

	public double getCosto() {
		return costo;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public String getStringVencimiento() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		String fecha = formatter.format(fechaVencimiento);
		return fecha;
	}
	public String getImagenPaquete() {
		return imagenPaquete;
	}
}