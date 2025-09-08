package com.trabajouy.controllers;

import java.io.IOException;
import java.util.HashSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import webservices.DtEmpresa;
import webservices.DtUsuario;
import webservices.Exception_Exception;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;

/**
 * Servlet implementation class Sesion
 */
@WebServlet({"/Sesion","/cerrarSesion","/SesionMobile","/cerrarSesionMobile"})
public class Sesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Sesion() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		String servletPath = request.getServletPath();
		if(servletPath.equals("/Sesion")) {
			if(request.getSession().getAttribute("usuarioLogueado") == null) {
				request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
				request.getRequestDispatcher("/WEB-INF/inicioSesion/inicioSesion.jsp").
				forward(request, response);
			}else {
				response.sendRedirect("Home");
			}
		}
		else if(servletPath.equals("/cerrarSesion")) {
			request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
			request.getSession().setAttribute("usuarioLogueado", null);
			request.getRequestDispatcher("/WEB-INF/inicioSesion/cierreSesion.jsp").forward(request, response);
		}
		else if(servletPath.equals("/cerrarSesionMobile")) {
			request.getSession().setAttribute("usuarioLogueadoMobile", null);
			response.sendRedirect("SesionMobile");
		}
		else if(servletPath.equals("/SesionMobile")) {
			if(request.getSession().getAttribute("usuarioLogueadoMobile") == null) {
				request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
				request.getRequestDispatcher("/WEB-INF/inicioSesion/inicioSesionMobile.jsp").
				forward(request, response);
			}else {
				response.sendRedirect("HomeMobile");
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
		if(servletPath.equals("/Sesion")) {
			String nickname = (String) request.getParameter("nickname");
			String pass = (String) request.getParameter("pass");
			DtUsuario usuario;
			try {
				usuario = portUsuarios.iniciarSesion(nickname, pass);
			}catch(Exception ex){
				usuario = null;
			}
			if(usuario != null) {
				request.getSession().setAttribute("usuarioLogueado", usuario);
				response.sendRedirect("Home");
			}else {
				request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
				request.getRequestDispatcher("/WEB-INF/inicioSesion/errorInicio.jsp").forward(request, response);
			}
		}
		if(servletPath.equals("/SesionMobile")) {
			String nickname = (String) request.getParameter("nickname");
			String pass = (String) request.getParameter("pass");
			DtUsuario usuario;
			try {
				usuario = portUsuarios.iniciarSesion(nickname, pass);
				if(usuario.getClass()== DtEmpresa.class) {
					usuario=null;
				}
			}catch(Exception ex){
				usuario = null;
			}
			if(usuario != null) {
				request.getSession().setAttribute("usuarioLogueadoMobile", usuario);
				response.sendRedirect("HomeMobile");
			}else {
				request.setAttribute("listaKeywords", new HashSet<String>(portUsuarios.listarKeywords().getValue()));
				request.getRequestDispatcher("/WEB-INF/inicioSesion/errorInicioMobile.jsp").forward(request, response);
			}
		}
	}

}
