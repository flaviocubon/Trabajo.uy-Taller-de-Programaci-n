package logica.DAO;

import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.LocalTimeSerializer;
import logica.OfertaLaboral;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class OfertaDAO {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOferta;
	@Column(unique = true, nullable = false)
	private String nombreOferta;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idEmpresa")
	private EmpresaDAO empresa;
	@Column(name = "DESCRIPCION", nullable = false, length = 1000)
	private String descripcion;
	@Column(nullable = false)
	private String ciudad;
	@Column(nullable = false)
	private String departamento;
	@XmlElement
	@XmlJavaTypeAdapter(LocalTimeSerializer.class)
	@Column(name = "horarioInicio", columnDefinition = "TIME", nullable = false)
	private LocalTime horarioInicio;
	@XmlElement
	@XmlJavaTypeAdapter(LocalTimeSerializer.class)
	@Column(name = "horarioFin", columnDefinition = "TIME", nullable = false)
	private LocalTime horarioFin;
	@Column(nullable = false)
	private int remuneracion;
	@Column(name = "fechaAlta", columnDefinition = "DATE", nullable = false)
	private Date fechaAlta;
	@Column(nullable = false)
	private String tipoPublicacion;
	@Column(name = "fechaBaja", columnDefinition = "DATE", nullable = false)
	private Date fechaBaja;
	@Column(nullable = false)
	private double costo;
	private String paquete=null;
	@Column(name = "IMAGEN", length = 1000)
	private String imagen;
	
	public OfertaDAO() {
		
	}
	
	
	public OfertaDAO(OfertaLaboral oferta, EmpresaDAO empresa, Date fechaBaja) {
		nombreOferta= oferta.getNombre();
		this.empresa=empresa;
		descripcion=oferta.getDescripcion();
		ciudad=oferta.getCiudad();
		departamento=oferta.getDepartamento();
		horarioInicio=oferta.getHorario().getHorarioInicio();
		horarioFin=oferta.getHorario().getHorarioFin();
		remuneracion=oferta.getRemuneracion();
		fechaAlta=oferta.getFecha();
		tipoPublicacion= oferta.getTipo().getNombre();
		this.fechaBaja=fechaBaja;
		costo= oferta.getCosto();
		if(oferta.getCompra()!=null)paquete=oferta.getCompra().getNombre();
		imagen=oferta.getImagen();
	}

	public int getIdOferta() {
		return idOferta;
	}
	public EmpresaDAO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaDAO empresa) {
		this.empresa = empresa;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public String getNombreOferta() {
		return nombreOferta;
	}
	public void setNombreOferta(String nombreOferta) {
		this.nombreOferta = nombreOferta;
	}
	public EmpresaDAO getNombreEmpresa() {
		return empresa;
	}
	public void setNombreEmpresa(EmpresaDAO empresa) {
		this.empresa = empresa;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public LocalTime getHorarioFin() {
		return horarioFin;
	}
	public void setHorarioFin(LocalTime horarioFin) {
		this.horarioFin = horarioFin;
	}
	public int getRemuneracion() {
		return remuneracion;
	}
	public void setRemuneracion(int remuneracion) {
		this.remuneracion = remuneracion;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getTipoPublicacion() {
		return tipoPublicacion;
	}
	public void setTipoPublicacion(String tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public double getCosto() {
		return costo;
	}
	public String getPaquete() {
		return paquete;
	}
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}
