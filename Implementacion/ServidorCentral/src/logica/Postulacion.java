package logica;

import java.time.LocalDate;
import java.util.Date;

public class Postulacion {
	private Date fechaPostulacion;
	private String resumenCV;
	private String descripcion;
	private OfertaLaboral oferta;
	private String video;
	private int orden;
	private LocalDate fechaOrden;
	
	public Postulacion(Date fechaPostulacion, String resumenCV, String descripcion, OfertaLaboral oferta) {
		super();
		this.fechaPostulacion = fechaPostulacion;
		this.resumenCV = resumenCV;
		this.descripcion = descripcion;
		this.oferta = oferta;
		this.video="";
		this.orden=0;
		this.fechaOrden=LocalDate.now();
	}
	public Date getFechaPostulacion() {
		return fechaPostulacion;
	}

	public String getResumenCV() {
		return resumenCV;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public OfertaLaboral getOferta() {
		return oferta;
	}
	
	public String getVideo() {
		return video;
	}
	public void setVideo(String value) {
		this.video=value;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int value) {
		this.orden=value;
	}
	public LocalDate getFechaOrden() {
		return fechaOrden;
	}
	public void setFechaOrden(LocalDate value) {
		this.fechaOrden=value;
	}
}
