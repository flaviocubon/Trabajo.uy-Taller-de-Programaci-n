package logica;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPaquete {
	private String nombre;
	private String descripcion;
    private double descuento;
    private Date fecha;
    private double costo;
    private int periodoValidez;
	private Map<String, DTAgrupa> tiposPublicacion;
	private String imagen;
	
	public DTPaquete() {}
	
	public DTPaquete(PaqueteTipoPublicacion paquete) {
		this.nombre = paquete.getNombre();
		this.descripcion = paquete.getDescripcion();
		this.descuento = paquete.getDescuento();
		this.fecha = paquete.getFecha();
		this.costo = paquete.getCosto();
		this.periodoValidez = paquete.getPeriodoValidez();
		this.tiposPublicacion = new HashMap<String, DTAgrupa>();
		Map<String, Agrupa> tipos = paquete.getTiposPublicacion();
		for (Entry<String, Agrupa> agrupaTipo : tipos.entrySet()) {
			DTAgrupa dataAgrupa = new DTAgrupa(agrupaTipo.getValue().getCant(), agrupaTipo.getValue().getTipo());
			tiposPublicacion.put(dataAgrupa.getNombreTipo(), dataAgrupa);
		}
		this.imagen = paquete.getImagen();
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setPeriodoValidez(int periodoValidez) {
		this.periodoValidez = periodoValidez;
	}

	public void setTiposPublicacion(Map<String, DTAgrupa> tiposPublicacion) {
		this.tiposPublicacion = tiposPublicacion;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Map<String, DTAgrupa> getTipos(){
		return tiposPublicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getDescuento() {
		return descuento;
	}

	public Date getFecha() {
		return fecha;
	}

	public double getCosto() {
		return costo;
	}

	public int getPeriodoValidez() {
		return periodoValidez;
	}
	
	public String getImagen() {
		return imagen;
	}

	public Map<String, DTAgrupa> getTiposPublicacion() {
		return tiposPublicacion;
	}
}