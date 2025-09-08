package logica;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuario {
	private String nickname;
	private String mail;
	private String nombre;
	private String apellido;
	private String imagen = null;
	private Set<String> seguidos;
	private Set<String> seguidores;
	
	public Set<String> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(Set<String> seguidos) {
		this.seguidos = seguidos;
	}

	public Set<String> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(Set<String> seguidores) {
		this.seguidores = seguidores;
	}

	public DTUsuario() {
		
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

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getApellido() {
		return apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public String getMail() {
		return mail;
	}
	public String getNickname() {
		return nickname;
	}
	
	public String getImagen() {
		if (imagen == null)
			return "";
		else
			return imagen;
	}
	
	public DTUsuario(String nickname, String mail, String nombre, String apellido, String imagen, Set<String> seguidos, Set<String> seguidores) {
		this.nickname = nickname;
		this.mail = mail;
		this.nombre = nombre;
		this.apellido = apellido;
		this.imagen = imagen;
		this.seguidores = seguidores;
		this.seguidos = seguidos;
	}
	
	public DTUsuario(String nickname, String mail, String nombre, String apellido) {
		this.nickname = nickname;
		this.mail = mail;
		this.nombre = nombre;
		this.apellido = apellido;
	}
}