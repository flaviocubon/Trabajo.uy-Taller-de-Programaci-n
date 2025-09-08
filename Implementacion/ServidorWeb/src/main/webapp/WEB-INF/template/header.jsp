<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="webservices.DtUsuario"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<header>
	<nav class="navbar-custom navbar navbar-expand-lg navbar-dark custom-shadow sticky-top" id="navbar-text">
		<!-- Wrapper botones de menu y home -->
		<div class="wrapper">
			<!-- Botón menu hamburguesa -->
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#listaCasosDeUso" aria-controls="listaCasosDeUso" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
			<!-- Botón home y nombre del sitio -->
			<a class="navbar-brand" href="Home">
				<i class="bi bi-bag-fill" id="logo"></i>
				<strong> Trabajo.uy </strong>
			</a>
		</div>
		<!-- Campo de busqueda de ofertas laborales y empresas -->
		<form action="BusquedaOfertasYEmpresas" method="get" class="ofertas-search-bar vh-25 flex-fill d-flex justify-content-center">
			<i class="bi bi-search"></i>
			<input class=" flex-xl-grow-0" name="terminosDeBusqueda" type="text" placeholder="Ofertas laborales, Empresas">
		</form>
		<!-- Manejo de sesión y menu de usuario -->
		<div class="user-info-2">
			<%
				DtUsuario usr = (DtUsuario) session.getAttribute("usuarioLogueado");
				if (usr == null) {
			%>
			<!-- Links de iniciar sesión y registrarse -->
			<ul class="nav">
				<li class="nav-item">
			    	<a class="nav-link text-white fw-semibold" href="Sesion">Iniciar Sesión</a>
			    </li>
			    <li class="nav-item">
			    	<a class="nav-link text-white fw-semibold" href="AltaUsuario">Registrarse</a>
			    </li>
			</ul>
			<%
				} else {
			%>
			<!-- Menu del usuario -->
		    <div id="user-dropdown-toggle" class="user-toggle">
				<img src="Imagenes?id=<%=usr.getImagen()%>&tipo=usuarios" alt="Avatar" class="user-avatar">
		        <div class="user-name"><%= usr.getNickname() %></div>
		    </div>
		   	<ul class="user-menu" id="user-dropdown">
		    	<li><a href="PerfilUsuario?nickname=<%=URLEncoder.encode(usr.getNickname(), StandardCharsets.UTF_8.toString()) %>" >Mi Perfil</a></li>
		        <li><a href="cerrarSesion">Cerrar Sesión</a></li>
		    </ul>
		    <%
				}
			%>
         </div>
		<!-- Links colapsables a casos de uso -->
		<div class="d-lg-none collapse navbar-collapse" id="listaCasosDeUso">
     		<ul class="navbar-nav">
	  			<jsp:include page="/WEB-INF/template/linksCasosDeUso.jsp" />
    		</ul>
		</div>
	</nav>
</header>