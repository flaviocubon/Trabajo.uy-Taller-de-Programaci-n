package logica.DAO;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.Empresa;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class EmpresaDAO extends UsuarioDAO {
	@Column(name = "DESCRIPCION", nullable = false, length = 1000)
	private String descripcion;
	private String sitio_web;
	
	public EmpresaDAO() {
		
	}
	
	public EmpresaDAO(Empresa empresa) {
		super(empresa);
		descripcion=empresa.getDescripcion();
		sitio_web=empresa.getLink();
	}

	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSitio_web() {
		return sitio_web;
	}
	public void setSitio_web(String sitio_web) {
		this.sitio_web = sitio_web;
	}
}
