<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="webservices.DtUsuario"%>
<%@page import="webservices.DtEmpresa"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>


<li class="nav-item">
<%if(request.getSession().getAttribute("usuarioLogueadoMobile")!= null){ %>
  <a href="OfertaMobile" class="nav-link list-group-item list-group-item-action py-2 ripple"><span>Ofertas Laborales</span></a>
  <a href="cerrarSesionMobile" class="nav-link list-group-item list-group-item-action py-2 ripple"><span>Cerrar Sesión</span></a>
  <%} %>
</li>
