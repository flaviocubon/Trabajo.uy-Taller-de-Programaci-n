package logica.DAO;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.Postulante;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class PostulanteDAO extends UsuarioDAO {
	@Column(nullable = false)
	private String nacionalidad;
	@Column(name="fecha_nacimiento", columnDefinition="DATE", nullable = false)
	private Date fecha_nacimiento;
	
	public PostulanteDAO() {
		
	}
	
	public PostulanteDAO(Postulante postulante) {
		super(postulante);
		nacionalidad=postulante.getNacionalidad();
		fecha_nacimiento= postulante.getFechaNacimiento();
	}

	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

}
