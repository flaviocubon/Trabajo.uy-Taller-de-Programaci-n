package logica;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import excepciones.compraPaqueteRepetida;
import excepciones.nombrePaqueteRepetido;
import excepciones.nombreTipoPublicacionRepetido;
import excepciones.paqueteYaComprado;


public interface ITipos {

	 public abstract void ingresarDatosTipoPublicacion(String nombreT, String descripcionT, int exposicionT, Date fechaT, double costoT, int duracionT)
			throws nombreTipoPublicacionRepetido;
	
	public abstract Map<String, DTTipoPublicacion> obtenerDataTipos();
	
	public abstract void borrarTipos();
	public abstract Boolean ingresarKeyword(String keyword);
	public abstract Set<String> listarPaquetes();
	public abstract Set<String> listarTiposPublicacion();
	public void agregarTipoPublicacion(String nombrePaquete, String nombreTipo, int cant) throws paqueteYaComprado;
	public abstract void borrarPaquetes();
	public abstract void ingresarDatosPaquete(String nombre, String descripcion, int validez, double descuento, Date fechaAlta, String imagen) throws nombrePaqueteRepetido;
	public abstract Map<String, DTPaquete> obtenerDataPaquetes();
	public abstract Boolean empresaPoseePaquete(String nombreEmpresa, String nombrePaquete);
	public abstract void comprarPaquete(String nombreEmpresa, String nombrePaquete, LocalDate fecha) throws compraPaqueteRepetida;
	public abstract void comprarPaquete(String nombreEmpresa, String nombrePaquete) throws compraPaqueteRepetida;
	public abstract Set<String> listarPaquetesDisponiblesParaUsuarioYTipo(String nickname, String nombreTipo);
	public abstract DTPaquete obtenerDatosPaquete(String nombrePaquete);
	public abstract void canjearOfertaDePaquete(String nombreEmpresa, String nombreTipo, String nombrePaquete);
	public abstract Set<DTCompra> obtenerDatosCompra(String nombreEmpresa);
}
