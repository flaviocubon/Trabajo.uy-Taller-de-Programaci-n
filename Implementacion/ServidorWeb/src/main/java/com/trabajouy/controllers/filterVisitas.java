package com.trabajouy.controllers;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import webservices.DtOfertaLaboral;
import webservices.WsUsuarios;
import webservices.WsUsuariosService;

public class filterVisitas implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		WsUsuariosService wsUsuarios = new WsUsuariosService();
		WsUsuarios portUsuarios = wsUsuarios.getWsUsuariosPort();
		String nombreOferta=arg0.getParameter("n");
		HttpServletRequest sreq = (HttpServletRequest)arg0;

			if(nombreOferta!=null && "GET".equals(sreq.getMethod())) {
				nombreOferta= URLDecoder.decode(nombreOferta,StandardCharsets.UTF_8.toString());
				//si no existe la oferta segun su nombre tirar 404
				try {
					DtOfertaLaboral oferta = portUsuarios.seleccionarOfertaLaboral(nombreOferta);
					portUsuarios.agregarVisita(nombreOferta);
				}
				catch (Exception e){
					}
				}
			arg2.doFilter(arg0, arg1);
	}

}
