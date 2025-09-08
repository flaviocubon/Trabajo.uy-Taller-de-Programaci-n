package logica;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTOfertaLaboral {

	private String nombre;
	private String nombreEmpresa;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private DTHorario horario;
	private int remuneracion;
	private Date fecha;
	private double costo;
	private Set<DTPostulacion> postulaciones;
	private String tipo;
	private Set<String> keywords;
	private String imagen;
	private Estado estado;
	private String paquete;
	@XmlElement
	@XmlJavaTypeAdapter(LocalDateSerializer.class)
	private LocalDate fechaVencimiento;
	private int visitas;
	private int cant_favoritos;
	
	public DTOfertaLaboral() {
		
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public void setHorario(DTHorario horario) {
		this.horario = horario;
	}

	public void setRemuneracion(int remuneracion) {
		this.remuneracion = remuneracion;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setPostulaciones(Set<DTPostulacion> postulaciones) {
		this.postulaciones = postulaciones;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public DTOfertaLaboral(String nombre, String nombreEmpresa, String descripcion, String ciudad, String departamento, DTHorario horario, int remuneracion,
			Date fecha, String tipo, Set<String> keywords, Set<DTPostulacion> postulaciones, double costo, Estado estado, String paquete, String imagen, LocalDate fechaVencimiento, int cant_favoritos) {
		super();
		this.nombre = nombre;
		this.nombreEmpresa=nombreEmpresa;
		this.descripcion=descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fecha = fecha;
		this.tipo = tipo;
		this.costo=costo;
		this.keywords = keywords;
		this.postulaciones=postulaciones;
		this.nombreEmpresa = nombreEmpresa;
		this.estado=estado;
		this.paquete=paquete;
		this.imagen=imagen;
		this.fechaVencimiento=fechaVencimiento;
		this.cant_favoritos=cant_favoritos;
	}
	
	public int getCant_favoritos() {
		return cant_favoritos;
	}

	public void setCant_favoritos(int cant_favoritos) {
		this.cant_favoritos = cant_favoritos;
	}

	public DTOfertaLaboral(OfertaLaboral ofertaLaboral) {
		this(
				ofertaLaboral.getNombre(),
				ofertaLaboral.getPublicante().getNickname(),
				ofertaLaboral.getDescripcion(),
				ofertaLaboral.getCiudad(),
				ofertaLaboral.getDepartamento(),
				ofertaLaboral.getHorario(),
				ofertaLaboral.getRemuneracion(),
				ofertaLaboral.getFecha(),
				ofertaLaboral.getTipo().getNombre(),
				ofertaLaboral.getKeywords(),
				new HashSet<DTPostulacion>(),
				ofertaLaboral.getCosto(),
				ofertaLaboral.getEstado(),
				(ofertaLaboral.getCompra() != null) ? ofertaLaboral.getCompra().getNombre() : "",
				ofertaLaboral.getImagen(),
				LocalDate.ofInstant(ofertaLaboral.getFecha().toInstant(), ZoneId.systemDefault()),
				ofertaLaboral.getCantFavoritos());
		
		Set<DTPostulacion> dataPostulaciones = new HashSet<DTPostulacion>();
		ofertaLaboral.getPostulantes().forEach( (key,postulante)->{
			dataPostulaciones.add(new DTPostulacion(postulante.getNickname(), postulante.getPostulaciones().get(this.getNombre())));
		});

	}
	public String getNombre() {
		return nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public int getRemuneracion() {
		return remuneracion;
	}
	public Date getFecha() {
		return fecha;
	}
	public double getCosto() {
		return costo;
	}
	public Set<DTPostulacion> getPostulaciones() {
		return postulaciones;
	}
	public String getTipo() {
		return tipo;
	}
	public Set<String> getKeywords() {
		return keywords;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public DTHorario getHorario() {
		return horario;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public String getImagen() {
		if (imagen == null)
			return "";
		else
			return imagen;
	}
	public boolean estaVencida() {
		return fechaVencimiento.isBefore(LocalDate.now());
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public String getPaquete() {
		return this.paquete;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public int getVisitas() {
		return visitas;
	}
	
	public void setVisitas(int value) {
		this.visitas=value;
	}
}
