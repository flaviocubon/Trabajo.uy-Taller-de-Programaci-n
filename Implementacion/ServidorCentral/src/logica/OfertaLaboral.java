package logica;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OfertaLaboral {
	
	private Empresa publicante;
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private DTHorario horario;
	private int remuneracion;
	private Date fecha;
	private Map<String, Postulante> postulantes;
	private TipoPublicacion tipo;
	private Set<String> keywords;
	private Estado estado;
	private PaqueteTipoPublicacion paquete;
	private String imagen;
	private int visitas;
	private Set<String> favoritos;
	
	public OfertaLaboral(String nombre, Empresa publicante, String descripcion, String ciudad, String departamento, DTHorario horario, int remuneracion,
			Date fecha, TipoPublicacion tipo, Set<String> keywords, String imagen) {
		super();
		this.nombre = nombre;
		this.descripcion=descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fecha = fecha;
		this.tipo = tipo;
		this.publicante=publicante;
		this.keywords = Set.copyOf(keywords);
		this.postulantes= new HashMap<String, Postulante>();
		this.paquete=null;
		this.estado=Estado.Ingresado;
		this.imagen=imagen;
		this.visitas=0;
		this.favoritos= new HashSet<String>();
	}
	public OfertaLaboral() {
		
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
		double costo;
		if (this.paquete==null) {
				costo=this.tipo.getCosto();
			}
			else {
				double descuento=paquete.getDescuento();
				costo=tipo.getCosto()*((100-descuento)/100);
			}
		return costo;
	}
	public Map<String, Postulante> getPostulantes() {
		return postulantes;
	}

	public TipoPublicacion getTipo() {
		return tipo;
	}

	public Set<String> getKeywords() {
		return keywords;
	}

	public PaqueteTipoPublicacion getCompra() {
		return paquete;
	}
	public void setPaquete(PaqueteTipoPublicacion paquete) {
		this.paquete = paquete;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public DTHorario getHorario() {
		return horario;
	}
	public Boolean existePostulante(String nombre) {
		return postulantes.containsKey(nombre);
	}
	public void agregarPostulante(Postulante post) {
		if (!postulantes.containsKey(post.getNickname())) {
			postulantes.put(post.getNickname(), post);
		}
	}
	public Empresa getPublicante() {
		return publicante;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@SuppressWarnings("deprecation")
	public boolean estaVencida() {
		LocalDate fechaActual= LocalDate.now();
		LocalDate fechaAlta=fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return fechaAlta.plusDays(tipo.getDuracion()).isBefore(fechaActual);
	}
	
	public void agregarVisita() {
		this.visitas=this.visitas+1;
	}
	public int getVisitas() {
		return visitas;
	}
	public void agregarQuitarFavorito(String postulante) {
		if(favoritos.contains(postulante)) {
			favoritos.remove(postulante);
		}else {
			favoritos.add(postulante);
		}
	}
	public int getCantFavoritos() {
		return favoritos.size();
	}
}