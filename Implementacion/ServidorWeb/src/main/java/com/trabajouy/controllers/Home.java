package com.trabajouy.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservices.WsTipos;
import webservices.WsTiposService;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;

/**
 * Servlet implementation class Home
 */
@WebServlet({"/Home", "/HomeMobile"})
public class Home extends HttpServlet {
    /**
     * Default constructor. 
     */
    public Home() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletPath = req.getServletPath();
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		Set<String> listaKeywords= new HashSet<String>(portUsuarios.listarKeywords().getValue());
		req.setAttribute("listaKeywords", listaKeywords);
		if(servletPath.equals("/Home")) {
			req.getRequestDispatcher("/WEB-INF/home/Home.jsp").forward(req, res);
		}
		else if(servletPath.equals("/HomeMobile")) {
			if(req.getSession().getAttribute("usuarioLogueadoMobile") != null) {
				String cerrar=req.getParameter("c");
				if(cerrar!=null && cerrar.equals("true")) req.getSession().setAttribute("usuarioLogueadoMobile", null);
				req.getRequestDispatcher("/WEB-INF/home/HomeMobile.jsp").forward(req, res);
			}else {
				res.sendRedirect("SesionMobile");
			}
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


