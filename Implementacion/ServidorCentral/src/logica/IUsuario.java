package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import excepciones.noExisteOferta;
import logica.DAO.OfertaDAO;
import logica.DAO.PostulacionDAO;

public interface IUsuario {
	public abstract Boolean ingresarOferta(String nickname, String nombreTipo, String nombre, String descripcion, DTHorario horario, int remuneracion, Date fecha, String ciudad, String departamento, Set<String> keyword, String imagen) throws Exception;
	public abstract Set<String> listarTiposDePublicacion();
	public abstract Set<String> listarEmpresas();
	public abstract Set<String> listarPostulantes();
	public abstract Set<String> listarKeywords();
	public abstract List<String> listarUsuarios();
	public abstract DTUsuario mostrarDatosUsuario(String nickname);
	public abstract DTOfertaLaboral seleccionarOfertaLaboral(String nombre) throws noExisteOferta;
	public abstract Set<DTOfertaLaboral> listarOfertasLaborales();
	public abstract void ingresarDatosPostulacion(String postulante, String curriculum, String motivacion, String oferta, Date fecha,String video)throws Exception;
	public abstract Set<String> obtenerOfertasDeEmpresa(String empresa);
	public DTEmpresa getDataEmpresa(String nombre);
	public DTPostulante getDataPostulante(String nombre);
	public abstract void ingresarPostulante(String nickName, String nombre, String apellido,
			String correoElectronico, Date fechaNacimiento, String nacionalidad) throws Exception;
	public abstract void ingresarPostulante(String nickName, String nombre, String apellido,
			String correoElectronico, Date fechaNacimiento, String nacionalidad, String imagen, String pass) throws Exception;
	public abstract void ingresarEmpresa(String nickName, String nombre, String apellido,
			String correoElectronico, String nombreEmpresa, String descripcion, String link) throws Exception;
	public abstract void ingresarEmpresa(String nickName, String nombre, String apellido,
			String correoElectronico, String nombreEmpresa, String descripcion, String link, String imagen, String pass) throws Exception;
	public abstract void editarDatosBasicos(String nickname, String email, String nombre, String apellido, String nacionalidad, Date fecha, String descripcion, String link);
	public void editarDatosBasicos(String nickname, String email, String pass, String nombre, String apellido, String nacionalidad, Date fecha, String descripcion, String link, String imagen);
	public abstract Set<DTOfertaLaboral> listarOfertasPorKeyword(String keyword);
	public abstract DTUsuario iniciarSesion(String nickname, String pass);
	public abstract Set<String> listarOfertasIngresadas(String nickEmpresa);
	public abstract void aceptarRechazarOferta(String nomOferta, Estado cambio);
	public abstract void finalizarOferta(String nomOferta);
	public abstract Boolean existeUsuario(String nickname);
	public abstract Boolean existeEmail(String email);
	public abstract void elegirOrden(String nomPostulante,String nomOferta,int Orden);
	public abstract void follow(String seguidor, String seguir);
	public abstract void unfollow(String seguidor, String seguir);
	public abstract boolean esSeguidor(String seguidor, String seguir);
	public abstract void agregarVisita(String nomOferta);
	public abstract ArrayList<DTOfertaLaboral> obtenerMasVisitadas();
	public abstract List<OfertaDAO> ObtenerOfertasFinalizadas(String nickname)throws noExisteOferta;
	public abstract OfertaDAO ObtenerUnaOfertaFinalizada(String nombre)throws noExisteOferta;
	public abstract List<PostulacionDAO> ObtenerPostulacionesOferta(String nombreOferta)throws noExisteOferta;
	public abstract List<PostulacionDAO> ObtenerPostulacionesUsuario(String nickname)throws noExisteOferta;
	public abstract void agregarOfertaFavorita( String nickPostulante,String nombreOferta);
	public abstract PostulacionDAO ObtenerUnaPostulacionUsuario(String nickname, String oferta) throws noExisteOferta;
	public abstract ArrayList<DTEmpresa> buscarPorFiltroEmpresas(String terminosDeBusqueda);
	public abstract ArrayList<DTOfertaLaboral> buscarPorFiltroOfertasLaborales(String terminosDeBusqueda);
}
