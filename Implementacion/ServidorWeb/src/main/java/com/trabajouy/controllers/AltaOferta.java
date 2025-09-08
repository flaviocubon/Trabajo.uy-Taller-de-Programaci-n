package com.trabajouy.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.tomcat.jakartaee.commons.compress.utils.FileNameUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import webservices.DtEmpresa;
import webservices.DtHorario;
import webservices.DtUsuario;
import webservices.ObjectFactory;
import webservices.WrapperSetString;
import webservices.WsTipos;
import webservices.WsTiposService;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;

/**
 * Servlet implementation class AltaOferta
 */
@WebServlet("/AltaOferta")
@MultipartConfig
public class AltaOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AltaOferta() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DtUsuario usr = (DtUsuario) session.getAttribute("usuarioLogueado");
		
		
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		WsTiposService wsTipos = new WsTiposService();
		WsTipos portTipos = wsTipos.getWsTiposPort();
		List<String> listaTipos = portUsuarios.listarTiposDePublicacion().getValue();
		Set<String> listaKeywords= new HashSet(portUsuarios.listarKeywords().getValue());

		request.setAttribute("listaKeywords", listaKeywords);
		request.setAttribute("listaTipos", listaTipos);
		
		if (usr!=null && usr.getClass()== DtEmpresa.class) {
			String tipoPreseleccionado= listaTipos.get(0);
			Set<String> listaPaquetes= new HashSet(portTipos.listarPaquetesDisponiblesParaUsuarioYTipo(usr.getNickname(), tipoPreseleccionado).getValue());
			request.setAttribute("listaPaquetes", listaPaquetes);
			request.getRequestDispatcher("/WEB-INF/altaOferta/altaOferta.jsp").
			forward(request, response);
		}
		else {
			request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
			forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		WsTiposService wsTipos = new WsTiposService();
		WsTipos portTipos = wsTipos.getWsTiposPort();
		HttpSession session = request.getSession();
		DtUsuario usr = (DtUsuario) session.getAttribute("usuarioLogueado");
		
		Set<String> listaKeywords= new HashSet(portUsuarios.listarKeywords().getValue());
		request.setAttribute("listaKeywords", listaKeywords);
		
		List<String> listaTipos = portUsuarios.listarTiposDePublicacion().getValue();
		
		String tipoPreseleccionado= listaTipos.get(0);
		Set<String> listaPaquetes= new HashSet(portTipos.listarPaquetesDisponiblesParaUsuarioYTipo(usr.getNickname(), tipoPreseleccionado).getValue());
		
		request.setAttribute("listaPaquetes", listaPaquetes);
		request.setAttribute("listaTipos", listaTipos);
		
		Object mandarPaquetes= request.getParameter("getPaquete");
		session.removeAttribute("getPaquete");
		
		if (usr!=null && usr.getClass()== DtEmpresa.class) {
			String nombreTipo= request.getParameter("tipoOferta");
			if(mandarPaquetes==null) {
				String nombre= request.getParameter("nombre");
				String descripcion= request.getParameter("descripcion");
				int remuneracion= Integer.parseInt( request.getParameter("remuneracion"));
				String departamento= request.getParameter("departamento");
				String ciudad= request.getParameter("ciudad");
				String horarioInicio= request.getParameter("horarioInicio");
				String horarioFin= request.getParameter("horarioFin");
				ObjectFactory fab = new ObjectFactory();
				DtHorario horario= fab.createDtHorario();
				horario.setHorarioInicio(horarioInicio);
				horario.setHorarioFin(horarioFin);
				
				
				//Imagen
				Part imagen = request.getPart("imagen");
				String extension = FileNameUtils.getExtension(imagen.getSubmittedFileName());
				if (imagen.getSize() > 0 && (!extension.equals("png") && !extension.equals("jpeg") && !extension.equals("jpg"))) {
					request.setAttribute("exito", false);
					request.setAttribute("mensaje", "Debe ingresar una imagen en formato png, jpeg o jpg");
					request.getRequestDispatcher("/WEB-INF/altaOferta/altaOferta.jsp").forward(request, response);
					return;
				}
				String nombreImagen = "default.png";
				//Fin procesamiento imagen
				List<String> keywordsList= request.getParameterValues("keywords")!=null? Arrays.asList(request.getParameterValues("keywords")): new ArrayList<String>();
				WrapperSetString keywords = fab.createWrapperSetString();
				keywords.setValue(keywordsList);
				
				
				String paquete =request.getParameter("paquete");
				
				Date fechaLocal = Date.from(Instant.now());
				String nickname= usr.getNickname();
						
				boolean result=false;
				String mensaje;
				try {
					if (imagen.getSize() > 0) nombreImagen = portUsuarios.writeImg(nickname, "ofertas", extension, imagen.getInputStream().readAllBytes());
					Date fechaIn = new Date();
					XMLGregorianCalendar xmlDate = null;
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTime(fechaIn);
					try {
			            xmlDate = javax.xml.datatype.DatatypeFactory.newInstance()
			                          .newXMLGregorianCalendar(gc);
			        }
			        catch (Exception e) {
			            e.printStackTrace();
			        }
					result = portUsuarios.ingresarOferta(nickname,nombreTipo,nombre, descripcion, horario,remuneracion, xmlDate,ciudad, departamento,keywords, nombreImagen);
					if(paquete!=null && !paquete.equals("sinPaquete")) {
						portTipos.canjearOfertaDePaquete(nickname, nombre, paquete);
					}
					mensaje="La oferta fue ingresada correctamente.";
					response.sendRedirect("AltaOferta?mensaje="+mensaje);
					
				} catch (Exception e) {
					request.setAttribute("exito", false);
					request.setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/WEB-INF/altaOferta/altaOferta.jsp").
					forward(request, response);
					
				
				}
			}
			else {
				String horarioInicio= (String) request.getParameter("horarioInicio");				
				listaTipos.remove(nombreTipo);
				listaTipos.add(0, nombreTipo);
				request.setAttribute("listaTipos", listaTipos);	
				Set<String> listaPaquetesTipo= new HashSet(portTipos.listarPaquetesDisponiblesParaUsuarioYTipo(usr.getNickname(), nombreTipo).getValue());
				request.setAttribute("listaPaquetes", listaPaquetesTipo);
				request.getRequestDispatcher("/WEB-INF/altaOferta/altaOferta.jsp").
				forward(request, response);
			}
		}
		else {
			request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
			forward(request, response);
		}

	}
}
