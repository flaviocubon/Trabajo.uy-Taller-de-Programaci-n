<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<header>
	<nav class="navbar-custom navbar navbar-expand-lg navbar-dark custom-shadow sticky-top mobile" id="navbar-text">
		<!-- Botón home y nombre del sitio -->
		<a class="navbar-brand order-1" href="HomeMobile">
			<i class="bi bi-bag-fill" id="logo"></i>
			<strong> Trabajo.uy </strong>
		</a>
		<!-- Links colapsables a casos de uso -->
		<div class="d-lg-none collapse navbar-collapse order-3 order-lg-3" id="listaCasosDeUso">
     		<ul class="navbar-nav">
	  			<jsp:include page="/WEB-INF/template/linksCasosDeUsoMobile.jsp" />
    		</ul>
		</div>
		<div class="user-info-2 order-2 order-lg-3">
			<%
				DtUsuario usr = (DtUsuario) session.getAttribute("usuarioLogueadoMobile");
				if (usr == null) {
			%>
		<!-- 	<ul class="nav">
				<li class="nav-item">
			    	<a class="nav-link text-white fw-semibold" href="SesionMobile">Iniciar Sesión</a>
			    </li>
			</ul> -->
			<%
				} else {
			%>
		    <div id="user-dropdown-toggle" class="user-toggle">
				<img src="Imagenes?id=<%=usr.getImagen()%>&tipo=usuarios" alt="Avatar" class="user-avatar">
		        <div class="user-name"><%= usr.getNickname() %></div>
		    </div>
		   <!--  	<ul class="user-menu" id="user-dropdown">
		        <li><a href="HomeMobile?c=true">Cerrar Sesión</a></li>
		    </ul>-->
		    <%
				}
			%>
			<!-- Botón menu hamburguesa -->
         </div>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#listaCasosDeUso" aria-controls="listaCasosDeUso" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	</nav>
</header>