<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <% if (request.getAttribute("exito") != null && (boolean)request.getAttribute("exito") !=true) {%>
		<div id="mensajeError" class="alert alert-danger" role="alert">
		  <%=request.getAttribute("mensaje")%>
		</div>
	<% } else if (request.getParameter("mensaje") != null) {  %>
		<div id="mensajeError" class="alert alert-success" role="alert">
		  <%=request.getParameter("mensaje").replace("%20"," ")%>
		</div>
	<% } %>	
	
