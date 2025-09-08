package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.DataEmpresas;
import webservices.DataOfertas;
import webservices.DtPaquete;
import webservices.WsTipos;
import webservices.WsTiposService;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;

import java.io.IOException;
import java.util.HashSet;

/**
 * Servlet implementation class BusquedaOfertasYEmpresas
 */
@WebServlet({"/BusquedaOfertasYEmpresas"})
@MultipartConfig
public class BusquedaOfertasYEmpresas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusquedaOfertasYEmpresas() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Agrego las keyword para la sidebar
		listarKeywords(request, response);
		
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		String terminosDeBusqueda = request.getParameter("terminosDeBusqueda");
		portUsuarios.listarEmpresas();
		DataOfertas ofertasFiltradas = portUsuarios.buscarPorFiltroOfertasLaborales(terminosDeBusqueda);
		DataEmpresas empresasFiltradas = portUsuarios.buscarPorFiltroEmpresas(terminosDeBusqueda);
		
		request.setAttribute("terminosDeBusqueda", terminosDeBusqueda);
		request.setAttribute("ofertasFiltradas", ofertasFiltradas.getValue());
		request.setAttribute("empresasFiltradas", empresasFiltradas.getValue());
		
		// Renderizar los jsp a un HTML
		request.getRequestDispatcher("/WEB-INF/busquedaOfertasYEmpresas/contenedorBusquedaOfertasYEmpresas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void listarKeywords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
	}

}
