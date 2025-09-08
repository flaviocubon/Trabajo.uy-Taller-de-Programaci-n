package com.trabajouy.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.CompraPaqueteRepetida;
import webservices.CompraPaqueteRepetida_Exception;
import webservices.DataPaquetes.Value;
import webservices.DataPaquetes.Value.Entry;
import webservices.DtEmpresa;
import webservices.DtPaquete;
import webservices.WsTipos;
import webservices.WsTiposService;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;

/**
 * Servlet implementation class Paquete
 */
@WebServlet({"/ConsultaPaquetes","/CompraPaquete", "/DetallesPaquete"})
public class Paquete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Paquete() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if("/ConsultaPaquetes".equals(servletPath)) {
			WsUsuariosService wsUsuarios = new WsUsuariosService();
			WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
			WsTiposService wsTipos = new WsTiposService();
			WsTipos portTipos = wsTipos.getWsTiposPort();
			request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
			List<Entry> listaEntryPaquetes = portTipos.obtenerDataPaquetes().getValue().getEntry();
			Map<String, DtPaquete> listaPaquetes = new HashMap<String, DtPaquete>();
			for(Entry paquete : listaEntryPaquetes) {
				listaPaquetes.put(paquete.getKey(), paquete.getValue());
			}
			request.setAttribute("listaPaquetes", listaPaquetes);
			request.getRequestDispatcher("/WEB-INF/consultaPaquetes/consultapaquetes.jsp").forward(request, response);
		} else if("/DetallesPaquete".equals(servletPath)) {
			mostrarDetallesPaquete(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		comprarPaquete(request, response);
	}
	
	protected void mostrarDetallesPaquete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		WsTiposService wsTipos = new WsTiposService();
		WsTipos portTipos = wsTipos.getWsTiposPort();
		String paquete = request.getParameter("paquete");
		DtPaquete detallesPaquete = portTipos.obtenerDatosPaquete(paquete);
		request.setAttribute("detallesPaquete", detallesPaquete);
		request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));

		Object usuario = request.getSession().getAttribute("usuarioLogueado");
		if(usuario != null && usuario.getClass() == DtEmpresa.class) {
			request.setAttribute("empresaPoseePaquete", portTipos.empresaPoseePaquete(((DtEmpresa)usuario).getNickname(), paquete)) ;
		}
		
		request.getRequestDispatcher("/WEB-INF/detallePaquete/contenedorDetallePaquete.jsp").forward(request, response);
	}
	
	protected void comprarPaquete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		WsTiposService wsTipos = new WsTiposService();
		WsTipos portTipos = wsTipos.getWsTiposPort();
		Object usuario = request.getSession().getAttribute("usuarioLogueado");
		if(usuario != null && usuario.getClass() == DtEmpresa.class) {
			String paquete = request.getParameter("paquete");
			try {
				portTipos.comprarPaqueteFechaActual(((DtEmpresa)usuario).getNickname(), paquete);
				request.setAttribute("exito", true);
				request.setAttribute("mensaje", "Paquete " + paquete + " comprado con exito");
				mostrarDetallesPaquete(request, response);
			} catch (CompraPaqueteRepetida_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
