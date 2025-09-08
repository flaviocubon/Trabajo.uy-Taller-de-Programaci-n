package logica;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
	private String nickname;
	private String mail;
	private String nombre;
	private String apellido;
	private String imagen;
	private String pass ="";
	private Map<String, Usuario> seguidos;
	private Map<String, Usuario> seguidores;
	private Map<String, OfertaLaboral> favoritas;
	
	public Usuario(String nickname, String mail, String nombre, String apellido) {
		super();
		this.nickname = nickname;
		this.mail = mail;
		this.nombre = nombre;
		this.apellido = apellido;
		this.seguidos = new HashMap<String, Usuario>();
		this.seguidores = new HashMap<String, Usuario>();
		this.favoritas = new HashMap<String, OfertaLaboral>();
	}
	
	public Usuario(String nickname, String mail, String nombre, String apellido, String imagen, String pass) {
		super();
		this.nickname = nickname;
		this.mail = mail;
		this.nombre = nombre;
		this.apellido = apellido;
		this.imagen = imagen;
		this.pass = pass;
		this.seguidos = new HashMap<String, Usuario>();
		this.seguidores = new HashMap<String, Usuario>();
		this.favoritas = new HashMap<String, OfertaLaboral>();
	}
	
	public String getNickname() {
		return nickname;
	}
	public String getMail() {
		return mail;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	
	public String getImagen() {
		return imagen;
	}
	public String getPass() {
		return pass;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Map<String, Usuario> getSeguidores() {
		return this.seguidores;
	}
	public void addSeguidor(Usuario user) {
		this.seguidores.put(user.getNickname(), user);
	}
	public Map<String, Usuario> getSeguidos(){
		return this.seguidos;
	}
	public void addSeguido(Usuario user) {
		this.seguidos.put(user.getNickname(), user);
	}
	public Map<String, OfertaLaboral> getFavoritas() {
		return this.favoritas;
	}
	public void addFavorita(OfertaLaboral oferta) {
		this.favoritas.put(oferta.getNombre(), oferta);
	}
	public void borrarSeguidor(String nickname) {
		this.seguidores.remove(nickname);
	}
	public void borrarSeguido(String nickname) {
		this.seguidos.remove(nickname);
	}
	public void borrarFavorita(String nombreOferta) {
		this.favoritas.remove(nombreOferta);
	}
}
