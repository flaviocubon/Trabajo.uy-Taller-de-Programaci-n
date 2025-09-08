package logica;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTTipoPublicacion {
	private String nombre;
	private String descripcion;
	private int exposicion;
	private double duracion;
	private double costo;
	private Date fecha;
	
	public DTTipoPublicacion() {
		
	}
	
	public DTTipoPublicacion(String nombre, String descripcion, int exposicion, double duracion, double costo, Date fecha) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.exposicion = exposicion;
		this.duracion = duracion;
		this.costo = costo;
		this.fecha = fecha;
	}

	public boolean compare(DTTipoPublicacion tipoIn) {
		return nombre == tipoIn.nombre && descripcion == tipoIn.descripcion && exposicion == tipoIn.exposicion && duracion == tipoIn.duracion
				&& costo == tipoIn.costo && fecha.compareTo(tipoIn.fecha) == 0;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setExposicion(int exposicion) {
		this.exposicion = exposicion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getExposicion() {
		return exposicion;
	}

	public double getDuracion() {
		return duracion;
	}

	public double getCosto() {
		return costo;
	}

	public Date getFecha() {
		return fecha;
	}
}