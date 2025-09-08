package logica;

import java.time.LocalDate;
import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPostulacion {

	private Date fechaPostulacion;
	private String resumenCV;
	private String descripcion;
	private String nombreOferta;
	private String postulante;
	private String imagenOferta = "";
	private String video="";
	private int orden=0;
	@XmlElement
	@XmlJavaTypeAdapter(LocalDateSerializer.class)
	private LocalDate fechaOrden;
	
	public DTPostulacion() {
		
	}
	
	public void setFechaPostulacion(Date fechaPostulacion) {
		this.fechaPostulacion = fechaPostulacion;
	}

	public void setResumenCV(String resumenCV) {
		this.resumenCV = resumenCV;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setNombreOferta(String nombreOferta) {
		this.nombreOferta = nombreOferta;
	}

	public void setPostulante(String postulante) {
		this.postulante = postulante;
	}

	public void setImagenOferta(String imagenOferta) {
		this.imagenOferta = imagenOferta;
	}

	public DTPostulacion(Date fechaPostulacion, String resumenCV, String descripcion, String nombreOferta, String post,String video,int orden,LocalDate fechaOrden, String imagen) {
		super();
		this.fechaPostulacion = fechaPostulacion;
		this.resumenCV = resumenCV;
		this.descripcion = descripcion;
		this.nombreOferta= nombreOferta;
		this.postulante = post;
		this.video=video;
		this.orden=orden;
		this.fechaOrden=fechaOrden;
		this.imagenOferta=imagen;
	}
	
	public DTPostulacion(Date fechaPostulacion, String nombreOferta, String imagen) {
		super();
		this.fechaPostulacion = fechaPostulacion;
		this.resumenCV = "";
		this.descripcion = "";
		this.nombreOferta= nombreOferta;
		this.imagenOferta = imagen;
		this.video="";
		this.orden=0;
		this.fechaOrden=LocalDate.now();
	}
	
	public DTPostulacion(String nicknamePostulante, Postulacion postulacion) {
		this(
				postulacion.getFechaPostulacion(),
				postulacion.getResumenCV(),
				postulacion.getDescripcion(),
				postulacion.getOferta().getNombre(),
				nicknamePostulante,
				postulacion.getVideo(),
				postulacion.getOrden(),
				postulacion.getFechaOrden(),
				postulacion.getOferta().getImagen());
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
	public String getNombreOferta() {
		return nombreOferta;
	}
	public String getPostulante() {
		return postulante;
	}
	public String getImagen() {
		return imagenOferta;
	}

	public String getImagenOferta() {
		return imagenOferta;
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
