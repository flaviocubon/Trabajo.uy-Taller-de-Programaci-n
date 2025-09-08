<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page import="webservices.DtEmpresa"%>

<%
Object usuario = session.getAttribute("usuarioLogueado");
Boolean usuarioEsEmpresa = usuario != null && usuario.getClass() == DtEmpresa.class;
Boolean empresaPoseePaquete = (Boolean) request.getAttribute("empresaPoseePaquete");
%>

<%
if (usuarioEsEmpresa && !empresaPoseePaquete) {
	// El actor es una Empresa y no posee el paquete
	if (request.getAttribute("exito") == null ) {
		// Compra no hecha y empresa puede comprar paquete
%>
		<div id="submitBtn" class="me-2">
			<input type="submit" class="btn btn-light" value="Comprar">
		</div>
		<a id="cancelBtn" href="ConsultaPaquetes" class="btn btn-light">
			Volver </a>
<%
	} else if (!(Boolean) request.getAttribute("exito") ) {
		// Compra fallida
		Object mensageObj = request.getParameter("mensaje");
		String mensaje = ( mensageObj != null && mensageObj.getClass() == String.class ) ? ": " + (String)mensageObj : "";
%>
		<div class="alert alert-danger my-0 me-2" style="padding: 0.375rem 0.75rem">Operación fallida<%=mensaje%></div>
		<a id="cancelBtn" href="ConsultaPaquetes" class="btn btn-light">
			Seguir consultando paquetes </a>
		<a href="Home" class="btn btn-light"> Volver a inicio </a>
<%
	}
} else if(!usuarioEsEmpresa) {
	// El actor no es una empresa
%>
	<a id="cancelBtn" href="ConsultaPaquetes" class="btn btn-light">
		Volver </a>
<%
} else if(empresaPoseePaquete && request.getAttribute("exito") == null){
	// El actor es una empresa que ya posee el paquete
	
%>	
	<div class="alert alert-secondary my-0 me-2"  style="padding: 0.375rem 0.75rem">Usted ya posee este paquete</div>
	<a id="cancelBtn" href="ConsultaPaquetes" class="btn btn-light">
		Volver </a>
<%
} else if ((Boolean) request.getAttribute("exito")) {
	// La empresa posee el paquete y lo acaba de comprar con éxito
	Object mensageObj = request.getParameter("mensaje");
	String mensaje = ( mensageObj != null && mensageObj.getClass() == String.class ) ? ": " + (String)mensageObj : "";
%>
	<div class="alert alert-dark my-0 me-2" style="padding: 0.375rem 0.75rem">Operación satisfactoria<%=mensaje%></div>
	<a id="cancelBtn" href="ConsultaPaquetes" class="btn btn-light me-2">
		Ver más paquetes </a>
	<a href="Home" class="btn btn-light"> Volver a inicio </a>

<% } %>
