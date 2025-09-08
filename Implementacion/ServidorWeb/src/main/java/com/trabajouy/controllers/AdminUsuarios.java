package com.trabajouy.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.tomcat.jakartaee.commons.compress.utils.FileNameUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;


/**
 * Servlet implementation class AdminUsuarios
 */
@WebServlet({"/AltaUsuario","/ModificarUsuario"})
@MultipartConfig
public class AdminUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AdminUsuarios() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios port = wsUsuarios.getWsUsuariosPort();
		Set<String> listaKeywords= new HashSet<String>(port.listarKeywords().getValue());
		request.setAttribute("listaKeywords", listaKeywords);
		request.getRequestDispatcher("/WEB-INF/altaUsuario/altaUsuario.jsp").
		forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unused" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletPath = request.getServletPath();
		
		if ("/AltaUsuario".equals(servletPath)) {
			WsUsuariosService wsUsuarios = new WsUsuariosService();
			WsUsuarios port = wsUsuarios.getWsUsuariosPort();
			Set<String> listaKeywords= new HashSet<String>(port.listarKeywords().getValue());
			request.setAttribute("listaKeywords", listaKeywords);
			if (request.getParameter("existeNick") != null) {
				boolean existeNick = port.existeUsuario(request.getParameter("nickname"));
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				if (existeNick) {
					response.getWriter().write("true");
				} else {
					response.getWriter().write("false");
				}
				return;
			} else if (request.getParameter("existeEmail") != null) {
				boolean existeEmail = port.existeEmail(request.getParameter("email"));
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				if (existeEmail) {
					response.getWriter().write("true");
				} else {
					response.getWriter().write("false");
				}
				return;
			}
			String nickname = request.getParameter("nickname");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String contrasenia = request.getParameter("contrasenia");
			String confirmacion = request.getParameter("confirmacion");
			if (!contrasenia.equals(confirmacion)) {
				request.setAttribute("exito", false);
				request.setAttribute("mensaje", "La contraseña y la confirmación deben coincidir");
				request.getRequestDispatcher("/WEB-INF/altaUsuario/altaUsuario.jsp").forward(request, response);
				return;
			}
			String email = request.getParameter("email");
			String fecha = request.getParameter("fecha");
			String nacionalidad = request.getParameter("nacionalidad");
			String descripcion = request.getParameter("descripcion");
			String link = request.getParameter("url") == null ? "":request.getParameter("url");
			String tipouser = request.getParameter("tipoUsuario");
			Part imagen = request.getPart("imagen");
			String extension = FileNameUtils.getExtension(imagen.getSubmittedFileName());
			if (imagen.getSize() > 0 && (!extension.equals("png") && !extension.equals("jpeg") && !extension.equals("jpg"))) {
				request.setAttribute("exito", false);
				request.setAttribute("mensaje", "Debe ingresar una imagen en formato png, jpeg o jpg");
				request.getRequestDispatcher("/WEB-INF/altaUsuario/altaUsuario.jsp").forward(request, response);
				return;
			}
			String nombreImagen = "default.png";
			if ("postulante".equals(tipouser)) {
				try {
					if (imagen.getSize() > 0) nombreImagen = port.writeImg(nickname, "usuarios", extension, imagen.getInputStream().readAllBytes());
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
					port.ingresarPostulante(nickname, nombre, apellido, email, xmlDate ,nacionalidad, nombreImagen, contrasenia);
					request.setAttribute("exito", true);
					request.setAttribute("mensaje", "Usuario creado con exito");
					
				} catch (webservices.Exception_Exception e) {
					request.setAttribute("exito", false);
					request.setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/WEB-INF/altaUsuario/altaUsuario.jsp").forward(request, response);
					return;
				}	
			} else if ("empresa".equals(tipouser)) {
				try {
					int id = 0;
					if (imagen.getSize() > 0) nombreImagen = port.writeImg(nickname, "usuarios", extension, imagen.getInputStream().readAllBytes());
					port.ingresarEmpresa(nickname, nombre, apellido, email, nickname, descripcion, link, nombreImagen, contrasenia);
					request.setAttribute("exito", true);
					request.setAttribute("mensaje", "Usuario creado con exito");
				}catch (webservices.Exception_Exception e) {
					request.setAttribute("exito", false);
					request.setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("/WEB-INF/altaUsuario/altaUsuario.jsp").forward(request, response);
					return;
				}
			}
			response.sendRedirect("AltaUsuario?mensaje=Usuario creado con exito");
		} else if ("/ModificarUsuario".equals(servletPath)) {
			
		}
	}
}
