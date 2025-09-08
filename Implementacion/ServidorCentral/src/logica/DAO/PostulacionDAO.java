package logica.DAO;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.Postulacion;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class PostulacionDAO {
	@Id
	@OneToOne(cascade = CascadeType.PERSIST)
	private PostulanteDAO postulante;
	@Id
	@OneToOne(cascade = CascadeType.PERSIST)
	private OfertaDAO oferta;
	@Column(name = "fechaPostulacion", columnDefinition = "DATE", nullable = false)
	private Date fechaPostulacion;
	@Column(name = "RESUMENCV", length = 1000, nullable = false)
	private String resumenCV;
	@Column(name = "DESCRIPCION", length = 1000, nullable = false)
	private String descripcion;
	
	public PostulacionDAO() {
		
	}
	
	public PostulacionDAO(Postulacion postulacion, PostulanteDAO postulante, OfertaDAO oferta) {
		this.postulante=postulante;
		this.oferta=oferta;
		fechaPostulacion=postulacion.getFechaPostulacion();
		resumenCV=postulacion.getResumenCV();
		descripcion=postulacion.getDescripcion();
	}

	public PostulanteDAO getNombrePostulante() {
		return postulante;
	}

	public void setNombrePostulante(PostulanteDAO nombrePostulante) {
		this.postulante = nombrePostulante;
	}

	public OfertaDAO getOferta() {
		return oferta;
	}

	public void setOferta(OfertaDAO oferta) {
		this.oferta = oferta;
	}

	public Date getFechaPostulacion() {
		return fechaPostulacion;
	}

	public void setFechaPostulacion(Date fechaPostulacion) {
		this.fechaPostulacion = fechaPostulacion;
	}

	public String getResumenCV() {
		return resumenCV;
	}

	public void setResumenCV(String resumenCV) {
		this.resumenCV = resumenCV;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
