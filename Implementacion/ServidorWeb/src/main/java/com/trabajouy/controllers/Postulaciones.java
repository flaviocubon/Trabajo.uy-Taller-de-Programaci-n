package com.trabajouy.controllers;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.DtEmpresa;
import webservices.DtOfertaLaboral;
import webservices.DtPostulacion;
import webservices.DtPostulante;
import webservices.DtUsuario;
import webservices.Estado;
import webservices.NoExisteOferta_Exception;
import webservices.PostulacionDAO;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;

/**
 * Servlet implementation class Postulaciones
 */
@WebServlet({"/Postularse","/Postulacion","/PostulacionFinalizada","/PostularseMobile", "/PostulacionMobile"}) 
public class Postulaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Postulaciones() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		String servletPath = request.getServletPath();
		if("/Postularse".equals(servletPath)) {
			DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuarioLogueado");
			if (user == null) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
				return;
			}	
			String oferta = URLDecoder.decode(request.getParameter("n"), "UTF-8");
			request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
			try {
				DtOfertaLaboral dataOferta = portUsuarios.seleccionarOfertaLaboral(oferta);
				Set<DtPostulacion> postulaciones = new HashSet<DtPostulacion>(dataOferta.getPostulaciones());
				DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				Date fecha = formatter.parse(dataOferta.getFechaVencimiento().replace("-", "/"));
				boolean estaVencida = fecha.before(new Date());
				if (dataOferta.getEstado() != Estado.CONFIRMADO || estaVencida) {
					request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
					return;
				}
				boolean postulado = false;
				for (DtPostulacion x : postulaciones) {
					if (x.getPostulante() == user.getNickname()) {
						request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
						return;
					}
				}
				request.setAttribute("oferta", dataOferta);
				request.getRequestDispatcher("/WEB-INF/postulacionOferta/postulacionOferta.jsp").forward(request, response);
			} catch (Exception e) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
				return;
			}
		}
		else if("/Postulacion".equals(servletPath)) {
			DtUsuario dataUsuario =(DtUsuario)request.getSession().getAttribute("usuarioLogueado");
			try {
			String nickUsuario = dataUsuario.getNickname();
			String oferta = URLDecoder.decode(request.getParameter("n"),"UTF-8");
			String postulante = URLDecoder.decode(request.getParameter("p"),"UTF-8");
			request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
			try {
				DtOfertaLaboral dataOferta = portUsuarios.seleccionarOfertaLaboral(oferta);
				DtPostulacion dataPostulacion = null;
				DtPostulante dataPostulante = portUsuarios.getDataPostulante(postulante);
				for(DtPostulacion post : dataOferta.getPostulaciones()) {
					if(post.getPostulante().equals(dataPostulante.getNickname())) {
						dataPostulacion = post;
						break;
					}
				}
				
				if(dataUsuario instanceof DtEmpresa && dataOferta.getNombreEmpresa().equals(nickUsuario)&& dataPostulacion != null) {
					request.setAttribute("Postulacion", dataPostulacion);
					request.setAttribute("Postulante", dataPostulante);
					request.setAttribute("imagenOferta", dataOferta.getImagen());
					request.getRequestDispatcher("/WEB-INF/consultaPostulacion/consultaPostulacion.jsp").forward(request, response);
					return;
				}
				
				else if(dataUsuario instanceof DtPostulante && dataPostulacion !=null && dataPostulante.getNickname().equals(nickUsuario)) {
					request.setAttribute("Postulacion", dataPostulacion);
					request.setAttribute("Postulante", dataPostulante);
					request.setAttribute("imagenOferta", dataOferta.getImagen());
					request.getRequestDispatcher("/WEB-INF/consultaPostulacion/consultaPostulacion.jsp").forward(request, response);
					return;
				}
				else {
					request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
					return;
				}
				
				
			} 
			catch (Exception e) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
				return;
			}
			}
			catch(Exception e) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
				return;
			}
		}
		else if(servletPath.equals("/PostulacionFinalizada")) {
			DtUsuario user =(DtUsuario)request.getSession().getAttribute("usuarioLogueado");
			if(user == null) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
				return;
			}
			try {
				request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
				String nomOferta = URLDecoder.decode(request.getParameter("oferta"), "UTF-8");
				String nickname = URLDecoder.decode(request.getParameter("p"), "UTF-8");
				PostulacionDAO postulacion = portUsuarios.obtenerUnaPostulacionUsuario(nickname, nomOferta);
				request.setAttribute("postulacion", postulacion);
				request.getRequestDispatcher("/WEB-INF/consultaPostulacion/detallePostulacionFinalizada.jsp").forward(request, response);
			}catch(NoExisteOferta_Exception ex) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
			}catch(Exception e) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
			}
		}
		else if("/PostularseMobile".equals(servletPath)) {
			DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuarioLogueadoMobile");
			if (user == null) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404Mobile.jsp").include(request, response);
				return;
			}	
			String oferta = URLDecoder.decode(request.getParameter("n"), "UTF-8");
			request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
			try {
				DtOfertaLaboral dataOferta = portUsuarios.seleccionarOfertaLaboral(oferta);
				Set<DtPostulacion> postulaciones = new HashSet<DtPostulacion>(dataOferta.getPostulaciones());
				DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				Date fecha = formatter.parse(dataOferta.getFechaVencimiento().replace("-", "/"));
				boolean estaVencida = fecha.before(new Date());
				if (dataOferta.getEstado() != Estado.CONFIRMADO || estaVencida) {
					request.getRequestDispatcher("/WEB-INF/errorPages/404Mobile.jsp").include(request, response);
					return;
				}
				boolean postulado = false;
				for (DtPostulacion x : postulaciones) {		
					if (x.getPostulante().equals(user.getNickname())) {
						request.getRequestDispatcher("/WEB-INF/errorPages/404Mobile.jsp").include(request, response);
						return;
					}
				}
				request.setAttribute("oferta", dataOferta);
				request.getRequestDispatcher("/WEB-INF/postulacionOferta/postulacionOfertaMobile.jsp").forward(request, response);
			} catch (Exception e) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404Mobile.jsp").include(request, response);
				return;
			}
		}
		else if("/PostulacionMobile".equals(servletPath)) {
			DtUsuario dataUsuario =(DtUsuario)request.getSession().getAttribute("usuarioLogueadoMobile");
			try {
			String nickUsuario = dataUsuario.getNickname();
			String oferta = URLDecoder.decode(request.getParameter("n"),"UTF-8");
			String postulante = URLDecoder.decode(request.getParameter("p"),"UTF-8");
			request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
			try {
				DtOfertaLaboral dataOferta = portUsuarios.seleccionarOfertaLaboral(oferta);
				DtPostulacion dataPostulacion = null;
				DtPostulante dataPostulante = portUsuarios.getDataPostulante(postulante);
				for(DtPostulacion post : dataOferta.getPostulaciones()) {
					if(post.getPostulante().equals(dataPostulante.getNickname())) {
						dataPostulacion = post;
						break;
					}
				}
				
				
				if(dataUsuario instanceof DtEmpresa && dataOferta.getNombreEmpresa()==nickUsuario&& dataPostulacion != null) {
					request.setAttribute("Postulacion", dataPostulacion);
					request.setAttribute("Postulante", dataPostulante);
					request.setAttribute("imagenOferta", dataOferta.getImagen());
					request.getRequestDispatcher("/WEB-INF/consultaPostulacion/consultaPostulacionMobile.jsp").forward(request, response);
					return;
				}
				
				else if(dataUsuario instanceof DtPostulante && dataPostulacion !=null && dataPostulante.getNickname().equals(nickUsuario)) {
					request.setAttribute("Postulacion", dataPostulacion);
					request.setAttribute("Postulante", dataPostulante);
					request.setAttribute("imagenOferta", dataOferta.getImagen());
					request.getRequestDispatcher("/WEB-INF/consultaPostulacion/consultaPostulacionMobile.jsp").forward(request, response);
					return;
				}
				else {
					request.getRequestDispatcher("/WEB-INF/errorPages/404Mobile.jsp").include(request, response);
					return;
				}
				
				
			} 
			catch (Exception e) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404Mobile.jsp").include(request, response);
				return;
			}
			}
			catch(Exception e) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404Mobile.jsp").include(request, response);
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletPath = request.getServletPath();
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		if ("/Postularse".equals(servletPath)) {
			DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuarioLogueado");
			DtOfertaLaboral dataOferta;
			try {
				dataOferta = portUsuarios.seleccionarOfertaLaboral(request.getParameter("n"));
			} catch (Exception e) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
				return;
			}
			String cv = (String) request.getParameter("cv");
			String motivacion = (String) request.getParameter("motivacion");
			try {
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(new Date());
				XMLGregorianCalendar xCal = DatatypeFactory.newInstance()
				    .newXMLGregorianCalendar(cal);
				String video = request.getParameter("video");
				portUsuarios.ingresarDatosPostulacion(user.getNickname(), cv, motivacion, dataOferta.getNombre(), xCal,video);
				request.getSession().setAttribute("usuarioLogueado", portUsuarios.getDataPostulante(user.getNickname()));
				response.sendRedirect("Postulacion?n=" + URLEncoder.encode(dataOferta.getNombre(), StandardCharsets.UTF_8.toString()) + "&p=" + URLEncoder.encode(user.getNickname(), StandardCharsets.UTF_8.toString()));
			} catch (Exception e) {
				request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
				request.getRequestDispatcher("/WEB-INF/postulacionOferta/postulacionOferta.jsp").forward(request, response);
				return;
			}
		}
		if("/PostularseMobile".equals(servletPath)) {
			DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuarioLogueadoMobile");
			DtOfertaLaboral dataOferta;
			try {
				dataOferta = portUsuarios.seleccionarOfertaLaboral(request.getParameter("n"));
			} catch (Exception e) {
				request.getRequestDispatcher("/WEB-INF/errorPages/404Mobile.jsp").include(request, response);
				return;
			}
			String cv = (String) request.getParameter("cv");
			String motivacion = (String) request.getParameter("motivacion");
			try {
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(new Date());
				XMLGregorianCalendar xCal = DatatypeFactory.newInstance()
				    .newXMLGregorianCalendar(cal);
				String video = request.getParameter("video");
				portUsuarios.ingresarDatosPostulacion(user.getNickname(), cv, motivacion, dataOferta.getNombre(), xCal,video);
				request.getSession().setAttribute("usuarioLogueadoMobile", portUsuarios.getDataPostulante(user.getNickname()));
				response.sendRedirect("PostulacionMobile?n=" + URLEncoder.encode(dataOferta.getNombre(), StandardCharsets.UTF_8.toString()) + "&p=" + URLEncoder.encode(user.getNickname(), StandardCharsets.UTF_8.toString()));
			} catch (Exception e) {
				request.setAttribute("listaKeywords", new HashSet(portUsuarios.listarKeywords().getValue()));
				request.getRequestDispatcher("/WEB-INF/postulacionOferta/postulacionOfertaMobile.jsp").forward(request, response);
				return;
			}
		}
	}
}
