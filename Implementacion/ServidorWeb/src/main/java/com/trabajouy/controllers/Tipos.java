package com.trabajouy.controllers;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.DataTipos.Value.Entry;
import webservices.DtTipoPublicacion;
import webservices.WsTipos;
import webservices.WsTiposService;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;
/**
 * Servlet implementation class Tipos
 */
@WebServlet("/Tipos")
public class Tipos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Tipos() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsTiposService wsTipos = new WsTiposService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();		
		WsTipos portTipos = wsTipos.getWsTiposPort();
		request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
		String nombreTipo = (String)request.getParameter("t");
		if (nombreTipo!=null) {
			nombreTipo = URLDecoder.decode(nombreTipo,"UTF-8");
			List<Entry> tipos = portTipos.obtenerDataTipos().getValue().getEntry();
			Iterator<Entry> it = tipos.iterator();
			boolean existeTipo = false;
			DtTipoPublicacion dataTipo = null;
			while(existeTipo == false && it.hasNext()) {
				Entry tipoApuntado = it.next();
				String nomTipoIT = tipoApuntado.getKey();
				if (nomTipoIT.equals(nombreTipo)) {
					existeTipo = true;
					dataTipo = tipoApuntado.getValue();
				}
			}
			if(dataTipo!=null) {
			request.setAttribute("tipoPublicacion", dataTipo);
			request.getRequestDispatcher("/WEB-INF/consultaTipos/consultaTipoDetalle.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").include(request, response);
				return;
			}
			
		}
		else {
			List<Entry> tipos = portTipos.obtenerDataTipos().getValue().getEntry();
			Set<String> nombreTipos = new HashSet<String>();
			for(Entry tipo : tipos) {
				nombreTipos.add(((Entry) tipo).getKey());
			}
			request.setAttribute("tiposPublicacion", nombreTipos);
			request.getRequestDispatcher("/WEB-INF/consultaTipos/consultaTipo.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
