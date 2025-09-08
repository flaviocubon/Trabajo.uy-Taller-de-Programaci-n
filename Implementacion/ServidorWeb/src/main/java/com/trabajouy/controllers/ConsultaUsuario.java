package com.trabajouy.controllers;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.tomcat.jakartaee.commons.compress.utils.FileNameUtils;

import java.util.Set;
import java.util.Date;
import java.util.GregorianCalendar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import webservices.DtCompra;
import webservices.DtEmpresa;
import webservices.DtOfertaLaboral;
import webservices.DtPostulacion;
import webservices.DtPostulante;
import webservices.DtUsuario;
import webservices.Exception_Exception;
import webservices.NoExisteOferta_Exception;
import webservices.OfertaDAO;
import webservices.PostulacionDAO;
import webservices.WsTipos;
import webservices.WsTiposService;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;

/**
 * Servlet implementation class ConsultaUsuario
 */
@WebServlet({"/ConsultaUsuario","/PerfilUsuario","/Seguir"})
@MultipartConfig
public class ConsultaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ConsultaUsuario() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios port = wsUsuarios.getWsUsuariosPort();
		String servletPath = request.getServletPath();
		if(servletPath.equals("/ConsultaUsuario")) {
			ArrayList<String> nicknamesUsuarios = (ArrayList<String>) port.listarUsuarios().getValue();
			ArrayList<DtUsuario> usuarios = new ArrayList<DtUsuario>();
			for(String nombre : nicknamesUsuarios){
				usuarios.add(port.mostrarDatosUsuario(nombre));
			}
			Collections.sort(usuarios, new Comparator<DtUsuario>() {
				@Override
				public int compare(DtUsuario p1, DtUsuario p2) {
					return p1.getNickname().compareToIgnoreCase(p2.getNickname());
				}
			});
			request.setAttribute("usuarios", usuarios);
			request.setAttribute("listaKeywords", new HashSet<String>(port.listarKeywords().getValue()));
			request.getRequestDispatcher("/WEB-INF/consultaUsuario/consultaUsuario.jsp").
			forward(request, response);
		}
		else if(servletPath.equals("/PerfilUsuario")){
			try {
				cargarPerfilUsuario(request, response, "");
			}catch (NoExisteOferta_Exception ex){
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
			}catch (Exception e) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
			}
		}
		else if(servletPath.equals("/Seguir")) {	
			if (request.getSession().getAttribute("usuarioLogueado") == null){
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
				return;
			}
			String seguir = (String) URLDecoder.decode(request.getParameter("seguir"), StandardCharsets.UTF_8.toString());
			String seguidor = ((DtUsuario)request.getSession().getAttribute("usuarioLogueado")).getNickname();
			if (seguir == null || !port.existeUsuario(seguir) || !port.existeUsuario(seguidor) || seguir.equals(seguidor)) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
				return;
			}
			if (port.esSeguidor(seguidor, seguir)) {
				port.unfollow(seguidor, seguir);
			} else {
				port.follow(seguidor, seguir);
			}
			request.getSession().setAttribute("usuarioLogueado", port.mostrarDatosUsuario(seguidor));
			if (request.getParameter("origen") != null) {
				response.sendRedirect("PerfilUsuario?nickname=" + seguir);
			} else {
				response.sendRedirect("ConsultaUsuario");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios port = wsUsuarios.getWsUsuariosPort();
		DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuarioLogueado");
		Set<String> listaKeywords= new HashSet<String>(port.listarKeywords().getValue());
		request.setAttribute("listaKeywords", listaKeywords);
		if(user != null) { //Si es null significa que no inició sesión, evitamos que avance.
			String nickname = user.getNickname();
			Part imagenPart = request.getPart("imagen");
			String extension = FileNameUtils.getExtension(imagenPart.getSubmittedFileName());
			if (imagenPart.getSize() > 0 && (!extension.equals("png") && !extension.equals("jpeg") && !extension.equals("jpg"))) {
				String mensaje = "Debe ingresar una imagen en formato png, jpeg o jpg";
				try {
					cargarPerfilUsuario(request, response, mensaje);
				}catch(NoExisteOferta_Exception ex) {
					request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
				}catch(Exception e) {
					request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
				}
				return;
			}
			String nombreImagen = "";
			if (imagenPart.getSize() > 0) nombreImagen = port.writeImg(nickname, "usuarios", extension, imagenPart.getInputStream().readAllBytes());
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String pass = (request.getParameter("pass") == null)?"":request.getParameter("pass");
			Date fechaNac = null;
			XMLGregorianCalendar xmlDate = null;
			String nacionalidad="";
			String descripcion="";
			String link="";
			if(user instanceof DtPostulante) {
				fechaNac = new Date(request.getParameter("fecha").replace("-", "/"));
				nacionalidad = request.getParameter("nacionalidad");
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(fechaNac);
				try {
					xmlDate = javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				try {
					 xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()); //no se puede mandar null la fecha sino falla
				}catch(DatatypeConfigurationException ex) {
					ex.printStackTrace();
				}
				descripcion = request.getParameter("descripcion");
				link = request.getParameter("link");
			}
			port.editarDatosBasicos(nickname, user.getMail(), pass, nombre, apellido, nacionalidad, xmlDate, descripcion, link, nombreImagen);
			request.getSession().setAttribute("usuarioLogueado", port.mostrarDatosUsuario(nickname));
			String nickEncode = URLEncoder.encode(nickname, StandardCharsets.UTF_8.toString());
			response.sendRedirect("PerfilUsuario?nickname=" + nickEncode);
		}
	}
	protected void cargarPerfilUsuario(HttpServletRequest request, HttpServletResponse response, String mensaje) throws ServletException, IOException, NoExisteOferta_Exception {
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		WsTiposService wsTipos = new WsTiposService();
		WsTipos portTipos = wsTipos.getWsTiposPort();
		if(request.getParameter("nickname") == null) {
			request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
			return;
		}
		String nickname = URLDecoder.decode(request.getParameter("nickname"),StandardCharsets.UTF_8.toString());
		if(!portUsuarios.existeUsuario(nickname)) {
			request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
			return;
		}
		DtUsuario user = portUsuarios.mostrarDatosUsuario(nickname);
		List<String> seguidos = user.getSeguidos();
		List<String> seguidores = user.getSeguidores();
		List<DtUsuario> dataSeguidos = new ArrayList<DtUsuario>();
		List<DtUsuario> dataSeguidores = new ArrayList<DtUsuario>();
		for(String seguido : seguidos) {
			dataSeguidos.add(portUsuarios.mostrarDatosUsuario(seguido));
		}
		for(String seguidor : seguidores) {
			dataSeguidores.add(portUsuarios.mostrarDatosUsuario(seguidor));
		}
		if(user instanceof DtPostulante) {
			List<DtPostulante.Postulaciones.Entry> postulaciones = ((DtPostulante)user).getPostulaciones().getEntry();
			Set<DtPostulacion> setPostulacion = new HashSet<DtPostulacion>();
			for(DtPostulante.Postulaciones.Entry elemento : postulaciones) {
				setPostulacion.add((DtPostulacion) elemento.getValue());
			}
			List<PostulacionDAO> postFinalizadas = new ArrayList<PostulacionDAO>();
			postFinalizadas	= portUsuarios.obtenerPostulacionesUsuario(nickname).getValue();
			request.setAttribute("postFinalizadas", postFinalizadas);
			request.setAttribute("postulaciones", setPostulacion);
		}else {
			Set<DtCompra> compras = new HashSet<DtCompra>(portTipos.obtenerDatosCompra(nickname).getValue());
			Set<String> nombreOfertas = new HashSet<String>(((DtEmpresa)user).getNombreOfertas());
			Set<DtOfertaLaboral> dtOfertas = new HashSet<DtOfertaLaboral>();
			try {
				for(String ofertas : nombreOfertas) {
					dtOfertas.add(portUsuarios.seleccionarOfertaLaboral(ofertas));
				}
			} catch (Exception ex) {
					ex.printStackTrace();
			}
			List<OfertaDAO> ofertasFinalizadas = portUsuarios.obtenerOfertasFinalizadas(nickname).getValue();
			request.setAttribute("ofertasFinalizadas", ofertasFinalizadas);
			request.setAttribute("ofertas", dtOfertas);
			request.setAttribute("compras", compras);
		}
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("detalleUsuario", user);
		request.setAttribute("seguidores", dataSeguidores);
		request.setAttribute("seguidos", dataSeguidos);
		request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
		request.getRequestDispatcher("/WEB-INF/consultaUsuario/perfilUsuario.jsp").forward(request, response);
	}
}
