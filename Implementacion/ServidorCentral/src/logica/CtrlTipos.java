package logica;

import java.util.Date;
import java.util.HashSet;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


import excepciones.compraPaqueteRepetida;
import excepciones.nombrePaqueteRepetido;
import excepciones.nombreTipoPublicacionRepetido;
import excepciones.paqueteYaComprado;

public class CtrlTipos implements ITipos {

	public void ingresarDatosTipoPublicacion(String nombreT, String descripcionT, int exposicionT, Date fechaT,
			double costoT, int duracionT) throws nombreTipoPublicacionRepetido {
		ManejadorTipo muTipo = ManejadorTipo.getInstance();
		boolean existeNombre = muTipo.existeTipoPublicacion(nombreT);
		if (existeNombre) {
			throw new nombreTipoPublicacionRepetido("Ya existe un tipo de publicacion de nombre " + nombreT);
		}
			TipoPublicacion nuevoTipo = new TipoPublicacion(nombreT, descripcionT, exposicionT, fechaT, costoT, duracionT);
			muTipo.agregar(nuevoTipo);
	}
	
	public Set<String> listarPaquetes(){
		ManejadorPaquete muPaquete = ManejadorPaquete.getInstance();
		return muPaquete.obtenerPaquetes();
	}
	
	public Map<String, DTTipoPublicacion> obtenerDataTipos() {
		ManejadorTipo muTipo = ManejadorTipo.getInstance();
		return muTipo.obtenerDataTipos();
	}
	
	public Map<String, DTPaquete> obtenerDataPaquetes(){
		ManejadorPaquete muPaquete = ManejadorPaquete.getInstance();
		return muPaquete.obtenerDataPaquetes();
	}
	
	public void borrarTipos() {
		ManejadorTipo muTipo = ManejadorTipo.getInstance();
		muTipo.clear();
	}
	
	public void borrarPaquetes() {
		ManejadorPaquete muPaquete = ManejadorPaquete.getInstance();
		muPaquete.clear();
	}
	
	public Boolean ingresarKeyword(String keyword){
		ManejadorKeywords manejadorKey = ManejadorKeywords.getInstance();
		Boolean res = !manejadorKey.existeKeyword(keyword);
		if (res) {
			manejadorKey.agregarKeyword(keyword);
		}
		return res;
	}

	@Override
	public Set<String> listarTiposPublicacion() {
		ManejadorTipo muTipos = ManejadorTipo.getInstance();
		return muTipos.obtenerTipos();
	}
	
	public void agregarTipoPublicacion(String nombrePaquete, String nombreTipo, int cant) throws paqueteYaComprado {
		ManejadorTipo muTipos = ManejadorTipo.getInstance();
		ManejadorPaquete muPaquete = ManejadorPaquete.getInstance();
		TipoPublicacion tipo = muTipos.obtenerTipo(nombreTipo);
		PaqueteTipoPublicacion paquete = muPaquete.obtenerPaquete(nombrePaquete);
		if (paquete.getComprado()==false) {
			paquete.agregarTipo(tipo, cant);
		}
		else {
			throw new paqueteYaComprado("El paquete ya fue comprado por alguna empresa");
		}
	}
	
	public void ingresarDatosPaquete(String nombre, String descripcion, int validez, double descuento, Date fechaAlta, String imagen)throws nombrePaqueteRepetido {
		ManejadorPaquete muPaquete = ManejadorPaquete.getInstance();
		boolean existe = muPaquete.existePaquete(nombre);
		if (existe) {
			throw new nombrePaqueteRepetido("Ya existe un Paquete de nombre " + nombre);
		} else {
			PaqueteTipoPublicacion nuevoPaquete = new PaqueteTipoPublicacion(nombre, descripcion, descuento, fechaAlta, validez, imagen);
			muPaquete.agregarPaquete(nuevoPaquete);
		}
	}
	
	public Boolean empresaPoseePaquete(String nombreEmpresa, String nombrePaquete) {
		ManejadorUsuario muUsuario= ManejadorUsuario.getInstance();
		Empresa empresa = (Empresa) muUsuario.getUsuario(nombreEmpresa);
		return empresa.getCompras()!=null && empresa.getCompras().containsKey(nombrePaquete);
	}
	
	public void comprarPaquete(String nombreEmpresa, String nombrePaquete, LocalDate fecha) throws compraPaqueteRepetida {
		ManejadorUsuario muUsuario= ManejadorUsuario.getInstance();
		Empresa empresa = (Empresa) muUsuario.getUsuario(nombreEmpresa);
		if (empresa.getCompras()!=null && empresa.getCompras().containsKey(nombrePaquete)) throw new compraPaqueteRepetida("La empresa ya compro el paquete: " + nombrePaquete);
		else {
			empresa.comprarPaquete(nombrePaquete, fecha);
		}
	}

	@Override
	public void comprarPaquete(String nombreEmpresa, String nombrePaquete) throws compraPaqueteRepetida {
		// TODO Auto-generated method stub
		comprarPaquete(nombreEmpresa, nombrePaquete, LocalDate.now());
	}
	
	public Set<String> listarPaquetesDisponiblesParaUsuarioYTipo(String nickname, String nombreTipo){
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Empresa empresa = (Empresa) manejadorU.getUsuario(nickname);
		return empresa.listarPaquetesCompradosPorTipoSinCanjear(nombreTipo);
	}
	
	@Override
	public DTPaquete obtenerDatosPaquete(String nombrePaquete) {
		// TODO Auto-generated method stub
		ManejadorPaquete manejadorPaquete = ManejadorPaquete.getInstance();
		if (manejadorPaquete.existePaquete(nombrePaquete)) {
			PaqueteTipoPublicacion paquete = manejadorPaquete.obtenerPaquete(nombrePaquete);
			DTPaquete dataPaquete = new DTPaquete(paquete);
			return dataPaquete;
		}
		return null;
	}
	
	public void canjearOfertaDePaquete(String nombreEmpresa, String nombreOferta, String nombrePaquete) {
		ManejadorUsuario muUsuario= ManejadorUsuario.getInstance();
		Empresa empresa = (Empresa) muUsuario.getUsuario(nombreEmpresa);
		ManejadorOferta muOferta= ManejadorOferta.getInstancia();
		OfertaLaboral oferta= muOferta.obtenerOferta(nombreOferta);
		ManejadorPaquete maPaquete= ManejadorPaquete.getInstance();
		String nombreTipo= oferta.getTipo().getNombre();
		oferta.setPaquete(maPaquete.obtenerPaquete(nombrePaquete));
		empresa.canjearTipoDeCompra(nombreTipo, nombrePaquete);
	}
	
	public Set<DTCompra> obtenerDatosCompra(String nombreEmpresa) {
		ManejadorUsuario mUsuario = ManejadorUsuario.getInstance();
		Empresa empresa = (Empresa) mUsuario.getUsuario(nombreEmpresa);
		Map<String, Compra> compras = empresa.getCompras();
		Set<DTCompra> datosCompra = new HashSet<DTCompra>();
		for (Entry<String, Compra> entry : compras.entrySet()) {
			Compra compra = entry.getValue();
			DTCompra dtCompra = new DTCompra(compra.getComprador().getNickname(), compra.getProducto().getNombre(), compra.getProducto().getImagen(), compra.getFechaCompra(), compra.getCosto(), compra.getFechaVencimiento(), compra.getTiposNoCanjeados());
			datosCompra.add(dtCompra);
		}
		return datosCompra;
	}
}

