package logica;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Postulante extends Usuario {
	private String nacionalidad;
	private Date fechaNacimiento;
	private Map<String, Postulacion> postulaciones;
	private Set<String> favoritos;
	
	public void postularAOferta(OfertaLaboral oferta, Date fecha, String curriculum, String descripcion, String video) {
		if (!this.postulaciones.containsKey(oferta.getNombre())) {
			Postulacion post = new Postulacion(fecha, curriculum, descripcion, oferta);
			post.setVideo(video);
			this.postulaciones.put(oferta.getNombre(), post);
		}
		oferta.agregarPostulante(this);
	}
	
	public Postulante(String nickname, String mail, String nombre, String apellido, String nacionalidad, Date fechaNacimiento) {
		super(nickname, mail, nombre, apellido);
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.postulaciones= new HashMap<String, Postulacion>();
		this.favoritos= new HashSet<String>();
	}
	
	public Postulante(String nickname, String mail, String nombre, String apellido, String nacionalidad, Date fechaNacimiento, String imagen, String pass) {
		super(nickname, mail, nombre, apellido, imagen, pass);
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.postulaciones= new HashMap<String, Postulacion>();
		this.favoritos= new HashSet<String>();
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Map<String, Postulacion> getPostulaciones() {
		return postulaciones;
	}
	public void setPostulaciones(Map<String, Postulacion> postulaciones) {
		this.postulaciones = postulaciones;
	}
	public void removerPostulacion(String nombreOferta) {
		postulaciones.remove(nombreOferta);
	}
	public void agregarFavorito(String fav) {
		favoritos.add(fav);
	}
	public void quitarFavorito(String fav) {
		if(favoritos.contains(fav)) favoritos.remove(fav);
	}
	public Set<String> getOfertasFavoritas(){
		return favoritos;
	}
	public boolean esOfertaFavorita(String oferta) {
		return favoritos.contains(oferta);
	}
}
