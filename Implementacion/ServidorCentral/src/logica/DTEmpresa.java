package logica;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTEmpresa extends DTUsuario {

		private String nombreEmpresa;
		private String descripcion;
		private String link;
		private Set<String> nombreOfertas;
		private Set<String> compras;
		
		public DTEmpresa() {
			super();
		}
		
		public void setNombreEmpresa(String nombreEmpresa) {
			this.nombreEmpresa = nombreEmpresa;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public void setNombreOfertas(Set<String> nombreOfertas) {
			this.nombreOfertas = nombreOfertas;
		}

		public void setCompras(Set<String> compras) {
			this.compras = compras;
		}

		public DTEmpresa(String nickname, String mail, String nombre, String apellido, String nombreEmpresa,
				String descripcion, String link, Set<String> nombreOfertas, String imagen, Set<String> seguidos, Set<String> seguidores) {
			super(nickname, mail, nombre, apellido, imagen, seguidos, seguidores);
			this.nombreEmpresa = nombreEmpresa;
			this.descripcion = descripcion;
			this.link = link;
			this.nombreOfertas=nombreOfertas;
		}
		public DTEmpresa(String nickname, String mail, String nombre, String apellido, String nombreEmpresa,
				String descripcion, String link, Set<String> nombreOfertas, String imagen, Set<String> compras, Set<String> seguidos, Set<String> seguidores) {
			super(nickname, mail, nombre, apellido, imagen, seguidos, seguidores);
			this.nombreEmpresa = nombreEmpresa;
			this.descripcion = descripcion;
			this.link = link;
			this.nombreOfertas=nombreOfertas;
			this.compras = compras;
		}
		public DTEmpresa(Empresa empresa) {
			this(
				empresa.getNickname(),
				empresa.getMail(), empresa.getNombre(),
				empresa.getApellido(),
				empresa.getNombreEmpresa(),
				empresa.getDescripcion(),
				empresa.getLink(),
				empresa.getNombreOfertas(),
				empresa.getImagen(),
				empresa.getCompras().keySet(),
				empresa.getSeguidos().keySet(),
				empresa.getSeguidores().keySet());
		}
		public String getNombreEmpresa() {
			return nombreEmpresa;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public String getLink() {
			return link;
		}
		public Set<String> getNombreOfertas(){
			return nombreOfertas;
		}
		public Set<String> getCompras() {
			return compras;
		}
}

