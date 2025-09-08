<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="webservices.DataOfertas"%>
<%@page import="webservices.DtOfertaLaboral"%>
<%@page import="webservices.DtEmpresa"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<main class="container mainContainer ">
 	<h1>Busqueda de ofertas laborales y empresas</h1>
 	<section class="mx-1 px-1 py-2">
		<%
			String terminosDeBusqueda = (String) request.getAttribute("terminosDeBusqueda");
			int maxCharVisiblesDescripcion = 110;
			@SuppressWarnings("unchecked")
			List<DtOfertaLaboral> ofertasFiltradas = (List<DtOfertaLaboral>) request.getAttribute("ofertasFiltradas");
			@SuppressWarnings("unchecked")
			ArrayList<DtEmpresa> empresasFiltradas = (ArrayList<DtEmpresa>) request.getAttribute("empresasFiltradas");
		%>
 		<h2> Ofertas </h2>
	 	<p>Se han encontrado <%=ofertasFiltradas.size() + empresasFiltradas.size()%> resultados que contengan <span class="fw-semibold"><%=(terminosDeBusqueda != null) ? terminosDeBusqueda : "termino de busqueda nulo" %></span></p>
	 	<section class="d-flex justify-content-start flex-wrap">
		<%
			
			for(DtOfertaLaboral dto : ofertasFiltradas){
	        	
	        String urlCodificadaDetallesOferta= URLEncoder.encode(dto.getNombre(), StandardCharsets.UTF_8.toString());
	        
	        String descripcionVisibleOferta = dto.getDescripcion();
	        String descripcionOcultaOferta = "";
	        if(descripcionVisibleOferta.length() > maxCharVisiblesDescripcion) {
	        	descripcionOcultaOferta = descripcionVisibleOferta.substring(maxCharVisiblesDescripcion - 20);
	        	descripcionVisibleOferta = descripcionVisibleOferta.substring(0,maxCharVisiblesDescripcion - 20);
	        }
		%>
			<div class="tarjeta ">

				<h3><%=dto.getNombre() %></h3>
				<div>
					<p><%=descripcionVisibleOferta %><%=(descripcionOcultaOferta == "") ? "" : "..."%></p>
					<div class="wrapper-thumbnail">
						<img alt="thumbnail de <%=dto.getNombre() %>" src="Imagenes?id=<%=dto.getImagen()%>&tipo=ofertas">
					</div>
				</div>
    			<a href="Oferta?n=<%=urlCodificadaDetallesOferta%>" class="btn btn-primary">Ver Oferta</a>
			</div>
		<%
			};
		%>
		</section>
 		<h2> Empresas </h2>
	 	<section class="d-flex justify-content-start flex-wrap">
		<%
			
			for(DtEmpresa dte : empresasFiltradas){

			String urlCodificadaPerfil= URLEncoder.encode(dte.getNombre(), StandardCharsets.UTF_8.toString());
	        
	        String descripcionVisibleEmpresa = dte.getDescripcion();
	        String descripcionOcultaEmpresa = "";
	        if(descripcionVisibleEmpresa.length() > maxCharVisiblesDescripcion) {
	        	descripcionOcultaEmpresa = descripcionVisibleEmpresa.substring(maxCharVisiblesDescripcion - 20);
	        	descripcionVisibleEmpresa = descripcionVisibleEmpresa.substring(0, maxCharVisiblesDescripcion - 20);
	        }
		%>
			<div class="tarjeta container">
				<h3><%=dte.getNombre() %></h3>
				<div>
					<p><%=descripcionVisibleEmpresa %><%=(descripcionOcultaEmpresa == "") ? "" : "..."%></p>
					<div class="wrapper-thumbnail">
						<img alt="thumbnail de <%=dte.getNombre() %>" src="Imagenes?id=<%=dte.getImagen()%>&tipo=usuarios">
					</div>
				</div>
   				<a href="PerfilUsuario?nickname=<%=urlCodificadaPerfil%>" class="btn btn-primary">Ver Empresa</a>
			</div>
		<%
			};
		%>
		</section>
	</section>
</main>