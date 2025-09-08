package logica.DAO;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.Usuario;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TipoUsuario",
discriminatorType=DiscriminatorType.STRING)
public class UsuarioDAO {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
	@Column(unique = true, nullable = false)
	private String nickname;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido;
	private String imagen;
	
	public UsuarioDAO() {
		
	}
	
	public UsuarioDAO(Usuario usuario) {
		nickname= usuario.getNickname();
		email= usuario.getMail();
		nombre= usuario.getNombre();
		apellido= usuario.getApellido();
		imagen = usuario.getImagen();
	}
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


}
