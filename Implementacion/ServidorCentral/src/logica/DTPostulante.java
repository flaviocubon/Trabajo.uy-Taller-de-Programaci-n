package logica;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPostulante extends DTUsuario {
	

	private String nacionalidad;
	private Date fechaNacimiento;
	private Set<String> nombreOfertas;
	private Map<String, DTPostulacion> postulaciones;
	private Set<String> ofertasFavoritas;
	
	public DTPostulante() {
		super();
	}
	
	public void setOfertasFavoritas(Set<String> ofertasFavoritas) {
		this.ofertasFavoritas = ofertasFavoritas;
	}
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setNombreOfertas(Set<String> nombreOfertas) {
		this.nombreOfertas = nombreOfertas;
	}

	public void setPostulaciones(Map<String, DTPostulacion> postulaciones) {
		this.postulaciones = postulaciones;
	}

	public DTPostulante(String nickname, String mail, String nombre, String apellido, String nacionalidad, Date fechaNacimiento, Set<String> nombreOfertas, String imagen, Set<String> seguidos, Set<String> seguidores) {
		super(nickname, mail, nombre, apellido, imagen, seguidos, seguidores);
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.nombreOfertas=nombreOfertas;
	}
	
	public DTPostulante(String nickname, String mail, String nombre, String apellido, String nacionalidad, Date fechaNacimiento, Set<String> nombreOfertas, String imagen, Map<String, Postulacion> mapPostulaciones, Set<String> seguidos, Set<String> seguidores, Set<String> ofertasFavoritas) {
		super(nickname, mail, nombre, apellido, imagen, seguidos, seguidores);
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.nombreOfertas=nombreOfertas;
		this.postulaciones = new HashMap<String, DTPostulacion>();
		for (Entry<String, Postulacion> entry : mapPostulaciones.entrySet()) {
			Postulacion postulacion = entry.getValue();
			DTPostulacion dataPostulacion = new DTPostulacion(postulacion.getFechaPostulacion(), postulacion.getResumenCV(), postulacion.getDescripcion(), postulacion.getOferta().getNombre(), nickname, postulacion.getVideo(),postulacion.getOrden(),postulacion.getFechaOrden(), postulacion.getOferta().getImagen());
			this.postulaciones.put(dataPostulacion.getNombreOferta(), dataPostulacion);
		}
		this.ofertasFavoritas=ofertasFavoritas;
	}
	
	public DTPostulante(Postulante postulante) {
		this(
				postulante.getNickname(),
				postulante.getMail(),
				postulante.getNombre(),
				postulante.getApellido(),
				postulante.getNacionalidad(),
				postulante.getFechaNacimiento(),
				postulante.getPostulaciones().keySet(),
				postulante.getImagen(),
				postulante.getPostulaciones(),
				postulante.getSeguidos().keySet(),
				postulante.getSeguidores().keySet(),
				postulante.getOfertasFavoritas());
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public Set<String> getNombreOfertas(){
		return nombreOfertas;
	}
	public Map<String, DTPostulacion> getPostulaciones(){
		return postulaciones;
	}
	public Set<String> getOfertasFavoritas(){
		return ofertasFavoritas;
	}
}
