package webservices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import excepciones.noExisteOferta;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.DTEmpresa;
import logica.DTHorario;
import logica.DTOfertaLaboral;
import logica.DTPostulante;
import logica.DTUsuario;
import logica.Estado;
import logica.Fabrica;
import logica.IUsuario;
import logica.ManejadorUsuario;
import logica.Postulacion;
import logica.Postulante;
import logica.DAO.OfertaDAO;
import logica.DAO.PostulacionDAO;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class wsUsuarios {
	
	private IUsuario ctrlUsuario;
	
	private Endpoint endpoint = null;
    //Constructor
    public wsUsuarios(){
    	ctrlUsuario = Fabrica.getInstance().getIUsuario();
    }

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar(String rutaFinal){
    	Properties properties = new Properties();
    	String url = "";
    	try {
    		properties.load(new BufferedReader(new FileReader(rutaFinal)));
    		url = properties.getProperty("url") + "wsUsuarios";
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        endpoint = Endpoint.publish(url, this);
        System.out.println(url);
        //endpoint = Endpoint.publish("http://localhost:9128/wsUsuarios", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod(exclude = true)
    public void detener() {
    	endpoint.stop();
    }
    
    @WebMethod
    public Boolean ingresarOferta(@WebParam(name = "Nickname") String nickname, String nombreTipo, String nombre, String descripcion, DTHorario horario, int remuneracion, Date fecha, String ciudad, String departamento, WrapperSetString keyword, String imagen) throws Exception{
    	return ctrlUsuario.ingresarOferta(nickname, nombreTipo, nombre, descripcion, horario, remuneracion, fecha, ciudad, departamento, keyword.getValue(), imagen);
    }
   
    @WebMethod
    public WrapperSetString listarTiposDePublicacion(){
    	WrapperSetString tiposPublicacion = new WrapperSetString();
    	tiposPublicacion.setValue(ctrlUsuario.listarTiposDePublicacion());
    	return tiposPublicacion;
    }
	
    @WebMethod
    public WrapperSetString listarEmpresas(){
    	WrapperSetString empresas = new WrapperSetString();
    	empresas.setValue(ctrlUsuario.listarEmpresas());
    	return empresas;
    }
	
    @WebMethod
    public WrapperSetString listarPostulantes(){
    	WrapperSetString postulantes = new WrapperSetString();
    	postulantes.setValue(ctrlUsuario.listarPostulantes());
    	return postulantes;
    }
	
    @WebMethod
    public WrapperSetString listarKeywords(){
    	WrapperSetString keywords = new WrapperSetString();
    	keywords.setValue(ctrlUsuario.listarKeywords());
    	return keywords;
    }
	
    @WebMethod
    public WrapperListString listarUsuarios(){
    	WrapperListString usuarios = new WrapperListString();
    	usuarios.setValue(ctrlUsuario.listarUsuarios());
    	return usuarios;
    }
	
    @WebMethod
    public Boolean existeUsuario(String nickname) {
    	return ctrlUsuario.existeUsuario(nickname);
    }
    
    @WebMethod
    public DTUsuario mostrarDatosUsuario(String nickname) {
    	return ctrlUsuario.mostrarDatosUsuario(nickname);
    }
	
    @WebMethod
    public DTOfertaLaboral seleccionarOfertaLaboral(String nombre) throws noExisteOferta{
    	return ctrlUsuario.seleccionarOfertaLaboral(nombre);
    }
	
    @WebMethod
    public DataOfertas listarOfertasLaborales(){
    	DataOfertas ofertas = new DataOfertas();
    	ofertas.setValue(ctrlUsuario.listarOfertasLaborales());
    	return ofertas;
    }
	
    @WebMethod
    public void ingresarDatosPostulacion(String postulante, String curriculum, String motivacion, String oferta, Date fecha,String video)throws Exception{
    	ctrlUsuario.ingresarDatosPostulacion(postulante, curriculum, motivacion, oferta, fecha,video);
    }
    
    @WebMethod
	public WrapperSetString obtenerOfertasDeEmpresa(String empresa){
    	WrapperSetString ofertasEmp = new WrapperSetString();
    	ofertasEmp.setValue(ctrlUsuario.obtenerOfertasDeEmpresa(empresa));
    	return ofertasEmp;
    }
    
    @WebMethod
	public DTEmpresa getDataEmpresa(String nombre) {
    	return ctrlUsuario.getDataEmpresa(nombre);
    }
    
    @WebMethod
	public DTPostulante getDataPostulante(String nombre) {
    	return ctrlUsuario.getDataPostulante(nombre);
    }
    
    
    @WebMethod
	public void ingresarPostulante(String nickName, String nombre, String apellido, String correoElectronico, Date fechaNacimiento, String nacionalidad, String imagen, String pass) throws Exception{
    	ctrlUsuario.ingresarPostulante(nickName, nombre, apellido, correoElectronico, fechaNacimiento, nacionalidad, imagen, pass);
    }
    
    @WebMethod
	public void ingresarEmpresa(String nickName, String nombre, String apellido, String correoElectronico, String nombreEmpresa, String descripcion, String link, String imagen, String pass) throws Exception{
    	ctrlUsuario.ingresarEmpresa(nickName, nombre, apellido, correoElectronico, nombreEmpresa, descripcion, link, imagen, pass);
    }
        
    @WebMethod
	public void editarDatosBasicos(String nickname, String email, String pass, String nombre, String apellido, String nacionalidad, Date fecha, String descripcion, String link, String imagen) {
    	ctrlUsuario.editarDatosBasicos(nickname, email, pass, nombre, apellido, nacionalidad, fecha, descripcion, link, imagen);
    }
    
    @WebMethod
	public DataOfertas listarOfertasPorKeyword(String keyword){
    	DataOfertas ofertas = new DataOfertas();
    	ofertas.setValue(ctrlUsuario.listarOfertasPorKeyword(keyword));
    	return ofertas;
    }
    
    @WebMethod
	public DTUsuario iniciarSesion(String nickname, String pass) throws Exception{
    	DTUsuario user = ctrlUsuario.iniciarSesion(nickname, pass);
    	if(user != null) {
    		return user;
    	}else {
    		throw new Exception();
    	}
    }
    
    @WebMethod
    public Boolean existeEmail(String email) {
    	return ctrlUsuario.existeEmail(email);
    }
    
    @WebMethod
	public WrapperSetString listarOfertasIngresadas(String nickEmpresa){
    	WrapperSetString ofertasEmp = new WrapperSetString();
    	ofertasEmp.setValue(ctrlUsuario.listarOfertasIngresadas(nickEmpresa));
    	return ofertasEmp;
    }
    
    @WebMethod
	public void finalizarOferta(String nomOferta) {
    	ctrlUsuario.finalizarOferta(nomOferta);
    }
    
    @WebMethod
    public byte[] getFile(@WebParam(name = "fileName") String name, String tipo)
                    throws  IOException {
        byte[] byteArray = null;
        String separador = System.getProperty("file.separator");
        try {
                File f = new File(System.getProperty("user.home") + separador + "TrabajoUY_Utilidades" + separador + "media" + separador + tipo + separador + name);
                FileInputStream streamer = new FileInputStream(f);
                byteArray = new byte[streamer.available()];
                streamer.read(byteArray);
        } catch (IOException e) {
                throw e;
        }
        return byteArray;
    }
    
    @WebMethod
    public String writeImg(String name, String tipo, String extension, byte[] data) {
    	String separador = System.getProperty("file.separator");
    	String nombreImagen = name.replaceAll("[^a-zA-Z0-9]", "");
    	boolean existe = true;
    	int id = 0;
		while (existe) {
			File file = new File(System.getProperty("user.home") + separador + "TrabajoUY_Utilidades" + separador + "media" + separador + tipo + separador + nombreImagen + id + "." + extension);
			existe = file.exists();
			if (existe) id++;	
		}
		nombreImagen = nombreImagen + id + "." + extension;
		Path path = Paths.get(System.getProperty("user.home") + separador + "TrabajoUY_Utilidades" + separador + "media" + separador + tipo + separador + nombreImagen);
		try {
			Files.write(path, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
    	return nombreImagen;
    }
    @WebMethod
    public void elegirOrden(String nomPostulante,String nomOferta,int Orden) {
		ctrlUsuario.elegirOrden(nomPostulante, nomOferta, Orden);
		
    }
    @WebMethod
    public void follow(String seguidor, String seguir) {
    	ctrlUsuario.follow(seguidor, seguir);
    }
    @WebMethod
    public void unfollow(String seguidor, String seguir) {
    	ctrlUsuario.unfollow(seguidor, seguir);
    }
    @WebMethod
    public boolean esSeguidor(String seguidor, String seguir) {
    	return ctrlUsuario.esSeguidor(seguidor, seguir);
    }
    @WebMethod
    public void agregarVisita(String nomOferta) {
    	ctrlUsuario.agregarVisita(nomOferta);
    }
    @WebMethod
    public DataOfertaDAO ObtenerOfertasFinalizadas(String nickname)throws noExisteOferta {
    	DataOfertaDAO oferta = new DataOfertaDAO();
    	oferta.setValue(ctrlUsuario.ObtenerOfertasFinalizadas(nickname));
    	return oferta;
    }
    @WebMethod
    public OfertaDAO ObtenerUnaOfertaFinalizada(String nombre) throws noExisteOferta{
    	return ctrlUsuario.ObtenerUnaOfertaFinalizada(nombre);
    }
    @WebMethod
    public DataPostulacionDAO ObtenerPostulacionesOferta(String nombreOferta)throws noExisteOferta {
    	DataPostulacionDAO post = new DataPostulacionDAO();
    	post.setValue(ctrlUsuario.ObtenerPostulacionesOferta(nombreOferta));
    	return post;
    }
    @WebMethod
    public DataPostulacionDAO ObtenerPostulacionesUsuario(String nickname)throws noExisteOferta {
    	DataPostulacionDAO post = new DataPostulacionDAO();
    	post.setValue(ctrlUsuario.ObtenerPostulacionesUsuario(nickname));
    	return post;
    }
    @WebMethod
    public void agregarOfertaFavorita( String nickPostulante,String nombreOferta) {
    	ctrlUsuario.agregarOfertaFavorita( nickPostulante, nombreOferta);
    }
    
    @WebMethod
    public PostulacionDAO ObtenerUnaPostulacionUsuario(String nickname, String oferta) throws noExisteOferta{
    	return ctrlUsuario.ObtenerUnaPostulacionUsuario(nickname, oferta);
    }
    
    @WebMethod
	public DataEmpresas buscarPorFiltroEmpresas(String terminosDeBusqueda) {
    	DataEmpresas empresas = new DataEmpresas();
    	empresas.setValue(ctrlUsuario.buscarPorFiltroEmpresas(terminosDeBusqueda));
    	return empresas;
    }
    
    @WebMethod
	public DataOfertas buscarPorFiltroOfertasLaborales(String terminosDeBusqueda) {
    	DataOfertas ofertas = new DataOfertas();
    	ArrayList<DTOfertaLaboral> datosOfertasFiltradas = ctrlUsuario.buscarPorFiltroOfertasLaborales(terminosDeBusqueda);
    	ofertas.setValue(new HashSet<DTOfertaLaboral>(datosOfertasFiltradas));
    	return ofertas;
    }
}
