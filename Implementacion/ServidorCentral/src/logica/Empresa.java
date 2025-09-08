package logica;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Empresa extends Usuario {
	private String nombreEmpresa;
	private String descripcion;
	private String link;
	private Map<String, OfertaLaboral> ofertas;
	private Map<String, Compra> compras;
	

	public Empresa(String nickname, String mail, String nombre, String apellido, String nombreEmpresa,
		String descripcion, String link) {
		super(nickname, mail, nombre, apellido);
		this.nombreEmpresa = nombreEmpresa;
		this.descripcion = descripcion;
		this.link = link;
		this.ofertas= new HashMap<String, OfertaLaboral>();
		this.compras= new HashMap<String, Compra>();
	}
	
	public Empresa(String nickname, String mail, String nombre, String apellido, String nombreEmpresa,
			String descripcion, String link, String imagen, String pass) {
			super(nickname, mail, nombre, apellido, imagen, pass);
			this.nombreEmpresa = nombreEmpresa;
			this.descripcion = descripcion;
			this.link = link;
			this.ofertas= new HashMap<String, OfertaLaboral>();
			this.compras= new HashMap<String, Compra>();
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

	public Map<String, OfertaLaboral> getOfertas() {
		return ofertas;
	}

	public void agregarOferta(OfertaLaboral oferta) {
		if (ofertas==null) {
			ofertas= new HashMap<String, OfertaLaboral>();
		}
		this.ofertas.put(oferta.getNombre(), oferta);
	}
	public Set<String> getNombreOfertas(){
		Set<String> lista = null;
		if (ofertas != null) {
			lista = new HashSet<String>(ofertas.keySet());
		}
		return lista;
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
	public void setOfertas(Map<String, OfertaLaboral> ofertas) {
		ofertas = ofertas;
	}
	public void comprarPaquete(String nombrePaquete, LocalDate fecha) {
		ManejadorPaquete manejadorPaquete= ManejadorPaquete.getInstance();
		PaqueteTipoPublicacion paquete= manejadorPaquete.obtenerPaquete(nombrePaquete);
		Compra compraPaquete= new Compra(this, paquete, fecha);
		if (compras==null) {
			compras= new HashMap<String, Compra>();
		}
		compras.put(nombrePaquete, compraPaquete);
		paquete.setComprado(true);
	}
	public void canjearTipoDeCompra(String nombreTipo, String nombrePaquete) {
		Compra compraPaquete= compras.get(nombrePaquete);
		compraPaquete.canjearTipo(nombreTipo);
	}
	public Set<String> listarPaquetesCompradosPorTipoSinCanjear(String nombreTipo) {
		Set<String> paquetesAListar= new HashSet<String>();
		if (compras!=null) {
			Collection<Compra> paquetes= compras.values();
			for (Compra compraPaquete: paquetes) {
				Set<String> tiposDelPaquete= compraPaquete.getTiposNoCanjeados().keySet();
				if (tiposDelPaquete.contains(nombreTipo)) {
					paquetesAListar.add(compraPaquete.getProducto().getNombre());
				}
			}
		}
		return paquetesAListar;
	}
	public Map<String, Compra> getCompras(){
		return compras;
	}
	
	public void removerOferta(String nombreOferta) {
		ofertas.remove(nombreOferta);
	}
}
