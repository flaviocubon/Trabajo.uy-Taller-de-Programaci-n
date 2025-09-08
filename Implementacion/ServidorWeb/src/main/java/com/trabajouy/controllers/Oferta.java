package com.trabajouy.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.DtEmpresa;
import webservices.DtOfertaLaboral;
import webservices.DtPaquete;
import webservices.DtPostulacion;
import webservices.DtUsuario;
import webservices.Estado;
import webservices.Exception_Exception;
import webservices.NoExisteOferta_Exception;
import webservices.OfertaDAO;
import webservices.PostulacionDAO;
import webservices.WsTipos;
import webservices.WsTiposService;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Servlet implementation class Oferta
 */
@WebServlet({"/Oferta","/OfertaMobile","/Favorito"})
public class Oferta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Oferta() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletPath = request.getServletPath();
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		WsTiposService wsTipos = new WsTiposService();
		WsTipos portTipos = wsTipos.getWsTiposPort();
		if(servletPath.equals("/Oferta")) {
			String nombreOferta=request.getParameter("n");
			String keyword= request.getParameter("k");
			Set<String> listaKeywords= new HashSet<String>(portUsuarios.listarKeywords().getValue());
			request.setAttribute("listaKeywords", listaKeywords);
			if(nombreOferta!=null) {
				nombreOferta= URLDecoder.decode(nombreOferta,StandardCharsets.UTF_8.toString());
				//si no existe la oferta segun su nombre tirar 404
				try {
					DtOfertaLaboral oferta;
					try {
						oferta = portUsuarios.seleccionarOfertaLaboral(nombreOferta);
					}catch(NoExisteOferta_Exception ex) {
						oferta = null;
					}
					
					if(oferta != null) {
						String imagenPaquete=null;
						if(oferta.getPaquete()!=null && !oferta.getPaquete().equals("")) {
						DtPaquete dataPaquete= portTipos.obtenerDatosPaquete(oferta.getPaquete());
						imagenPaquete= dataPaquete.getImagen(); 
						}
						Map<String,String> imagenesPostulantes=new HashMap<String,String>();
						Set<DtPostulacion> postulaciones= new HashSet(oferta.getPostulaciones());
						for(DtPostulacion post: postulaciones) {
							DtUsuario datosUsuario=portUsuarios.mostrarDatosUsuario(post.getPostulante());
							String imagen= datosUsuario.getImagen()!=null && !datosUsuario.getImagen().equals("") ? datosUsuario.getImagen(): null;
							imagenesPostulantes.put(post.getPostulante(), imagen);
						}
						request.setAttribute("imagenesPostulantes", imagenesPostulantes);
						request.setAttribute("imagenPaquete", imagenPaquete);
						request.setAttribute("oferta", oferta);
						request.getRequestDispatcher("/WEB-INF/consultaOferta/consultaOfertaDetalles.jsp").
						forward(request, response);
					}else {
						OfertaDAO ofertaF = portUsuarios.obtenerUnaOfertaFinalizada(nombreOferta);
						List<PostulacionDAO> postulaciones = portUsuarios.obtenerPostulacionesOferta(nombreOferta).getValue();
						request.setAttribute("postulaciones", postulaciones);
						request.setAttribute("oferta", ofertaF);
						request.getRequestDispatcher("/WEB-INF/consultaOferta/detalleOfertaFinalizada.jsp").
						forward(request, response);
					}
				} catch (Exception e) {
					request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
					forward(request, response);
				}
			}
			else {
				List<DtOfertaLaboral> ofertas = null;
				if(keyword==null) ofertas= new ArrayList<DtOfertaLaboral>(portUsuarios.listarOfertasLaborales().getValue());
				else if (listaKeywords.contains(keyword))  ofertas= new ArrayList<DtOfertaLaboral>(portUsuarios.listarOfertasPorKeyword(keyword).getValue());
				else ofertas= new ArrayList<DtOfertaLaboral>();
				
				Collections.sort(ofertas, new Comparator<DtOfertaLaboral>() {
					@Override
					public int compare(DtOfertaLaboral p1, DtOfertaLaboral p2) {
						return p1.getNombre().compareToIgnoreCase(p2.getNombre());
					}
				});
				List<DtOfertaLaboral> ofertasConfirmadas = new ArrayList<DtOfertaLaboral>();
				for(DtOfertaLaboral ofertaC : ofertas) {
					if(ofertaC.getEstado() == Estado.CONFIRMADO) ofertasConfirmadas.add(ofertaC);
				}
				request.setAttribute("ofertas", ofertasConfirmadas);
				request.getRequestDispatcher("/WEB-INF/consultaOferta/consultaOferta.jsp").
				forward(request, response);
			}
		}
		else if(servletPath.equals("/OfertaMobile")) {
			if(request.getSession().getAttribute("usuarioLogueadoMobile") == null) {
				response.sendRedirect("SesionMobile");
				return;
			}
			String nombreOferta=request.getParameter("n");
			String keyword= request.getParameter("k");
			Set<String> listaKeywords= new HashSet<String>(portUsuarios.listarKeywords().getValue());
			request.setAttribute("listaKeywords", listaKeywords);
			
			if(nombreOferta!=null) {
				nombreOferta= URLDecoder.decode(nombreOferta,StandardCharsets.UTF_8.toString());
				//si no existe la oferta segun su nombre tirar 404
				try {
					DtOfertaLaboral oferta = portUsuarios.seleccionarOfertaLaboral(nombreOferta);
					String imagenPaquete=null;
					if(oferta.getPaquete()!=null && !oferta.getPaquete().equals("")) {
					DtPaquete dataPaquete= portTipos.obtenerDatosPaquete(oferta.getPaquete());
					imagenPaquete= dataPaquete.getImagen(); 
					}
					Map<String,String> imagenesPostulantes=new HashMap<String,String>();
					Set<DtPostulacion> postulaciones= new HashSet<DtPostulacion>(oferta.getPostulaciones());
					for(DtPostulacion post: postulaciones) {
						DtUsuario datosUsuario=portUsuarios.mostrarDatosUsuario(post.getPostulante());
						String imagen= datosUsuario.getImagen()!=null && !datosUsuario.getImagen().equals("") ? datosUsuario.getImagen(): null;
						imagenesPostulantes.put(post.getPostulante(), imagen);
					}
					request.setAttribute("imagenesPostulantes", imagenesPostulantes);
					request.setAttribute("imagenPaquete", imagenPaquete);
					request.setAttribute("oferta", oferta);
					request.getRequestDispatcher("/WEB-INF/consultaOferta/consultaOfertaDetallesMobile.jsp").
					
					forward(request, response);
					
				} catch (Exception e) {
					request.getRequestDispatcher("/WEB-INF/errorPages/404Mobile.jsp").
					forward(request, response);
				}
			}
			else {
				List<DtOfertaLaboral> ofertas = null;
				if(keyword==null) ofertas= new ArrayList<DtOfertaLaboral>(portUsuarios.listarOfertasLaborales().getValue());
				else if (listaKeywords.contains(keyword))  ofertas= new ArrayList<DtOfertaLaboral>(portUsuarios.listarOfertasPorKeyword(keyword).getValue());
				else ofertas= new ArrayList<DtOfertaLaboral>();
				
				Collections.sort(ofertas, new Comparator<DtOfertaLaboral>() {
					@Override
					public int compare(DtOfertaLaboral p1, DtOfertaLaboral p2) {
						return p1.getNombre().compareToIgnoreCase(p2.getNombre());
					}
				});
				List<DtOfertaLaboral> ofertasConfirmadas = new ArrayList<DtOfertaLaboral>();
				for(DtOfertaLaboral ofertaC : ofertas) {
					if(ofertaC.getEstado() == Estado.CONFIRMADO) ofertasConfirmadas.add(ofertaC);
				}
				request.setAttribute("ofertas", ofertasConfirmadas);
				request.getRequestDispatcher("/WEB-INF/consultaOferta/consultaOfertaMobile.jsp").
				forward(request, response);
			}
		}
		else if(servletPath.equals("/Favorito")) {
			String favorito= request.getParameter("fav");
			if(favorito!=null) {
				String ofertaFavorita= URLDecoder.decode(favorito,StandardCharsets.UTF_8.toString());
				DtUsuario usuario= request.getSession().getAttribute("usuarioLogueado")== null ? null : (DtUsuario) request.getSession().getAttribute("usuarioLogueado");
				try {
				portUsuarios.seleccionarOfertaLaboral(ofertaFavorita);
				}catch (NoExisteOferta_Exception ex){
					request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
				}catch (Exception e) {
					request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
				}
				if(usuario==null || usuario instanceof DtEmpresa ) request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
				portUsuarios.agregarOfertaFavorita(usuario.getNickname(),ofertaFavorita );
				request.getSession().setAttribute("usuarioLogueado", portUsuarios.mostrarDatosUsuario(usuario.getNickname()));
				if(request.getParameter("origen")!=null) {
					response.sendRedirect("Oferta?n="+ URLEncoder.encode(ofertaFavorita, StandardCharsets.UTF_8.toString()));
				}else {
					response.sendRedirect("Oferta");
				}
			}
			else request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").forward(request, response);
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
		WsTiposService wsTipos = new WsTiposService();
		WsTipos portTipos = wsTipos.getWsTiposPort();
		if(servletPath.equals("/Oferta")) {
			Set<String> listaKeywords= new HashSet<String>(portUsuarios.listarKeywords().getValue());
			request.setAttribute("listaKeywords", listaKeywords);
			
			String imagenPaquete=null;
			if (request.getParameter("ordenPostulantes")!=null) {
				String nOferta = request.getParameter("nOferta");
				try {
					DtOfertaLaboral oferta=portUsuarios.seleccionarOfertaLaboral(nOferta);				
					Set<DtPostulacion> postulaciones= new HashSet<>(oferta.getPostulaciones());
					for(DtPostulacion postulacion: postulaciones) {
						String nomPostulante=postulacion.getPostulante();
						String nomOferta=oferta.getNombre();
						int orden = Integer.parseInt(request.getParameter(postulacion.getPostulante()));
						portUsuarios.elegirOrden(nomPostulante,nomOferta,orden);
					}
					response.sendRedirect("Oferta?n="+URLEncoder.encode(nOferta, StandardCharsets.UTF_8.toString()));
				}
					catch(Exception e) {}
				}
			
			else if(request.getParameter("finalizar") != null) {
				String nOferta =request.getParameter("nOferta");
				try {
					DtOfertaLaboral oferta=portUsuarios.seleccionarOfertaLaboral(nOferta);
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String fechaVencimiento= oferta.getFechaVencimiento();
					Date fecha = formatter.parse(fechaVencimiento);
					boolean estaVencida = fecha.before(new Date());
					boolean exito;
					if(oferta.getEstado()==Estado.CONFIRMADO && estaVencida) {
						portUsuarios.finalizarOferta(oferta.getNombre());
						request.setAttribute("exito", true);
						exito=true;
					}
					else {
						exito=false;
						request.setAttribute("exito", false);
						request.setAttribute("mensaje", "La oferta no esta en condiciones de ser finalizada.");
					}
					if(exito==true) {
						response.sendRedirect("Oferta");
					}
					else {
						System.out.print("ALGO SUCEDIO MAL!!!!!!!");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("exito", false);
					request.setAttribute("mensaje", e.getMessage());
				}
			}
		}
		else if(servletPath.equals("/OfertaMobile")) {
			Set<String> listaKeywords= new HashSet<String>(portUsuarios.listarKeywords().getValue());
			request.setAttribute("listaKeywords", listaKeywords);
			doGet(request, response);
		}
	}
}
