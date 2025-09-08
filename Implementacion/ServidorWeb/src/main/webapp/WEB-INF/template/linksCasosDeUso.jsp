<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtEmpresa"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>


<%
	DtUsuario usr = (DtUsuario) session.getAttribute("usuarioLogueado");
	if (usr != null) {
%>
<li class="nav-item">
  <a href="PerfilUsuario?nickname=<%=URLEncoder.encode(usr.getNickname(), StandardCharsets.UTF_8.toString()) %>" class="nav-link list-group-item list-group-item-action py-2 ripple"><span>Mi Perfil</span></a>
</li>
<% 
	} 
%>
<li class="nav-item">
  <a href="Oferta"
  class="nav-link list-group-item list-group-item-action py-2 ripple"><span>Ofertas
      Laborales</span></a>
</li>
<%
	if (usr instanceof DtEmpresa) {
%>
<li class="nav-item">
  <a href="AltaOferta" class="nav-link list-group-item list-group-item-action py-2 ripple"><span>Crear
                  Oferta</span></a>
</li>
<% 
	} 
%>
<li class="nav-item">
  <a href="ConsultaUsuario"
      class="nav-link list-group-item list-group-item-action py-2 ripple"><span>Usuarios</span></a>
</li>
      
<li class="nav-item">
  <a href="Tipos" class="nav-link list-group-item list-group-item-action py-2 ripple"><span>Tipos de
      Publicación</span></a>
</li>
      
<li class="nav-item">
  <a href="ConsultaPaquetes" class="nav-link list-group-item list-group-item-action py-2 ripple"><span>Paquetes de Tipo de
      Publicación</span></a>
</li>
